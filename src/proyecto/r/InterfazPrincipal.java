/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

/**
 *
 * @author MCwar
 */
public class InterfazPrincipal extends Ventana{
                    
    private JLabel logo = new JLabel();
    
    private JPanel panelPrincipal;
    public PanelMenus panelMenus = new PanelMenus();    
    public PanelLateral panelLateral = new PanelLateral();   
    public PanelProductos panelProductos = new PanelProductos(); 
    
    public ArrayList <Producto> productosVenta = new ArrayList();
            
    private Dimension tamaxoVentana = new Dimension(300, 300);
    
    public Usuario usuarioActivo;
    
    public InterfazPrincipal(Usuario usuarioActivo){                
        
        this.usuarioActivo = usuarioActivo;
        
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            reportarError(ex);
        }
        
        addWindowListener(this);             
        setExtendedState(MAXIMIZED_BOTH);        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);                                            
        
        panelPrincipal = (JPanel) getContentPane();
        panelPrincipal.setLayout(new BorderLayout(15, 15));
        panelPrincipal.setBackground(new Color(0xfffc00));
        
        panelMenus.configurar(this);
        panelLateral.configurar(this, productosVenta);
        panelProductos.configurar(this);
        
        panelPrincipal.add(panelProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelLateral, BorderLayout.WEST);
        panelPrincipal.add(panelMenus, BorderLayout.NORTH);                                        
        
        panelLateral.actualizar();
        
        panelPrincipal.updateUI();

    }    
    
    private int buscarProducto(Producto ing){
        int i;      
        for(i = 0 ; i < productosVenta.size() ; i++){
            if(productosVenta.get(i).idProducto == ing.idProducto)
                break;
        }        
        return i;
    }
    
    public void añadirProducto(Producto ing){
        int index = buscarProducto(ing);                
        
        if(index != productosVenta.size()){
            productosVenta.get(index).cantidad++;
        }else{
            productosVenta.add(new Producto(ing));
            productosVenta.get(productosVenta.size() - 1).cantidad++;            
            panelLateral.tablaVenta.setSize(
                panelLateral.tablaVenta.getWidth(),
                panelLateral.tablaVenta.getHeight() + 30);
        }                     
        
        panelLateral.actualizar();                
    }
    
    public void disminuirProducto(Producto ing){
        int index = buscarProducto(ing);               
        
        if(index != productosVenta.size()){
            productosVenta.get(index).cantidad--;        
            if(productosVenta.get(index).cantidad == 0){
                productosVenta.remove(index);
            }                     
            
            panelLateral.actualizar();            
        }                                
    }
    
    public void eliminarProducto(Producto ing){
        int index = buscarProducto(ing);                
        
        if(index != productosVenta.size()){
            productosVenta.remove(index);  
            panelLateral.actualizar();            
        }
        
        
    }
    
}
