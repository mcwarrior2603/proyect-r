/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class Cobrar extends Ventana implements ActionListener{
    
    private static final Dimension dimensionVentana = new Dimension(300, 200);
    
    private final JLabel labelTotal = new JLabel("Total");
    private final JLabel labelPago = new JLabel("Su pago");
    private final JLabel labelCambio = new JLabel("Cambio");
    
    private final JTextField fieldTotal = new JTextField();
    private final JTextField fieldPago = new JTextField();
    private final JTextField fieldCambio = new JTextField();     
    
    private final JButton buttonGuardar = new JButton("Guardar");
    private final JButton buttonCancelar = new JButton("Cancelar");
    
    private JPanel panelPrincipal;
    private InterfazPrincipal gui;
    private float total;
    
    public Cobrar(float total, InterfazPrincipal gui){
        
        this.total = total;
        this.gui = gui;        
        
        setUndecorated(true);
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        setVisible(true);        
        
        panelPrincipal = (JPanel)getContentPane();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        addWindowListener(this);
        buttonCancelar.addActionListener(this);
        buttonGuardar.addActionListener(this);        
        
        configurarComponentes();
        
        panelPrincipal.add(labelTotal);
        panelPrincipal.add(fieldTotal);
        panelPrincipal.add(labelPago);
        panelPrincipal.add(fieldPago);
        panelPrincipal.add(labelCambio);
        panelPrincipal.add(fieldCambio);                
        panelPrincipal.add(buttonCancelar);
        panelPrincipal.add(buttonGuardar);
    }
    
    private void configurarComponentes(){
        labelTotal.setBounds(15, 25, 75, 30);
        fieldTotal.setBounds(95, 20, 150, 40);
        labelPago.setBounds(15, 70, 75, 30);
        fieldPago.setBounds(95, 65, 150, 40);
        labelCambio.setBounds(15, 115, 75, 30);
        fieldCambio.setBounds(95, 110, 150, 40);
        buttonCancelar.setBounds(85, 160, 100, 30);
        buttonGuardar.setBounds(190, 160, 100, 30);
                
        fieldTotal.setText(String.valueOf(total));
        fieldPago.setText("0.0");
        actualizar();
        
        fieldTotal.setEditable(false);
        fieldPago.setEditable(false);
        fieldCambio.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonCancelar){
            dispose();
            setVisible(false);
        }else if(e.getSource() == buttonGuardar){
            if(gui.guardarVenta(total)){
                JOptionPane.showMessageDialog(null, "Venta correcta");
                setVisible(false);
                dispose();
                gui.limpiarVenta();
            }else{
                JOptionPane.showMessageDialog(null, "Venta correcta");
                setVisible(false);
                dispose();
            }
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        if(isVisible()){
            dispose();
            setVisible(false);
        }
        
    }
    
    public void actualizar(){
        float pago = aFloat(fieldPago.getText(), "Pago");
        float total = aFloat(fieldTotal.getText(), "Total");
        if(pago == DEFAULT_AFLOAT || total == DEFAULT_AFLOAT)
            return;
        
        fieldCambio.setText(String.valueOf(pago - total));
        
    }
    
}
