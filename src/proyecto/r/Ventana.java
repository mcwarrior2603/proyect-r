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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author MCwar
 */
public class Ventana extends JFrame implements WindowListener{

    protected static Font fontTitulo = new Font("Arial", Font.BOLD, 20);
    
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
        for(int i = 0 ; i < e.getStackTrace().length ; i++){
            error += e.getStackTrace()[i] + "\n";
        }
        
        JOptionPane.showMessageDialog(null, error, "¡Error!", JOptionPane.ERROR_MESSAGE);
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
