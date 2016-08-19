/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO FINAL
 */
public class SeleccionProducto extends JPanel implements MouseListener{
    
   
    private final JLabel botonDisminuir = new JLabel();
    private final JLabel botonEliminar = new JLabel();
    private final JLabel nombre = new JLabel();
    private final JLabel producto = new JLabel();
   // private final String imagen ;
    private final JPanel botones = new JPanel();
    private final Dimension x = new Dimension(100,100);
    private final Producto a;
    
    
    public SeleccionProducto( Producto a){
        
       
    
        this.a = a;
        
        addMouseListener(this);
        setLayout( new BorderLayout());
        
        setPreferredSize(x);
        add(nombre,"North");
        add(producto,"Center");
        add(botones, "South");
        
        botones.add(botonDisminuir,"West");
        botones.add(botonEliminar,"East");
        botones.setLayout(new BorderLayout());
        
        botonDisminuir.addMouseListener(this);
        botonEliminar.addMouseListener(this);
        
       // imagen = " ";
        
        
    }
    
    private void configurarLogo(){
        producto.setIcon(new ImageIcon(new ImageIcon("Productos/" + ).getImage()
                .getScaledInstance( 50, 50, Image.SCALE_DEFAULT)));    
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
