package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VentasDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List ListaVentas(String valor) {
        List<Ventas> Lista = new ArrayList();
        String sql = "SELECT v.*, c.nombre FROM ventas v INNER JOIN clientes c ON v.cliente_id = c.id ORDER BY v.id DESC";
        String buscar = "SELECT v.*, c.nombre FROM ventas v INNER JOIN clientes c ON v.cliente_id = c.id WHERE c.nombre LIKE '%" + valor + "%' OR v.fecha LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
            } else {
                ps = con.prepareStatement(buscar);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Ventas vent = new Ventas();
                vent.setId(rs.getInt("id"));
                vent.setCliente_id(rs.getInt("cliente_id"));
                vent.setTotal(rs.getDouble("total"));
                vent.setFecha(rs.getString("fecha"));
                vent.setNombreCliente(rs.getString("nombre"));
                Lista.add(vent);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return Lista;
    }

    public String verificarCaja(int usuario_id) {
        String consulta = "SELECT * FROM detalle_cajas WHERE estado = ? AND usuario_id = ?";
        try {
            con = cn.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(VentasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, 1);
            ps.setInt(2, usuario_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                return "no";
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentasDao.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
