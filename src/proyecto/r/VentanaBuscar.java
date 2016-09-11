/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaBuscar extends Ventana{
    
    private static Dimension dimensionVentana;
    
    //private final Dimension x = new Dimension(500,600);
    
    private final JLabel producto = new JLabel("Nombre :");
    private final JButton botonBuscar = new JButton("Buscar");
    private final JTextField nombreText = new JTextField ();
    
    private JPanel panelPrincipal;
    private VentanaMainGUI gui;    
    
    
    public VentanaBuscar(){
        
        this.gui = gui;
        
       dimensionVentana = new Dimension((int)(gui.getWidth() / 4), gui.getHeight());
        
        setLayout(null);
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        
        panelPrincipal = (JPanel) getContentPane();        
        
        addWindowListener(this);
        
        producto.setBounds( 30, 30, 100, 30);
        nombreText.setBounds( 100, 30, 250, 30);
        botonBuscar.setBounds( 370, 30, 100, 30);
        
        panelPrincipal.add(producto);
        panelPrincipal.add(botonBuscar);
        panelPrincipal.add(nombreText);
        
        
        
    }
    
}
