/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author MCwar
 */
public class Ventana extends JFrame implements WindowListener{

    protected static Font fontTitulo = new Font("Arial", Font.BOLD, 20);
    public static final float DEFAULT_AFLOAT = -1.7932f;
    
    public static String hoy(String separador){
        Calendar fecha = Calendar.getInstance();
        String ret = "";
        
        ret += fecha.get(Calendar.YEAR) + separador;
        ret += (fecha.get(Calendar.MONTH) + 1) + separador;
        ret += fecha.get(Calendar.DAY_OF_MONTH);
        
        return ret;        
    }
        
    public static String hoy(){
        return hoy("/");
    }
    
    
    public static String obtenerMaster(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("archivos/master.txt"));
            return reader.readLine();
        } catch (FileNotFoundException ex) {
            reportarError(ex);
        } catch (IOException ex) {
            reportarError(ex);
        } 
        
        return "";
    }
    
    public static void reportarError(Exception e){
        String error = "¡Ha ocurrido un error :(\n\nTe recomiendo tomar una foto para\n" + 
                "facilitar la correción del fallo\n\n"
                + e.toString() + "\n\n";                                                        
        
        int num = 1;
        File archivoObj = new File("log/log" + num + ".txt");
            
        while(archivoObj.exists()){
            num++;
            archivoObj = new File("log/log" + num + ".txt");            
        }
        
        error += "Guardado en log/log" + num + ".txt";
        JOptionPane.showMessageDialog(null, error, "¡Error!", JOptionPane.ERROR_MESSAGE);
        
        for(int i = 0 ; i < e.getStackTrace().length ; i++){
            error += e.getStackTrace()[i] + "\n";
        }
        
        FileWriter writer;
        try {
            writer = new FileWriter(archivoObj);
            writer.write(error);
            writer.close();
        } catch (IOException ex) {
            reportarError(e);
        }                                
    }
    
    protected void confirmarCerrado(){
        if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar?", 
                "Confirmación", JOptionPane.YES_NO_OPTION) == 0){
            setVisible(false);
            dispose();
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        if(isVisible())
            confirmarCerrado();        
    }
    
    public static float aFloat(String f, String significado){
        try{
            return Float.parseFloat(f.trim());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, f + " no es un valor correcto para " + significado);
            return -1f;
        }        
    }
    
    public static boolean checkFecha(String fecha){
        
        fecha = fecha.trim();
                                
        int axo;
        int mes;
        int dia;
        
        try{
            axo = Integer.parseInt(fecha.substring(0, 4));
            mes = Integer.parseInt(fecha.substring(5, 7));
            dia = Integer.parseInt(fecha.substring(8, 10));                        
            
            if(fecha.length() != 10)
                return false;
            if(mes < 1 || mes > 12)
                return false;
            if(dia < 1 || dia > 31)
                return false;
            if(fecha.charAt(4) != '/' & fecha.charAt(4) != '-')
                return false;
            if(fecha.charAt(7) != '/' & fecha.charAt(7) != '-')
                return false;
            
            return true;
        } catch(NumberFormatException e){
            return false;    
        }                
    }
    
    @Override
    public void windowOpened(WindowEvent e) {}    
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    
}
