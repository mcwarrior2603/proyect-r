/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaCobrar extends Ventana implements ActionListener{
    
    private static final Font texto = new Font("Arial", Font.BOLD, 30);    
    
    private final JLabel labelTotal = new JLabel("Total");
    private final JLabel labelPago = new JLabel("Su pago");
    private final JLabel labelCambio = new JLabel("Cambio");
    
    private final JTextField fieldTotal = new JTextField();
    private final JTextField fieldPago = new JTextField();
    private final JTextField fieldCambio = new JTextField();     
    
    private final JButton buttonGuardar = new JButton("Guardar");
    private final JButton buttonCancelar = new JButton("Cancelar");
    
    private final JButton cero = new JButton("0");
    private final JButton primero = new JButton("1");
    private final JButton segundo = new JButton("2");
    private final JButton tercero = new JButton("3");
    private final JButton cuarto = new JButton("4");
    private final JButton quinto = new JButton("5");
    private final JButton sexto = new JButton("6");
    private final JButton septimo = new JButton("7");
    private final JButton octavo = new JButton("8");
    private final JButton noveno = new JButton("9");        
    
    private final JButton punto = new JButton(".");
    private final JButton borrar = new JButton("C");
    
    private JPanel panelPrincipal;
    private VentanaMainGUI gui;
    private float total;
    private boolean isPuntoActivo = false;
    private float decimales = 1;
    private BigDecimal numeroActual = BigDecimal.ZERO;        
    
    private boolean visible = true;
    
    public VentanaCobrar(float total, VentanaMainGUI gui){                
        super(675, 300);
        
//        setUndecorated(true); 
        
        this.total = total;
        this.gui = gui;                                        
        
        panelPrincipal = (JPanel)getContentPane();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                
        buttonCancelar.addActionListener(this);
        buttonGuardar.addActionListener(this);        
        cero.addActionListener(this);
        primero.addActionListener(this);
        segundo.addActionListener(this);
        tercero.addActionListener(this);
        cuarto.addActionListener(this);
        quinto.addActionListener(this);
        sexto.addActionListener(this);
        septimo.addActionListener(this);
        octavo.addActionListener(this);
        noveno.addActionListener(this);
        borrar.addActionListener(this);
        punto.addActionListener(this);        
        
        configurarComponentes();
        
        panelPrincipal.add(labelTotal);
        panelPrincipal.add(fieldTotal);
        panelPrincipal.add(labelPago);
        panelPrincipal.add(fieldPago);
        panelPrincipal.add(labelCambio);
        panelPrincipal.add(fieldCambio);                
        panelPrincipal.add(buttonCancelar);
        panelPrincipal.add(buttonGuardar);
        panelPrincipal.add(cero);
        panelPrincipal.add(primero);
        panelPrincipal.add(segundo);
        panelPrincipal.add(tercero);
        panelPrincipal.add(cuarto);
        panelPrincipal.add(quinto);
        panelPrincipal.add(sexto);
        panelPrincipal.add(septimo);
        panelPrincipal.add(octavo);
        panelPrincipal.add(noveno);
        
        panelPrincipal.add(punto);
        panelPrincipal.add(borrar);                                
        
    }
    
    private void configurarComponentes(){
        labelTotal.setBounds(15, 25, 175, 30);
        fieldTotal.setBounds(195, 20, 150, 40);
        labelPago.setBounds(15, 70, 175, 30);
        fieldPago.setBounds(195, 65, 150, 40);
        labelCambio.setBounds(15, 115, 175, 30);
        fieldCambio.setBounds(195, 110, 150, 40);
        buttonCancelar.setBounds(135, 160, 100, 30);
        buttonGuardar.setBounds(240, 160, 100, 30);
        
        primero.setBounds(370, 20, 50, 50);
        segundo.setBounds(440, 20, 50, 50);
        tercero.setBounds(510, 20, 50, 50);
        cuarto.setBounds(370, 90, 50, 50);
        quinto.setBounds(440, 90, 50, 50);
        sexto.setBounds(510, 90, 50, 50);
        septimo.setBounds(370, 160, 50, 50);
        octavo.setBounds(440, 160, 50, 50);
        noveno.setBounds(510, 160, 50, 50);
        cero.setBounds(370, 230, 50, 50);
        borrar.setBounds(440, 230, 50, 50);
        punto.setBounds(510, 230, 50, 50);
                
        primero.setFont(texto);
        segundo.setFont(texto);
        tercero.setFont(texto);
        cuarto.setFont(texto);
        quinto.setFont(texto);
        sexto.setFont(texto);
        septimo.setFont(texto);
        octavo.setFont(texto);
        noveno.setFont(texto);
        cero.setFont(texto);
        borrar.setFont(texto);
        punto.setFont(texto);
        labelCambio.setFont(texto);
        labelPago.setFont(texto);
        labelTotal.setFont(texto);
        fieldCambio.setFont(texto);
        fieldTotal.setFont(texto);
        fieldPago.setFont(texto);
        
        fieldTotal.setText(String.valueOf(total));
        fieldPago.setText("0.0");
        actualizarPago(numeroActual.toString());
        
        fieldTotal.setEditable(false);
        fieldPago.setEditable(false);
        fieldCambio.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int valor = 0;
        
        if(e.getSource() == buttonCancelar){
            cerrar();
            gui.cobrando = false;
        }else if(e.getSource() == buttonGuardar){
            if(gui.guardarVenta(total)){
                JOptionPane.showMessageDialog(null, "Venta correcta");
                cerrar();
                gui.limpiarVenta();           
                gui.cobrando = false;
            }else{
                JOptionPane.showMessageDialog(null, "Venta correcta");
                cerrar();
                gui.cobrando = false;
            }            
        }else if(e.getSource() == punto){
            isPuntoActivo = true;
            decimales /= 10;
            return;
        }else if(e.getSource() == borrar){
            numeroActual = BigDecimal.ZERO;
            isPuntoActivo = false;
            decimales = 1; 
            actualizar();
        }        
        
        if(e.getSource() == cero){            
            valor = 0;         
        }else if(e.getSource() == primero){
            valor = 1;
        }else if(e.getSource() == segundo){
            valor = 2;            
        }else if(e.getSource() == tercero){
            valor = 3;
        }else if(e.getSource() == cuarto ){
            valor = 4;            
        }else if(e.getSource() == quinto){
            valor = 5;            
        }else if(e.getSource() == sexto){
            valor = 6;
        }else if(e.getSource() == septimo){
            valor = 7;
        }else if(e.getSource() == octavo){
            valor = 8;
        }else if(e.getSource() == noveno){
            valor = 9;
        }           
        
        if(isPuntoActivo){
            numeroActual = (numeroActual.add(BigDecimal.valueOf(decimales * valor)));
            decimales /= 10;
        }else{
            numeroActual = (numeroActual.movePointRight(1).add(BigDecimal.valueOf(valor)));
        }
        
        actualizarPago(numeroActual.toString());        
    }
    
    @Override
    public void windowClosing(WindowEvent e) {            
        if(isVisible()){
            cerrar();
            gui.cobrando = false;
        }                
    }
    
    public void actualizarPago(String g){
        fieldPago.setText(g);
        actualizar();
    }
    
    public void actualizar(){
        float pago = aFloat(fieldPago.getText(), "Pago");
        float total = aFloat(fieldTotal.getText(), "Total");
        if(pago == DEFAULT_AFLOAT || total == DEFAULT_AFLOAT)
            return;
        
        fieldCambio.setText(String.valueOf(pago - total));
        
    }
    
}
