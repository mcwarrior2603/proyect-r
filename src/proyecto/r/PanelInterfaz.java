/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author MCwar
 */
public class PanelInterfaz extends JPanel{
    
    protected Color fondo = new Color(0x2aff00);        
    protected VentanaMainGUI gui;        
    
    public void configurar(VentanaMainGUI gui){
        this.gui = gui;
        
        setBackground(fondo);                
    }
    
}
