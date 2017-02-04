/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaGuardarApertura extends Ventana implements ActionListener{
    
    private final JLabel labelTitulo = new JLabel();
    private final JLabel labelMonto = new JLabel("Monto de apertura:");
    private final JTextField fieldMonto = new JTextField();
    private final JButton buttonGuardar = new JButton("Guardar");
    
    private JPanel mainPanel;
    private VentanaMainGUI gui;
            
    public VentanaGuardarApertura(VentanaMainGUI gui){
        super(350, 160, NOMBRE_SW + " - Guardar apertura");                        
        
        this.gui = gui;
        
        configurarComponentes();
                
        if(gui.ventApertura != null)
            gui.ventApertura.cerrar();            
        gui.ventApertura = this;
        
    }
    
    private void configurarComponentes(){
        String titulo = "Apertura de caja :: Turno " + gui.turnoActual;
        labelTitulo.setText(titulo);        
        
        setAlwaysOnTop(true);
        setLocation((int)(gui.getWidth() / 2) + 100, (int) (gui.getHeight() / 2) - 100);
        mainPanel = (JPanel)getContentPane();
        
        labelTitulo.setFont(fontTitulo);
        
        labelTitulo.setBounds(25, 5, 300, 40);
        labelMonto.setBounds(15, 55, 150, 30);
        fieldMonto.setBounds(170, 55, 100, 30);
        buttonGuardar.setBounds(150, 90, 100, 30);
        
        mainPanel.add(labelTitulo);
        mainPanel.add(labelMonto);
        mainPanel.add(fieldMonto);
        mainPanel.add(buttonGuardar);
        
        buttonGuardar.addActionListener(this);
    }   
    
    @Override
    protected boolean confirmarCerrado(){
        if(super.confirmarCerrado()){
            cerrar();
            return true;
        }            
        return false;
    }
    
    @Override
    public void cerrar(){        
        super.cerrar();
        gui.ventApertura = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonGuardar){
            guardarApertura();
        }
    }
    
    private void guardarApertura(){
        /**
         * Guarda la apertura al iniciar el programa
         */
        float monto = aFloat(fieldMonto.getText(), "Monto de apertura");
        
        if(monto == DEFAULT_AFLOAT)
            return;
        
        Calendar hora = Calendar.getInstance();
        
        String sql = "INSERT INTO CORTES_CAJA(FECHA, APERTURA, TURNO, HORA) "
                + "VALUES("
                + "'" + hoy() + "', "
                + monto + ","
                + gui.turnoActual + ", "
                + "'" + horaNow() + "')";
        
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Apertura guardada correctamente");
            gui.timerRecordatorio.cancel();
            cerrar();
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo guardar la apertura :(");
        }
    }
    
}
