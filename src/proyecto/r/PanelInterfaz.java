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
    //en esta seccion se le da color al fondo de la ´pantalla           
    protected VentanaMainGUI gui;        
    /**
     * Super clase de los paneles de la interfaz principal;
     * @param gui Interfaz principal la cual contiene los paneles
     */
    public void configurar(VentanaMainGUI gui){        
        this.gui = gui;
        
        setBackground(gui.colorPanel);
    }        
}
