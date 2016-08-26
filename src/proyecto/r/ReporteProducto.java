/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author MCwar
 */
public class ReporteProducto extends Ventana{
    
    private static final Dimension dimensionVentana = new Dimension(600, 400);
    
    private JRadioButton rbHoy = new JRadioButton("Hoy");
    private JRadioButton rbPeriodo = new JRadioButton("Periodo");
//    private ButtonGroup
    
    public ReporteProducto(){
        setPreferredSize(dimensionVentana);        
        pack();        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addWindowListener(this);
        
    }            
    
}
