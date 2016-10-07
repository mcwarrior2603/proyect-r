/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import com.sun.scenario.effect.Color4f;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author mcwarrior
 */
public class VentanaConfiguracion extends Ventana implements ActionListener{
    
    public static final int CONFIGURACION_INICIAL = 0;
    public static final int CONFIGURACION_COMUN = 1;
    
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
    private final JButton buttonGuardar = new JButton("Guardar");
    private final JButton buttonCancelar = new JButton("Cancelar");
    
    private final JTextField fieldLogotipo = new JTextField();
    private final JTextField fieldTiempo = new JTextField();
        
    private JSeparator separador1 = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator separador2 = new JSeparator(JSeparator.HORIZONTAL);
                    
    private JPanel mainPanel;
    
    private VentanaMainGUI gui;
    
    public VentanaConfiguracion(VentanaMainGUI gui, int uso){
        super(500, 500, NOMBRE_SW + " - Configuración");
                
        configurarComponentes(gui, uso);
        
        if(gui.ventConfiguracion != null){
            gui.ventConfiguracion.cerrar();
        }
        gui.ventConfiguracion = this;
        
    }
    
    private void configurarComponentes(VentanaMainGUI gui, int uso){
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
        buttonGuardar.setBounds(250, 250, 100, 30);
        buttonCancelar.setBounds(355, 250, 100, 30);
        
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
        mainPanel.add(buttonGuardar);
        mainPanel.add(buttonCancelar);
        
        buttonCambiarFondo.addActionListener(this);
        buttonCambiarPanel.addActionListener(this);
        buttonCambiarBoton.addActionListener(this);
        buttonGuardar.addActionListener(this);
        buttonCancelar.addActionListener(this);

        if(uso != CONFIGURACION_INICIAL){
            gui.cargarConfiguracion();
            labelMuestraFondo.setBackground(gui.colorFondo);
            labelMuestraPanel.setBackground(gui.colorPanel);
            labelMuestraBoton.setBackground(gui.colorBoton);
            fieldLogotipo.setText(gui.logotipo);
            fieldTiempo.setText(String.valueOf(gui.minutosRecordatorio));
        }
        
                
    }

    private void guardarConfiguracion(){
            
        PrintWriter writer;
        
        gui.logotipo = fieldLogotipo.getText().trim();
        gui.minutosRecordatorio = aInteger(fieldTiempo.getText());
        
        if(gui.minutosRecordatorio == DEFAULT_AINTEGER){
            JOptionPane.showMessageDialog(null, 
                    "El numero de minutos no es un valor correcto.\n"
                            + "No es posible guardar la configuración");
        }
        
        try {
                                         
            writer = new PrintWriter(gui.archivoConfiguracion);
            
            gui.archivoConfiguracion.createNewFile();
            
            writer.println(gui.colorFondo.getRed());
            writer.println(gui.colorFondo.getGreen());
            writer.println(gui.colorFondo.getBlue());
            
            writer.println(gui.colorPanel.getRed());
            writer.println(gui.colorPanel.getGreen());            
            writer.println(gui.colorPanel.getBlue()); 
            
            writer.println(gui.colorBoton.getRed());
            writer.println(gui.colorBoton.getGreen());
            writer.println(gui.colorBoton.getBlue());
            
            writer.println(gui.logotipo);
            
            writer.println(gui.minutosRecordatorio);
            
            writer.close();
            gui.cargarConfiguracion();
            cerrar();
            
        } catch (IOException ex) {
            reportarError(ex);
        } catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Configuración no válida, verifique.");
        }
    }
    
    @Override
    public void cerrar(){
        super.cerrar();
        gui.ventConfiguracion = null;
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
        }else if(e.getSource() == buttonGuardar){
            guardarConfiguracion();
            gui.cargarConfiguracion();
            gui.repaint();
        }else if(e.getSource() == buttonCancelar){
            confirmarCerrado();
        }
    }        
    
    
}
