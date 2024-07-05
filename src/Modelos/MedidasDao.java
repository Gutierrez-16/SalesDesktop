
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MedidasDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrar(Medidas med){
        String sql = "INSERT INTO medidas (medida, nombre_corto) VALUES(?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, med.getNombre());
            ps.setString(2, med.getNombre_corto());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public List ListaMeidas(String valor){
        List<Medidas> ListarMed = new ArrayList();
        String sql = "SELECT * FROM medidas ORDER BY estado ASC";
        String buscar = "SELECT * FROM medidas WHERE medida LIKE '%"+valor+"%' OR nombre_corto LIKE '%"+valor+"%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }else{
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while(rs.next()){
                Medidas med = new Medidas();
                med.setId(rs.getInt("id"));
                med.setNombre(rs.getString("medida"));
                med.setNombre_corto(rs.getString("nombre_corto"));
                med.setEstado(rs.getString("estado"));
                ListarMed.add(med);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListarMed;
    }
    
    public boolean modificar(Medidas med){
        String sql = "UPDATE medidas SET medida = ?, nombre_corto = ? WHERE id = ? ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, med.getNombre());
            ps.setString(2, med.getNombre_corto());
            ps.setInt(3, med.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public boolean accion(String estado, int id){
        String sql = "UPDATE medidas SET estado = ? WHERE id = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
}
