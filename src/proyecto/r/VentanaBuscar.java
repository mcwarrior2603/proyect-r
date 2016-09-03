/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author MCwar
 */
public class VentanaBuscar extends Ventana{
    
    private static Dimension dimensionVentana;
    
    private JPanel panelPrincipal;
    private VentanaMainGUI gui;    
    
    public VentanaBuscar(VentanaMainGUI gui){
        
        this.gui = gui;
        
        dimensionVentana = new Dimension((int)(gui.getWidth() / 4), gui.getHeight());
        
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        panelPrincipal = (JPanel) getContentPane();        
        
        addWindowListener(this);
        
        
    }
    
}
