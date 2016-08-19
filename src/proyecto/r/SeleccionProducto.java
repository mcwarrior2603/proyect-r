/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author USUARIO FINAL
 */
public class SeleccionProducto extends JPanel implements MouseListener{
    
   
    private final JLabel botonDisminuir = new JLabel();
    private final JLabel botonEliminar = new JLabel();    
    private final JLabel precio = new JLabel();
    private final JLabel imagen = new JLabel();       
        
    private final JPanel botones = new JPanel();
    private final JPanel panelImagen = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelPrecio = new JPanel(new FlowLayout(FlowLayout.CENTER));    
    private final JPanel panelDisminuir = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelEliminar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    private final Dimension x = new Dimension(100,100);
    private final Producto productoActivo;
    
    private final Border bordeDefault = BorderFactory.createRaisedBevelBorder();
    
    public SeleccionProducto(Producto param){
    
        setLayout(new BorderLayout(5, 5));
        setBorder(bordeDefault);
        setBackground(new Color(0xFAD12E));
        
        this.productoActivo = param;
        
        precio.setText("$" + productoActivo.precio);        
        precio.setFont(new Font("Arial", Font.BOLD, 17));        
        
        panelPrecio.add(precio);                        
        panelEliminar.add(botonEliminar);
        panelDisminuir.add(botonDisminuir);  
        panelImagen.add(imagen);
        
        panelPrecio.setOpaque(false);
        panelEliminar.setOpaque(false);
        panelDisminuir.setOpaque(false);
        panelImagen.setOpaque(false);
        botones.setOpaque(false);
        
        setSize(x);
        add(panelPrecio, "North");
        add(panelImagen, "Center");
        add(botones, "South");
        
        botones.setLayout(new BorderLayout());
        botones.add(panelDisminuir,"West");
        botones.add(panelEliminar,"East");        
        
        addMouseListener(this);        
        botonDisminuir.addMouseListener(this);
        botonEliminar.addMouseListener(this);        
        
        configurarImagenes();
                
    }
    
    private void configurarImagenes(){
        imagen.setIcon(new ImageIcon(new ImageIcon("Productos/" + productoActivo.imagen).getImage()
                .getScaledInstance( 100, 100, Image.SCALE_DEFAULT))); 
        botonEliminar.setIcon(new ImageIcon(new ImageIcon("multimedia/cancel.png").getImage().
                getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
        botonDisminuir.setIcon(new ImageIcon(new ImageIcon("multimedia/minus.png").getImage().
                getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this)
            System.out.println("panel");
        if(e.getSource() == botonDisminuir)
            System.out.println("Disminuir");
        if(e.getSource() == botonEliminar)
            System.out.println("Eliminar");
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
