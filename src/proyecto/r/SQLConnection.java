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
    private volatile static String server = "sql3.freemysqlhosting.net:3306";
    private volatile static String dbName = "sql3131722";
    private volatile static String user = "sql3131722";
    private volatile static String pass = "EjJGjgJDhf";
    
    public static boolean abrirConexion(){
        if(conexion == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + 
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
    
    public static void cerrarConexion(){
        if(conexion != null) try {
            if(!conexion.isClosed())
                conexion.close();
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
    }
    
    
}
