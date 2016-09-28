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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JPanel mainPanel;
              
    private float fApertura;
    private float fVentas;
    private float fDevoluciones;
    private float fEgresos;
    private float fCierre;
    
    
    public VentanaCorteDeCaja(){    
        super(420, 500);                        
        
        mainPanel = (JPanel) getContentPane();
                        
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
        
        labelapertura.setBounds( 25, 100, 100, 30);
        textApertura.setBounds( 170, 100, 200, 30);        
        labelVentas.setBounds( 25, 150, 100, 30);
        textVentas.setBounds( 170, 150, 200, 30);        
        labelDevoluciones.setBounds( 25, 200, 175, 30);
        textDevoluciones.setBounds( 170, 200, 200, 30);        
        labelEgresos.setBounds( 25, 250, 100, 30);
        textEgresos.setBounds( 170, 250, 200, 30);        
        labelCierre.setBounds( 25, 300, 100, 30);
        textCierre.setBounds( 170, 300, 200, 30);
        
        linea.setBounds(160, 270, 220, 30);
        
        buttonVentas.setBounds( 85, 400, 150, 30);        
        buttonEgresos.setBounds( 240, 400, 100, 30);
        
        buttonBuscar.addActionListener(this);
        buttonVentas.addActionListener(this);
        buttonEgresos.addActionListener(this);
    }
    
    private void buscar(String fecha){   
        /**
         * Busca la fecha de acuerdo al corte de caja que se desea ver
         */
        try {
            String sql = "SELECT * FROM CORTES_CAJA WHERE FECHA = '" + fecha + "'";
        
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
    public void actionPerformed(ActionEvent e) {
        if(!checkFecha(textFecha.getText().trim())){
            JOptionPane.showMessageDialog(null, "Fecha inválida.");
            return;   
        }            
        
        if(e.getSource() == buttonBuscar){                        
            buscar(textFecha.getText());            
        }else if(e.getSource() == buttonVentas){
            new VentanaListaVentas(textFecha.getText().trim());
        }else if(e.getSource() == buttonEgresos){
            new VentanaListaEgresos(textFecha.getText().trim());
        }
    }
    
}
