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
    
    private static int numeroFilas = 100;
    
    @Override
    public void configurar(InterfazPrincipal gui){
        super.configurar(gui);
        setBorder(borde);
        setOpaque(false);
        setLayout(new BorderLayout());
        
        contenedorProductos.setLayout(new GridLayout(numeroFilas, 10, 10, 10));
        contenedorProductos.setOpaque(false);
        
        String texto = "...........";
        
        for(int i = 0 ; i < (10 * numeroFilas) ; i++){
            contenedorProductos.add(new SeleccionProducto(new Producto(1, "Papa", 1, -1, "papa.jpg")));
        }
         
        scrollProductos = new JScrollPane(contenedorProductos, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductos.getViewport().setOpaque(false);
        scrollProductos.setOpaque(false);
        scrollProductos.setBorder(new EmptyBorder(1, 1, 1, 1));
        
        add(scrollProductos);     
        
        gui.productosVenta.add(new Producto(1, "Papas", 1f, -1, "papa.jpg"));
        gui.productosVenta.get(0).cantidad++;        
    }
    
}
