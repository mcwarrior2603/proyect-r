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
public class VentanaCorteDeCaja extends Ventana{        
    
    private static final Dimension dimensionVentana = new Dimension(600, 400);
    
    private JPanel mainPanel;
    
    
    public VentanaCorteDeCaja(){
    
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        mainPanel = (JPanel) getContentPane();
        
        addWindowListener(this);
        
    }
    
}
