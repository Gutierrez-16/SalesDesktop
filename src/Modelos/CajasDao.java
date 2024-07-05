package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CajasDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Cajas cj) {
        String sql = "INSERT INTO cajas (nombre) VALUES(?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cj.getNombre());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public List ListaCajas(String valor) {
        List<Cajas> ListarCaj = new ArrayList();
        String sql = "SELECT * FROM cajas ORDER BY estado ASC";
        String buscar = "SELECT * FROM cajas WHERE nombre LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Cajas cj = new Cajas();
                cj.setId(rs.getInt("id"));
                cj.setNombre(rs.getString("nombre"));
                cj.setEstado(rs.getString("estado"));
                ListarCaj.add(cj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListarCaj;
    }

    public boolean modificar(Cajas cj) {
        String sql = "UPDATE cajas SET nombre = ? WHERE id = ? ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cj.getNombre());
            ps.setInt(2, cj.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public boolean accion(String estado, int id) {
        String sql = "UPDATE cajas SET estado = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public String abrirCaja(double monto, int usuario_id) {
        String consulta = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.usuario_id = u.id  WHERE d.estado = 1";

        String sql = "INSERT INTO detalle_cajas (monto_inicial, usuario_id) VALUES(?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                ps = con.prepareStatement(sql);
                ps.setDouble(1, monto);
                ps.setInt(2, usuario_id);
                ps.execute();
                return "registrado";
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }
    }

    public List ListaAperturas(String valor) {
        List<AperturaCierre> Lista = new ArrayList();

        try {
            con = cn.getConexion();

            if ("".equalsIgnoreCase(valor)) {
                String sql = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.usuario_id = u.id  ORDER BY d.fecha_apertura DESC";
                ps = con.prepareStatement(sql);
            } else {
                String sql = "SELECT d.*, u.nombre FROM detalle_cajas d INNER JOIN usuarios u ON d.usuario_id = u.id  WHERE u.nombre LIKE '%" + valor + "%' OR d.fecha_apertura LIKE '%" + valor + "%' ";
                ps = con.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                AperturaCierre apert = new AperturaCierre();
                apert.setFecha_apertura(rs.getString("fecha_apertura"));
                apert.setFecha_cierre(rs.getString("fecha_cierre"));
                apert.setMonto_inicial(rs.getDouble("monto_inicial"));
                apert.setMonto_final(rs.getDouble("monto_final"));
                apert.setTotal_ventas(rs.getInt("total_ventas"));
                apert.setNombreUsuario(rs.getString("nombre"));
                Lista.add(apert);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return Lista;
    }
    
    public double montoFinal(int usuario_id) {
        double monto = 0.00;
        String sql = "SELECT SUM (total) AS monto_total FROM ventas WHERE usuario_id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                monto = rs.getDouble("monto_total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return monto;
    }
    
    public int totalVentas(int usuario_id) {
        int total = 0;
        String sql = "SELECT COUNT (*) AS total FROM ventas WHERE usuario_id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return total;
    }
    
    public boolean cerrarCaja(AperturaCierre apert) {
        String sql = "UPDATE detalle_cajas SET fecha_cierre = ?, monto_final = ?, total_ventas = ?, estado = ? WHERE usuario_id = ? AND estado = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, apert.getFecha_cierre());
            ps.setDouble(2, apert.getMonto_final());
            ps.setDouble(3, apert.getTotal_ventas());
            ps.setDouble(4, 0);
            ps.setDouble(5, apert.getUsuario_id());
            ps.setDouble(6, 1);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
}
