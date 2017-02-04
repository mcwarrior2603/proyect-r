/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaCorteDeCaja extends Ventana implements ActionListener{                   
    private final JTextField textApertura = new JTextField();
    private final JTextField textCierre = new JTextField();
    private final JTextField textFecha = new JTextField();
    private final JTextField textVentas = new JTextField();
    private final JTextField textDevoluciones = new JTextField();
    private final JTextField textEgresos = new JTextField();
    
    private final JLabel labelTitulo = new JLabel("Corte de caja");
    private final JLabel linea = new JLabel("____________________________________");
    private final JLabel labelapertura = new JLabel("Apertura");
    private final JLabel labelCierre = new JLabel("Cierre");
    private final JLabel labelVentas = new JLabel("Ventas");
    private final JLabel labelDevoluciones = new JLabel("Devoluciones/Cancelaciones");
    private final JLabel labelEgresos = new JLabel("Egresos");    
    private final JButton buttonBuscar = new JButton("Buscar");
    private final JButton buttonVentas = new JButton ("Ventas/Devouciones");
    private final JButton buttonEgresos = new JButton("Egresos");
    private final JRadioButton turno1 = new JRadioButton("TURNO 1");
    private final JRadioButton turno2 = new JRadioButton("TURNO 2");
    private final JRadioButton turno3 = new JRadioButton("TURNO 3");
    private final JRadioButton resDiario = new JRadioButton("RESUMEN DIARIO");
    private final ButtonGroup groupTurnos = new ButtonGroup();
    private JPanel mainPanel;
    
    private float fApertura;
    private float fVentas;
    private float fDevoluciones;
    private float fEgresos;
    private float fCierre;
    
    VentanaMainGUI gui;
    
    public VentanaCorteDeCaja(VentanaMainGUI gui){    
        super(450, 520, NOMBRE_SW + " - Corte de caja");                        
        
        this.gui = gui;
        
        if(gui.ventCorte != null)
            gui.ventCorte.cerrar();
        gui.ventCorte = this;
        
        mainPanel = (JPanel) getContentPane();
        
        
        mainPanel.add(turno1);
        mainPanel.add(turno2);
        mainPanel.add(turno3);
        mainPanel.add(resDiario);
        mainPanel.add(textApertura);
        mainPanel.add(textCierre);
        mainPanel.add(textFecha);
        mainPanel.add(buttonBuscar);
        mainPanel.add(textVentas);
        mainPanel.add(textDevoluciones);
        mainPanel.add(textEgresos);
        mainPanel.add(labelapertura);
        mainPanel.add(labelCierre);
        mainPanel.add(labelVentas);
        mainPanel.add(labelDevoluciones);
        mainPanel.add(labelEgresos);
        mainPanel.add(buttonVentas);
        mainPanel.add(linea);
        mainPanel.add(buttonEgresos);
        mainPanel.add(labelTitulo);
        
        groupTurnos.add(turno1);
        groupTurnos.add(turno2);
        groupTurnos.add(turno3);
        groupTurnos.add(resDiario);
        resDiario.setSelected(true);
        
        textApertura.setEditable(false);
        textVentas.setEditable(false);
        textDevoluciones.setEditable(false);
        textEgresos.setEditable(false);
        textCierre.setEditable(false);
        labelTitulo.setFont(fontTitulo);
        textFecha.setText("AAAA/MM/DD");
        
        
        labelTitulo.setBounds( 15, 30, 185, 30);
        textFecha.setBounds( 190, 30, 100, 30);
        buttonBuscar.setBounds( 295, 30, 100, 30);
        
        turno1.setBounds( 15, 90, 100, 30);
        turno2.setBounds( 130, 90, 100, 30);
        turno3.setBounds(245, 90, 100, 30);
        resDiario.setBounds( 130, 110, 180, 30);
        
        labelapertura.setBounds( 25, 160, 100, 30);
        textApertura.setBounds( 170, 160, 200, 30);        
        labelVentas.setBounds( 25, 210, 100, 30);
        textVentas.setBounds( 170, 210, 200, 30);        
        labelDevoluciones.setBounds( 25, 260, 175, 30);
        textDevoluciones.setBounds( 170, 260, 200, 30);        
        labelEgresos.setBounds( 25, 310, 100, 30);
        textEgresos.setBounds( 170, 310, 200, 30);        
        labelCierre.setBounds( 25, 370, 100, 30);
        textCierre.setBounds( 170, 370, 200, 30);
        
        linea.setBounds(160, 330, 220, 30);
        
        buttonVentas.setBounds( 85, 420, 150, 30);        
        buttonEgresos.setBounds( 240, 420, 100, 30);
        
        buttonBuscar.addActionListener(this);
        buttonVentas.addActionListener(this);
        buttonEgresos.addActionListener(this);
    }
    
    private void buscar(String fecha){   
        /**
         * Busca la fecha de acuerdo al corte de caja que se desea ver
         */
        
        String sqlTurno = "";
        
        if(resDiario.isSelected()){
            sqlTurno = "";
        }else if(turno1.isSelected()){
            sqlTurno = " AND TURNO = 1";
        }else if(turno2.isSelected()){
            sqlTurno = " AND TURNO = 2";
        }else if(turno3.isSelected()){
            sqlTurno = " AND TURNO = 3";
        }
        
        try {
            String sql = "SELECT * FROM CORTES_CAJA WHERE FECHA = '" + fecha + "'";                    
            sql += sqlTurno;
            
            ResultSet query = SQLConnection.buscar(sql);
            if(!query.next()){                
                JOptionPane.showMessageDialog(null, "No existe corte "
                        + "para la fecha indicada");
                return;                
            }else{                
                fApertura = query.getFloat("APERTURA");
                textApertura.setText(String.valueOf(fApertura));               
            }
            
            sql = "SELECT  SUM(TOTAL) AS TOTAL FROM VENTAS WHERE FECHA = '" + fecha +"'";
            sql += sqlTurno;
            
            query = SQLConnection.buscar(sql);
            
            if(!query.next()){                
                fVentas = 0.0f;
                textVentas.setText(fVentas+"");
            }else{
                fVentas = query.getFloat("TOTAL");
                textVentas.setText(fVentas+ "");
            }
            
            sql = "SELECT SUM(TOTAL) AS TOTAL FROM DEVOLUCIONES WHERE "
                    + "FECHA = '"+ fecha + "'";
            sql += sqlTurno;
            
            query = SQLConnection.buscar(sql);
            
            if(!query.next()){                
                fDevoluciones = 0.0f;
                textDevoluciones.setText(fDevoluciones+"");
            }else{
                fDevoluciones = query.getFloat("TOTAL");
                textDevoluciones.setText(fDevoluciones+ "");
            }
            
            sql = "SELECT SUM(MONTO) AS TOTAL FROM EGRESOS WHERE FECHA = '"
                    + fecha + "'";
            sql += sqlTurno;
            
            query = SQLConnection.buscar(sql);
            
            if(!query.next()){                
                fEgresos = 0.0f;
                textEgresos.setText(fEgresos+"");
            }else{
                fEgresos = query.getFloat("TOTAL");
                textEgresos.setText(fEgresos+ "");
            }
            
            fCierre = fApertura + fVentas - fDevoluciones - fEgresos;
            
            textCierre.setText(fCierre + "");
            
        } catch (SQLException ex) {
            reportarError(ex);
        }
    }        

    @Override
    public void cerrar(){
        super.cerrar();  
        gui.ventCorte = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!checkFecha(textFecha.getText().trim())){
            JOptionPane.showMessageDialog(null, "Fecha inválida.");
            return;   
        }            
        
        if(e.getSource() == buttonBuscar){                        
            buscar(textFecha.getText());            
        }else if(e.getSource() == buttonVentas){
            new VentanaListaVentas(gui, textFecha.getText().trim());
        }else if(e.getSource() == buttonEgresos){
            new VentanaListaEgresos(gui, textFecha.getText().trim());
        }
    }
    
}
