/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MCwar
 */
public class Ventana extends JFrame implements WindowListener, KeyListener{
    
    public static final String NOMBRE_SW = "Puffy PV";
    public static final String version = "1.5.3";
    public static final String fechaVersion = "07/10/2016";
    
    protected static Font fontTitulo = new Font("Arial", Font.BOLD, 20);
    public static final float DEFAULT_AFLOAT = -1.7932f;
    public static final int DEFAULT_AINTEGER = -17898;
    public static final String LOGO_DEV = "anima_dev.png";
            
    protected Dimension dimensionVentana;        
                        
    private JDialog ventanaCarga;        

    public Ventana(int width, int height, String title){                                                
        dimensionVentana = new Dimension(width, height);
                
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        ((JPanel)getContentPane()).setLayout(null);
        ((JPanel)getContentPane()).addKeyListener(this);        
        
        setPreferredSize(dimensionVentana);
        pack();        
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle(title);
        
        addKeyListener(this);
        addWindowListener(this);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }                
                        
    public void abrirVentanaCarga(){
        
        JLabel iconoCargando = new JLabel();
        
        iconoCargando.setIcon(new ImageIcon(
                new ImageIcon("multimedia/cargando.gif").getImage().
                        getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
           
        ventanaCarga = new JDialog();
        
        (ventanaCarga.getContentPane()).add(iconoCargando);
        ventanaCarga.setUndecorated(true);
        ventanaCarga.setPreferredSize(new Dimension(250, 250));
        ventanaCarga.pack();
        ventanaCarga.setAlwaysOnTop(true);           
        ventanaCarga.setLocationRelativeTo(null);        
        ventanaCarga.setVisible(true);                                                         
                
        ((JPanel)ventanaCarga.getContentPane()).updateUI();                             
    }
        
    public void cerrarVentanaCarga(){
        
        if(ventanaCarga == null)
            return;
        
        ventanaCarga.setVisible(false);
        ventanaCarga.dispose();
        ventanaCarga = null;        
            
            
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
         
    protected boolean confirmarCerrado(){
        if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar?", 
                "Confirmación", JOptionPane.YES_NO_OPTION) == 0)
            return true;
        return false;       
    }

    public void cerrar(){        
        setVisible(false);
        dispose();        
    }

    public static float aFloat(String f, String significado){
        try{
            return Float.parseFloat(f.trim());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, f + " no es un valor correcto para " + significado);
            return DEFAULT_AFLOAT;
        }        
    }
    
    public static int aInteger(String i){
        try{
            return Integer.parseInt(i.trim());
        }catch(Exception e){            
            return DEFAULT_AINTEGER;
        }
    }

    public static boolean checkFecha(String fecha){
        
        fecha = fecha.trim();
                                
        if(fecha.isEmpty()){
            return false;
        }
        
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
        } catch(StringIndexOutOfBoundsException e){
            return false;
        }
    }

    public static String hoy(){
        return hoy("/");
    }

    public static String hoy(String separador){
        Calendar fecha = Calendar.getInstance();
        String ret = "";
        
        ret += fecha.get(Calendar.YEAR) + separador;
        ret += (fecha.get(Calendar.MONTH) + 1) + separador;
        ret += fecha.get(Calendar.DAY_OF_MONTH);
        
        return ret;        
    }
    
    public static String horaNow(){
        Calendar hora = Calendar.getInstance();
        
        String ret = "";
        ret += hora.get(Calendar.HOUR_OF_DAY) + ":";
        ret += hora.get(Calendar.MINUTE) + ":";
        ret += hora.get(Calendar.SECOND);
        
        return ret;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {        
        if(e.getKeyCode() == KeyEvent.VK_F4){
            System.out.println("F4"); 
        }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            confirmarCerrado();
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
    @Override
    public void keyTyped(KeyEvent e) {}    
    @Override
    public void keyReleased(KeyEvent e) {}   
        
}
