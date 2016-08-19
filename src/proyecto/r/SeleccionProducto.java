/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO FINAL
 */
public class SeleccionProducto extends JPanel {
   
    private final JLabel botonAñadir = new JLabel();
    private final JLabel botonEliminar = new JLabel();
    private final JLabel nombre = new JLabel();
    private final JLabel producto = new JLabel();
    private final String imagen ;
    private final JPanel botones = new JPanel();
    private final Dimension x = new Dimension(100,100);
    
    public SeleccionProducto(){
        
        setLayout( new BorderLayout());
        
        setPreferredSize(x);
        add(nombre,"North");
        add(producto,"Center");
        add(botones, "South");
        
        botones.add(botonAñadir,"West");
        botones.add(botonEliminar,"East");
        botones.setLayout(new BorderLayout());
        
        
        imagen = " ";
                
    }
    
    private void configurarLogo(){
        producto.setIcon(new ImageIcon(new ImageIcon("Productos/" + imagen).getImage()
                .getScaledInstance( 50, 50, Image.SCALE_DEFAULT)));    
        
    }
    
}
