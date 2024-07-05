package Modelos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String user = "sa";
    private final String password = "12345678";
    private final String url = "jdbc:sqlserver://localhost\\MSSQL:1433;databaseName=db_riga;encrypt=true;trustServerCertificate=true" + "";
    private final String db = "db_riga";
    private Connection conexion;
    Statement stm;
    ResultSet rs;
    
    public Connection getConexion()throws SQLException{
        
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Se conecto a la base de datos: "+db);
        }catch (ClassNotFoundException ex){
            System.out.println("Error al conectar a la base de datos"+ex);
        }
        return conexion;
    }
    
    public void ejecutaStatement(String query, Connection conexion)throws SQLException{
        
        try{
        stm = conexion.createStatement();
        rs = stm.executeQuery(query);
        
        while(rs.next()){
            System.out.println("Nombre"+rs.getString(2));
        }
        }catch(SQLException ex){
            
        }finally {
        if (rs != null) try{rs.close();}catch(SQLException e){}
        if (stm != null) try{stm.close();}catch(SQLException e){}
        if (conexion != null) try{conexion.close();}catch(SQLException e){}
        }
    }
    
    public static void main(String[] args) throws SQLException{
        Conexion con = new Conexion();
        con.getConexion();
    }

}


//package Modelos;
//
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Conexion {
//    private final String url = "jdbc:sqlserver://dbriga.database.windows.net:1433;database=db_riga-2023-11-29-23-16;user=sqlriga@dbriga;password=@Rigadbsqlserver;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//    
//    private Connection conexion;
//    Statement stm;
//    ResultSet rs;
//    
//    public Connection getConexion()throws SQLException{
//        
//        try{
//        conexion = DriverManager.getConnection(url);
//            System.out.println("Se conecto a la base de datos: ");
//        }catch (SQLException ex){
//            System.out.println("Error al conectar a la base de datos"+ex);
//        }
//        return conexion;
//    }
//    
//    public void ejecutaStatement(String query, Connection conexion)throws SQLException{
//        
//        try{
//        stm = conexion.createStatement();
//        rs = stm.executeQuery(query);
//        
//        while(rs.next()){
//            System.out.println("Nombre"+rs.getString(2));
//        }
//        }catch(SQLException ex){
//            
//        }finally {
//        if (rs != null) try{rs.close();}catch(SQLException e){}
//        if (stm != null) try{stm.close();}catch(SQLException e){}
//        if (conexion != null) try{conexion.close();}catch(SQLException e){}
//        }
//    }
//    
//    public static void main(String[] args) throws SQLException{
//        Conexion con = new Conexion();
//        con.getConexion();
//    }
//
//}