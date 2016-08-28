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
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    public boolean cobrando = false;
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
    
    @Override
    public void windowClosing(WindowEvent e){
        if(isVisible()){
            if(JOptionPane.showConfirmDialog(null, "¿Confirmar cerrado?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0)
                System.exit(0);
        }
    }
    
    public void cargarProductos(){
        panelProductos.cargarProductos();
    }
    
    public boolean guardarVenta(float total){                
                        
        Calendar fecha = Calendar.getInstance();                
        int id;
        
        String sql = "INSERT INTO VENTAS(ID_USUARIO, FECHA, HORA, TOTAL)"
                + "VALUES("
                + usuarioActivo.id + ","
                + "'" + hoy() + "', "
                + "'" + fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE) + "',"
                + total 
                + ")";
        
        if(!SQLConnection.actualizar(sql))
            return false;
        
        ResultSet ultimoId = SQLConnection.buscar("SELECT * FROM VENTAS ORDER BY ID_VENTA DESC LIMIT 1");
        try {
            ultimoId.next();
            id = ultimoId.getInt("ID_VENTA");
        } catch (SQLException ex) {
            reportarError(ex);
            return false;
        }        
        
        for(int i = 0 ; i < productosVenta.size() ; i++){
            sql = "INSERT INTO PRODUCTOS_VENTAS(ID_PRODUCTO, ID_VENTA, CANTIDAD)"
                    + "VALUES("
                    + productosVenta.get(i).idProducto + ","
                    + id + ","
                    + productosVenta.get(i).cantidad + ")";
            
            if(!SQLConnection.actualizar(sql))
                return false;
        }
            
        return true;
    }
    
    public void limpiarVenta(){
        productosVenta.clear();
        panelLateral.actualizar();
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
