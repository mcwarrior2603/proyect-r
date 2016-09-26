/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author mcwarrior
 */
public class VentanaConfiguracion extends Ventana implements ActionListener{
    
    private final JLabel labelTitulo = new JLabel("Configuración");
    private final JLabel labelColorFondo = new JLabel("Color de fondo");
    private final JLabel labelColorPanel = new JLabel("Color de los páneles");
    private final JLabel labelColorBoton = new JLabel("Color de los botones");
    private final JLabel labelMuestraFondo = new JLabel("Muestra");
    private final JLabel labelMuestraPanel = new JLabel("Muestra");
    private final JLabel labelMuestraBoton = new JLabel("Muestra");
    private final JLabel labelLogotipo = new JLabel("Logotipo");
    private final JLabel labelTiempo = new JLabel("Tiempo de recordatorio(min)");
    
    private final JButton buttonCambiarFondo = new JButton("Cambiar");
    private final JButton buttonCambiarPanel = new JButton("Cambiar");
    private final JButton buttonCambiarBoton = new JButton("Cambiar");
    
    private final JTextField fieldLogotipo = new JTextField();
    private final JTextField fieldTiempo = new JTextField();
        
    private JSeparator separador1 = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator separador2 = new JSeparator(JSeparator.HORIZONTAL);
                    
    private JPanel mainPanel;
    
    private VentanaMainGUI gui;
    
    public VentanaConfiguracion(VentanaMainGUI gui){
        super(500, 500);
                
        configurarComponentes(gui);
        
    }
    
    private void configurarComponentes(VentanaMainGUI gui){
        this.gui = gui;
        
        mainPanel = (JPanel) getContentPane();
        
        labelTitulo.setFont(fontTitulo);        
        
        labelTitulo.setBounds(25, 5, 200, 30);
        labelColorFondo.setBounds(15, 40, 150, 30);
        labelMuestraFondo.setBounds(170, 40, 100, 30);
        buttonCambiarFondo.setBounds(275, 40, 150, 30);
        labelColorPanel.setBounds(15, 75, 150, 30);
        labelMuestraPanel.setBounds(170, 75, 100, 30);
        buttonCambiarPanel.setBounds(275, 75, 150, 30);
        labelColorBoton.setBounds(15, 110, 150, 30);
        labelMuestraBoton.setBounds(170, 110, 100, 30);
        buttonCambiarBoton.setBounds(275, 110, 150, 30);
        separador1.setBounds(15, 150, 450, 3);        
        labelLogotipo.setBounds(15, 160, 100, 30);
        fieldLogotipo.setBounds(120, 160, 200, 30);
        separador2.setBounds(15, 200, 450, 3);
        labelTiempo.setBounds(15, 205, 220, 30);
        fieldTiempo.setBounds(240, 205, 100, 30);
                
        labelMuestraFondo.setOpaque(true);
        labelMuestraFondo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        labelMuestraPanel.setOpaque(true);
        labelMuestraPanel.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        labelMuestraBoton.setOpaque(true);
        labelMuestraBoton.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        
        mainPanel.add(labelTitulo);
        mainPanel.add(labelColorFondo);
        mainPanel.add(labelMuestraFondo);
        mainPanel.add(buttonCambiarFondo);
        mainPanel.add(labelColorPanel);
        mainPanel.add(labelMuestraPanel);
        mainPanel.add(buttonCambiarPanel);
        mainPanel.add(labelColorBoton);
        mainPanel.add(labelMuestraBoton);
        mainPanel.add(buttonCambiarBoton);
        mainPanel.add(separador1);
        mainPanel.add(labelLogotipo);
        mainPanel.add(fieldLogotipo);
        mainPanel.add(separador2);
        mainPanel.add(labelTiempo);
        mainPanel.add(fieldTiempo);
        
        buttonCambiarFondo.addActionListener(this);
        buttonCambiarPanel.addActionListener(this);
        buttonCambiarBoton.addActionListener(this);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {               
        
        if(e.getSource() == buttonCambiarFondo){                        
            gui.colorFondo = JColorChooser.showDialog(null, "Color para el fondo", gui.colorFondo);           
            labelMuestraFondo.setBackground(gui.colorFondo);            
        }else if(e.getSource() == buttonCambiarPanel){
            gui.colorPanel = JColorChooser.showDialog(null, "Color para paneles", gui.colorPanel);
            labelMuestraPanel.setBackground(gui.colorPanel);
        }else if(e.getSource() == buttonCambiarBoton){
            gui.colorBoton = JColorChooser.showDialog(null, "Color para botones", gui.colorBoton);
            labelMuestraBoton.setBackground(gui.colorBoton);
        }
    }
    
   
    
}
