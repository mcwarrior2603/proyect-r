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
    
    private static int numeroFilas = 20;
    
    private static ArrayList <Producto> productosRegistrados = new ArrayList();
    
    @Override
    public void configurar(InterfazPrincipal gui){
        super.configurar(gui);
        setBorder(borde);
        setOpaque(false);
        setLayout(new BorderLayout());
        
        cargarProductos();        
        
        contenedorProductos.setLayout(new GridLayout(numeroFilas, 5, 10, 10));
        contenedorProductos.setOpaque(false);
        
        for(int i = 0 ; i < 100 ; i++){
            if(i < productosRegistrados.size())
                contenedorProductos.add(new SeleccionProducto(productosRegistrados.get(i), gui));
            else
                contenedorProductos.add(new SeleccionProducto(null, null));
        }
         
        scrollProductos = new JScrollPane(contenedorProductos, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductos.getViewport().setOpaque(false);
        scrollProductos.setOpaque(false);
        scrollProductos.setBorder(new EmptyBorder(1, 1, 1, 1));
        
        add(scrollProductos);                     
    }
    
    private static void cargarProductos(){
        ResultSet query = SQLConnection.buscar("SELECT * FROM PRODUCTOS");
        
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
    }
    
}
