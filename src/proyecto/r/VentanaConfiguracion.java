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
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    
    private final JLabel labelTurnos = new JLabel("Turnos"); 
    private final JLabel labelnumturn = new JLabel("#Turnos");
    private final JLabel labelturn = new JLabel("Turno");
    private final JLabel labelHora = new JLabel("Hora inicio (24H)");
    private final JLabel labelturn1 = new JLabel("1");
    private final JLabel labelturn2 = new JLabel("2");
    private final JLabel labelturn3 = new JLabel("3");
    
    private final JRadioButton turno1 = new JRadioButton("1");
    private final JRadioButton turno2 = new JRadioButton("2");
    private final JRadioButton turno3 = new JRadioButton("3");
    private final ButtonGroup groupTurnos = new ButtonGroup();
    
    private final JLabel labelTiempo = new JLabel("Tiempo de recordatorio(min)");   
    private final JButton buttonCambiarFondo = new JButton("Cambiar");
    private final JButton buttonCambiarPanel = new JButton("Cambiar");
    private final JButton buttonCambiarBoton = new JButton("Cambiar");
    private final JButton buttonGuardar = new JButton("Guardar");
    private final JButton buttonCancelar = new JButton("Cancelar");
    
    private final JTextField fieldLogotipo = new JTextField();
    private final JTextField fieldTiempo = new JTextField();
    private final JTextField fieldTurno1 = new JTextField();
    private final JTextField fieldTurno1m = new JTextField();
    private final JTextField fieldTurno2 = new JTextField();
    private final JTextField fieldTurno2m = new JTextField();
    private final JTextField fieldTurno3 = new JTextField();
    private final JTextField fieldTurno3m = new JTextField();
    private int horas[] = new int[3];
    private int minutos[] = new int[3];
    private int temporalNumTurnos = 1;
    
    private JSeparator separador1 = new JSeparator(JSeparator.HORIZONTAL);
    private JSeparator separador2 = new JSeparator(JSeparator.HORIZONTAL);
                            
    private JPanel mainPanel;
    
    private VentanaMainGUI gui;
    
    public VentanaConfiguracion(VentanaMainGUI gui, int uso){
        super(500, 600, NOMBRE_SW + " - Configuración");
                
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
        labelTurnos.setBounds(15, 205, 220, 30);
        labelnumturn.setBounds(15, 245, 100, 30);
        turno1.setBounds(85, 245, 50, 30);
        turno2.setBounds(135, 245, 50, 30);
        turno3.setBounds(190, 245, 100, 30);
        labelturn.setBounds(30, 285, 100, 30);
        labelHora.setBounds(105, 285, 100, 30);
        labelturn1.setBounds(50, 325, 100, 30);
        fieldTurno1.setBounds(100, 325, 40, 30);
        fieldTurno1m.setBounds(150, 325, 40, 30);
        labelturn2.setBounds(50, 365, 100, 30);
        fieldTurno2.setBounds(100, 365, 40, 30);
        fieldTurno2m.setBounds(150, 365, 40, 30);
        labelturn3.setBounds(50, 405, 100, 30);
        fieldTurno3.setBounds(100, 405, 40, 30);
        fieldTurno3m.setBounds(150, 405, 40, 30);
        labelTiempo.setBounds(50, 455, 250, 30);
        fieldTiempo.setBounds(250, 455, 100, 30);
        buttonGuardar.setBounds(230, 515, 100, 30);
        buttonCancelar.setBounds(360, 515, 100, 30);
        
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
        mainPanel.add(labelTurnos);
        mainPanel.add(labelnumturn);
        mainPanel.add(turno1);
        mainPanel.add(turno2);
        mainPanel.add(turno3);
        mainPanel.add(labelturn);
        mainPanel.add(labelHora);
        mainPanel.add(labelturn1);
        mainPanel.add(fieldTurno1);
        mainPanel.add(fieldTurno1m);
        mainPanel.add(labelturn2);
        mainPanel.add(fieldTurno2);
        mainPanel.add(fieldTurno2m);
        mainPanel.add(labelturn3);
        mainPanel.add(fieldTurno3);
        mainPanel.add(fieldTurno3m);
        mainPanel.add(labelTiempo);
        mainPanel.add(fieldTiempo);
        mainPanel.add(buttonGuardar);
        mainPanel.add(buttonCancelar);
        
        groupTurnos.add(turno1);
        groupTurnos.add(turno2);
        groupTurnos.add(turno3);        
                
        buttonCambiarFondo.addActionListener(this);
        buttonCambiarPanel.addActionListener(this);
        buttonCambiarBoton.addActionListener(this);
        buttonGuardar.addActionListener(this);
        buttonCancelar.addActionListener(this);

        turno1.addActionListener(this);
        turno2.addActionListener(this);
        turno3.addActionListener(this);
        
        if(uso != CONFIGURACION_INICIAL){            
            labelMuestraFondo.setBackground(gui.colorFondo);
            labelMuestraPanel.setBackground(gui.colorPanel);
            labelMuestraBoton.setBackground(gui.colorBoton);
            fieldLogotipo.setText(gui.logotipo);
            fieldTiempo.setText(String.valueOf(gui.minutosRecordatorio));
            if(gui.numTurnos >= 1){
                fieldTurno1.setText(String.valueOf(gui.horaInicioTurno[0]));
                fieldTurno1m.setText(String.valueOf(gui.minutoInicioTurno[0]));
            }
            if(gui.numTurnos >= 2){
                fieldTurno2.setText(String.valueOf(gui.horaInicioTurno[1]));
                fieldTurno2m.setText(String.valueOf(gui.minutoInicioTurno[1]));                
            }
            if(gui.numTurnos >= 3){
                fieldTurno3.setText(String.valueOf(gui.horaInicioTurno[2]));
                fieldTurno3m.setText(String.valueOf(gui.minutoInicioTurno[2]));
            }
            temporalNumTurnos = gui.numTurnos;
            setEditableTurnos(temporalNumTurnos, true);
            
            if(temporalNumTurnos == 1){                
                turno1.setSelected(true);                
            }else if(temporalNumTurnos == 2){
                turno2.setSelected(true);
            }else if(temporalNumTurnos == 3){
                turno3.setSelected(true);
            }
            
        }
        
                
    }

    private boolean checkTurnos(){        
        boolean valido = true;
                
        if(temporalNumTurnos >= 1){
            horas[0] = aInteger(fieldTurno1.getText());
            minutos[0] = aInteger(fieldTurno1m.getText());
        }
        if(temporalNumTurnos >= 2){
            horas[1] = aInteger(fieldTurno2.getText());
            minutos[1] = aInteger(fieldTurno2m.getText());
        }
        if(temporalNumTurnos >= 3){
            horas[2] = aInteger(fieldTurno3.getText());                        
            minutos[2] = aInteger(fieldTurno3m.getText());
        }                        
        
        for(int i = 0 ; (i < temporalNumTurnos) && (valido) ; i++){
            if(horas[i] == DEFAULT_AINTEGER)
                valido = false;
        }
        
        for(int i = 0 ; (i < temporalNumTurnos) && (valido) ; i++){
            if(horas[i] < 0 
                    || horas[i] > 23
                    || minutos[i] < 0 
                    || minutos[i] > 59)
                valido = false;
        }
        
        for(int i = 0 ; (i < temporalNumTurnos) && (valido) ; i++){
            if(minutos[i] == DEFAULT_AINTEGER)
                valido = false;
        }
        
        Calendar turnos[] = new Calendar[3];
        
        for(int i = 0 ; i < temporalNumTurnos ; i++){
            turnos[i] = Calendar.getInstance();
            turnos[i].set(Calendar.HOUR, horas[i]);
            turnos[i].set(Calendar.MINUTE, minutos[i]);
        }
        
        for(int i = 0 ; (i < temporalNumTurnos - 1) && (valido) ; i++){
            if(turnos[i].compareTo(turnos[i + 1]) >= 0){
                valido = false;
            }
        }
        
        return valido;     
    }
    
    private boolean guardarConfiguracion(){
            
        PrintWriter writer;
        
        gui.logotipo = fieldLogotipo.getText().trim();
        gui.minutosRecordatorio = aInteger(fieldTiempo.getText());
        
        if(!checkTurnos()){
            JOptionPane.showMessageDialog(null, 
                    "Las horas indicadas para los turnos no son válidas\n"
                            + "Recuerde que:\n"
                            + "- Deben estar en formato de 24 horas\n"
                            + "- Las horas deben ir en orden ascendente, \n"
                            + "\tpor ejemplo: 7:00 - 12:00 - 18:00");
            return false;
        }
        
        if(gui.minutosRecordatorio == DEFAULT_AINTEGER){
            JOptionPane.showMessageDialog(null, 
                    "El numero de minutos no es un valor correcto.\n"
                            + "No es posible guardar la configuración");
            return false;
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
                        
            writer.println(temporalNumTurnos);
            
            for(int i = 0 ; i < temporalNumTurnos ; i++){
                writer.println(horas[i]);
                writer.println(minutos[i]);
            }
            
            writer.close();
            gui.cargarConfiguracion(); 
            JOptionPane.showMessageDialog(null, 
                    "Configuración guardada correctamente");
            cerrar();
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }                
    }
            
    private void setEditableTurnos(int numTurnos, boolean editable){
        fieldTurno1.setEditable(!editable);
        fieldTurno2.setEditable(!editable);
        fieldTurno3.setEditable(!editable);
        fieldTurno1m.setEditable(!editable);
        fieldTurno2m.setEditable(!editable);
        fieldTurno3m.setEditable(!editable);
        
        if(numTurnos >= 1){
            fieldTurno1.setEditable(editable);
            fieldTurno1m.setEditable(editable);
        }
        if(numTurnos >= 2){
            fieldTurno2.setEditable(editable);
            fieldTurno2m.setEditable(editable);            
        }
        if(numTurnos >= 3){
            fieldTurno3.setEditable(editable);
            fieldTurno3m.setEditable(editable);
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
            if(!guardarConfiguracion())
                return;            
            gui.repaint();
        }else if(e.getSource() == buttonCancelar){
            if(confirmarCerrado())
                cerrar();
        }else if(e.getSource() == turno1){
            temporalNumTurnos = 1;
            setEditableTurnos(1, true);
        }else if(e.getSource() == turno2){
            temporalNumTurnos = 2;
            setEditableTurnos(2, true);
        }else if(e.getSource() == turno3){
            temporalNumTurnos = 3;
            setEditableTurnos(3, true);
        }
    }                       
}