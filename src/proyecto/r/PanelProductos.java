/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author MCwar
 */
public class PanelProductos extends PanelInterfaz{            
    
    Border borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), 
            "Productos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 30));
        
    private JPanel contenedorProductos = new JPanel();
    private JScrollPane scrollProductos;
    
    private static int numeroProductos;
    private static int numeroFilas;
    private static int numeroColumnas;
    
    private static ArrayList <Producto> productosRegistrados = new ArrayList();
    private static ArrayList <SeleccionProducto> botonesProductos = new ArrayList();
    
    @Override
    public void configurar(VentanaMainGUI gui){
        super.configurar(gui);
        setBorder(borde);
        setOpaque(false);
        setLayout(new BorderLayout());
        
        cargarProductos();                                                           
    }
    
    @Override
    public void configurarColores(Color fondo){
        setBackground(fondo);
        for (SeleccionProducto botonesProducto : botonesProductos) {
            botonesProducto.configurarColores(gui.colorBoton);
        }
    }
    
    /**
     * Actualiza los productos mostrados en el panel, mostrando
     * solamente aquellos en cuyo nombre se encuentre la subcadena
     * recibida.
     * @param buscar subcadena que se buscará en los nombres de los productos
     */
    public void actualizarPanel(String buscar){
                                
        numeroColumnas = Math.max(
                (int) (Math.floor((float)(gui.getWidth() * 0.70)/ 
                        (SeleccionProducto.maximoTamaxo.getWidth() + 40))),
                1);
        numeroFilas = Math.max(
                (int) (Math.ceil(((float) numeroProductos) / 
                        (float) numeroColumnas)),
                1);
        
        if(scrollProductos != null){
            remove(scrollProductos);
            scrollProductos = null;
        }        
        if(contenedorProductos != null){
            contenedorProductos.removeAll();            
        }            
                
        contenedorProductos = new JPanel();                
        contenedorProductos.setLayout(new GridLayout(numeroFilas, numeroColumnas, 10, 10));
        contenedorProductos.setOpaque(false);                               
        
        int productosAgregados = 0;
        
        for(int i = 0 ; i < numeroProductos ; i++){            
            if(i < productosRegistrados.size()){                                                           
                if(botonesProductos.get(i).productoActivo.nombre.
                        toLowerCase().contains(buscar.toLowerCase())){
                    contenedorProductos.add(botonesProductos.get(i));                                    
                    productosAgregados++;
                }                    
            }
        }                                
        for(int i = productosAgregados ; i < (numeroColumnas * numeroFilas) ; i++){
            contenedorProductos.add(new SeleccionProducto(null, null));
        }
        
        
        scrollProductos = new JScrollPane(contenedorProductos, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollProductos.getViewport().setOpaque(false);
        scrollProductos.setOpaque(false);
        scrollProductos.setBorder(new EmptyBorder(1, 1, 1, 1));
        scrollProductos.getVerticalScrollBar().setPreferredSize(new Dimension(50, 0));
        scrollProductos.getVerticalScrollBar().setUnitIncrement(15);
        
        add(scrollProductos);  
        updateUI();
    }
    
    /**
     * Método que manda llamar a su homónimo, pasando como parámetro 
     * una cadena vacía lo cual hará que e muestren todos los productos
     */
    public void actualizarPanel(){
        actualizarPanel("");
    }
    
    public void cargarProductos(){
        ResultSet query = SQLConnection.buscar("SELECT * FROM PRODUCTOS ORDER BY NOMBRE");
                
        for(int i = 0 ; i < productosRegistrados.size() ; i++){
            productosRegistrados.remove(i);
            botonesProductos.remove(i);
        }
        productosRegistrados = new ArrayList();
        botonesProductos = new ArrayList();
        
        if(query == null)
            return;
        try {
            while(query.next()){
                productosRegistrados.add(new Producto(
                        query.getInt("ID_PRODUCTO"),
                        query.getString("NOMBRE"),
                        query.getFloat("PRECIO"),
                        query.getInt("ID_CATEGORIA"),
                        query.getString("IMAGEN")));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }                
        
        for(int i = 0 ; i < productosRegistrados.size() ; i++){            
            botonesProductos.add(new SeleccionProducto(
                    productosRegistrados.get(i), gui));            
        }
        
        numeroProductos = Math.max(productosRegistrados.size(), 1);
        
        actualizarPanel();
    }
    
}
