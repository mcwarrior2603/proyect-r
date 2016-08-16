/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author MCwar
 */
public class PanelProductos extends PanelInterfaz{            
    
    Border borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true), 
            "Productos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 30));
    
    @Override
    public void configurar(InterfazPrincipal gui){
        super.configurar(gui);
        setBorder(borde);
        setOpaque(false);
    }
    
}
