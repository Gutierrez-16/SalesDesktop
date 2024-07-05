package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class UsuarioDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuarios Login(String usuario, String clave) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
        Usuarios us = new Usuarios();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()){
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return us;
    }  
    
    public boolean registrar(Usuarios us){
        String sql = "INSERT INTO usuarios (usuario, nombre, clave, caja_id, rol) VALUES(?,?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getUsuario());
            ps.setString(2, us.getNombre());
            ps.setString(3, us.getClave());
            ps.setInt(4, us.getCaja());
            ps.setString(5, us.getRol());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public List ListaUsuarios(String valor){
        List<Usuarios> ListatUsers = new ArrayList();
        String sql = "SELECT u.*, c.nombre AS caja FROM usuarios u INNER JOIN cajas c ON u.caja_id = c.id ORDER BY u.estado ASC";
        String buscar = "SELECT u.*, c.nombre AS caja FROM usuarios u INNER JOIN cajas c ON u.caja_id = c.id WHERE u.usuario LIKE '%"+valor+"%' OR u.nombre LIKE '%"+valor+"%' OR c.caja LIKE '%"+valor+"%'";
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
                Usuarios us = new Usuarios();
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setNombre(rs.getString("nombre"));
                us.setCaja(rs.getInt("caja_id"));
                us.setNombre_Caja(rs.getString("caja"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
                ListatUsers.add(us);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListatUsers;
    }
    
    public boolean modificar(Usuarios us){
        String sql = "UPDATE usuarios SET usuario = ?, nombre = ?, caja_id = ?, rol = ? WHERE id = ? ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getUsuario());
            ps.setString(2, us.getNombre());
            ps.setInt(3, us.getCaja());
            ps.setString(4, us.getRol());
            ps.setInt(5, us.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public boolean accion(String estado, int id){
        String sql = "UPDATE usuarios SET estado = ? WHERE id = ?";
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
    
    public Configuracion getConfig(){
        String sql = "SELECT * FROM configuracion ";
        Configuracion cof = new Configuracion();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                cof.setId(rs.getInt("id"));
                cof.setRuc(rs.getString("ruc"));
                cof.setNombre(rs.getString("nombre"));
                cof.setTelefono(rs.getString("telefono"));
                cof.setDireccion(rs.getString("direccion"));
                cof.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return cof;
    }
}

