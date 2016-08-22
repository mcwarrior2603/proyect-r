/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO FINAL
 */
public class ReporteVentas extends Ventana implements WindowListener {
    
    private final Dimension ventana = new Dimension(400,200);
    private final JLabel titulo = new JLabel();
    private final JLabel alcance = new JLabel();
    private final TextField fechaInicio = new TextField();
    private final TextField fechaTerminal = new TextField();
    private final JButton generador = new JButton();
    private JPanel todo = new JPanel();
    //private JPanel encabezado = new JPanel();
   // private JPanel pie = new JPanel();
    
    
    public ReporteVentas(){
        
        addWindowListener(this);
        setLayout(null);
        setPreferredSize(ventana);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
        
        todo = (JPanel) getContentPane();
        
        todo.add(titulo);
        todo.add(generador);
        
        titulo.setText("Ventas");
        alcance.setText("al");
        generador.setText("Generar Repeorte");
        
       // todo.add(encabezado, "North");
        //todo.add(pie, "South");
        
       // pie.add(generador, "West");
        
       // encabezado.add(titulo);
       // encabezado.add(alcance);
       // encabezado.add(fechaInicio);
       // encabezado.add(fechaTerminal);
        
        fechaInicio.setBounds(30, 30,100,20);
        fechaTerminal.setBounds(50, 30,100,20);
        fechaInicio.setEnabled(true);
        fechaTerminal.setEnabled(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    //To change body of generated methods, choose Tools | Templates.
    }
}
