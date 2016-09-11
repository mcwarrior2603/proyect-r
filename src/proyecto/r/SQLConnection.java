/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MCwar
 */
public class SQLConnection {
    
    private volatile static Connection conexion;
    private volatile static String server = "localhost:3306";
    private volatile static String dbName = "presto";
    private volatile static String user = "presto";
    private volatile static String pass = "fenixoftPresto";
    
    public synchronized static boolean abrirConexion(){
        if(conexion == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://" + server + "/" + dbName + 
                        "?user=" + user + "&password=" + pass);
                System.out.println("Conexión correcta");
                return true;
            } catch (ClassNotFoundException ex) {
                Ventana.reportarError(ex);
            } catch (SQLException ex) {
                Ventana.reportarError(ex);
            }            
            System.out.println("Conexion incorrecta");
            return false;
        }
                
        return true;
    }
    
    public synchronized static void cerrarConexion(){
        if(conexion != null) try {
            if(!conexion.isClosed())
                conexion.close();
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
    }
    
    public synchronized static ResultSet buscar(String sql){
        ResultSet query;
        if(!abrirConexion())
            return null;
        try {
            Statement st = conexion.createStatement();
            query = st.executeQuery(sql);
            return query;
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }                        
        return null;
    }
    
    public synchronized static boolean actualizar(String sql, int cambiosEsperados){
        if(!abrirConexion())
            return false;
        try {
            Statement st = conexion.createStatement();
            conexion.setAutoCommit(false);
            
            int rowCount = st.executeUpdate(sql);
            
            if(rowCount == cambiosEsperados){
                conexion.commit();
                return true;
            } else{
                conexion.rollback();
                return false;
            }
            
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                
            }
            return false;
        }
        
    }
    
    public synchronized static boolean actualizar(String sql){
        return actualizar(sql, 1);
    }

    
    
}
