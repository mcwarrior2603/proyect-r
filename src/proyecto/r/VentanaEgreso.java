/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaEgreso extends Ventana implements ActionListener{
        
    public static final int AÑADIR = 0;
    public static final int VER = 1;
    
    private JLabel labelTitulo = new JLabel("Egreso");
    private JLabel labelFecha = new JLabel("Fecha:");
    private JLabel labelMonto = new JLabel("Monto:");
    private JLabel labelConcepto = new JLabel("Concepto:");
    private JLabel labelUsuario = new JLabel("Usuario:");    
    
    private JTextField fieldFecha = new JTextField();
    private JTextField fieldMonto = new JTextField();
    private JTextArea fieldConcepto = new JTextArea();
    private JTextField fieldUsuario = new JTextField();    
    
    private JButton buttonGuardar = new JButton("Guardar");
    private JButton buttonCerrar = new JButton("Cerrar");
    private JButton buttonEliminar = new JButton("Eliminar [X]");
    
    private JPanel mainPanel;
        
    private VentanaMainGUI gui;
    private Egreso eg;
    private int uso;
    
    public VentanaEgreso(int uso, VentanaMainGUI gui){        
        super(400, 400, NOMBRE_SW + " - Egresos");
                               
        configurar(uso, gui);
    }
    
    public VentanaEgreso(int uso, VentanaMainGUI gui, Egreso eg){
        super(400, 400, NOMBRE_SW + " - Egresos");        
        
        this.eg = eg;                        
        configurar(uso, gui);
    }
    
    private void configurar(int uso, VentanaMainGUI gui){
                
        this.gui = gui;
        this.uso = uso;
        
        if(gui.ventEgreso != null)
            gui.ventEgreso.cerrar();
        gui.ventEgreso = this;
        
        buttonEliminar.addActionListener(this);
        buttonGuardar.addActionListener(this);
        buttonCerrar.addActionListener(this);                            
        
        mainPanel = (JPanel) getContentPane();
               
        configurarComponentes();
        configurarUso();
        
    }
    
    private void configurarComponentes(){        
        labelTitulo.setFont(fontTitulo);
        
        labelTitulo.setBounds(15, 15, 100, 50);
        labelFecha.setBounds(240, 40, 75, 30);
        fieldFecha.setBounds(280, 40, 100, 30);
        labelMonto.setBounds(15, 80, 75, 30);
        fieldMonto.setBounds(75, 80, 200, 30);
        labelConcepto.setBounds(15, 115, 75, 30);
        fieldConcepto.setBounds(95, 115, 200, 120);
        labelUsuario.setBounds(15, 240, 75, 30);
        fieldUsuario.setBounds(95, 240, 100, 30);
        buttonEliminar.setBounds(25, 275, 100, 30);
        buttonGuardar.setBounds(175, 275, 100, 30);
        buttonCerrar.setBounds(280, 275, 100, 30);        
        
        mainPanel.add(labelTitulo);
        mainPanel.add(labelFecha);
        mainPanel.add(fieldFecha);
        mainPanel.add(labelMonto);
        mainPanel.add(fieldMonto);
        mainPanel.add(labelConcepto);
        mainPanel.add(fieldConcepto);
        mainPanel.add(labelUsuario);
        mainPanel.add(fieldUsuario);
        mainPanel.add(buttonEliminar);
        mainPanel.add(buttonGuardar);
        mainPanel.add(buttonCerrar);
        
        fieldMonto.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        fieldConcepto.setBorder(BorderFactory.createLoweredSoftBevelBorder()); 
        fieldConcepto.setLineWrap(true);
        
        if(eg != null){
            fieldFecha.setText(eg.fecha);
            fieldMonto.setText(String.valueOf(eg.monto));
            fieldConcepto.setText(eg.concepto);
            fieldUsuario.setText(eg.usuario);
        }
    }
    
    private void configurarUso(){
        fieldFecha.setEditable(false);
        fieldUsuario.setEditable(false);
        
        fieldFecha.setText(hoy());                
        
        if(uso == AÑADIR){
            buttonEliminar.setEnabled(false);
            fieldUsuario.setText(gui.usuarioActivo.nombre);                    
        }else if(uso == VER){                   
            fieldMonto.setEditable(false);
            fieldConcepto.setEditable(false);
            buttonGuardar.setEnabled(false);
        }
    }       
    
    @Override
    protected boolean confirmarCerrado(){
        if(super.confirmarCerrado()){
            cerrar();
            return true;
        }else{
            return false;
        }
    }    
    
    @Override
    public void cerrar(){
        super.cerrar();
        gui.ventEgreso = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonEliminar){            
                        
        }else if(e.getSource() == buttonGuardar){
            if(guardarEgreso())
                cerrar();                                        
        }else if(e.getSource() == buttonCerrar){
            confirmarCerrado();
        }
    }
            
    private boolean guardarEgreso(){
        float monto = aFloat(fieldMonto.getText(), "Monto");
        String sql;
        
        if(monto == DEFAULT_AFLOAT)
            return false;        
        
        if(uso == AÑADIR){        
            sql = "INSERT INTO EGRESOS "
                    + "(FECHA, MONTO, CONCEPTO, ID_USUARIO) "
                    + " VALUES("
                    + "'" + fieldFecha.getText() + "', "
                    + monto + ", "
                    + "'" + fieldConcepto.getText() + "', "
                    + gui.usuarioActivo.id + ")";
            
            if(!SQLConnection.actualizar(sql)){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");
                return false;
            }else{
                JOptionPane.showMessageDialog(null, "Guardado correcto");
                return true;
            }
        }else if(uso == VER){
            sql = "UPDATE EGRESOS SET "
                    + "MONTO = " + monto + ", "
                    + "CONCEPTO = '" + fieldConcepto.getText() + "' "
                    + "WHERE ID_EGRESO = " + eg.id;
            if(!SQLConnection.actualizar(sql)){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");
                return false;
            }else{
                JOptionPane.showMessageDialog(null, "Guardado correcto");
                return true;
            }                                
        }
        return false;
    }
    
}
