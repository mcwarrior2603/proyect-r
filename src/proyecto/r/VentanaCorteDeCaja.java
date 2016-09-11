/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaCorteDeCaja extends Ventana{        
    
    private static final Dimension dimensionVentana = new Dimension(420, 500);
    private final JRadioButton botonAyer = new JRadioButton("Ayer");
    private final JRadioButton botonFecha = new JRadioButton("Fecha");
    
    private final JTextField textApertura = new JTextField();
    private final JTextField textCierre = new JTextField();
    private final JTextField textFecha = new JTextField();
    private final JTextField textVentas = new JTextField();
    private final JTextField textDevoluciones = new JTextField();
    private final JTextField textEgresos = new JTextField();
    
    private final JLabel labelapertura = new JLabel("Apertura");
    private final JLabel labelCierre = new JLabel("Cierre");
    private final JLabel labelVentas = new JLabel("Ventas");
    private final JLabel labelDevoluciones = new JLabel("Devoluciones");
    private final JLabel labelEgresos = new JLabel("Egresos");
    
    private final JButton Ventas = new JButton ("Ver ventas");
    
    private JPanel mainPanel;
    
    
    public VentanaCorteDeCaja(){
    
        setLayout(null);
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(this);
        
        mainPanel = (JPanel) getContentPane();
        
        mainPanel.add(botonAyer);
        mainPanel.add(botonFecha);
        mainPanel.add(textApertura);
        mainPanel.add(textCierre);
        mainPanel.add(textFecha);
        mainPanel.add(textVentas);
        mainPanel.add(textDevoluciones);
        mainPanel.add(textEgresos);
        mainPanel.add(labelapertura);
        mainPanel.add(labelCierre);
        mainPanel.add(labelVentas);
        mainPanel.add(labelDevoluciones);
        mainPanel.add(labelEgresos);
        mainPanel.add(Ventas);
        
        botonAyer.setBounds( 30, 30, 100, 30);
        botonFecha.setBounds( 150, 30, 100, 30);
        textFecha.setBounds( 270, 30, 100, 30);
        
        labelapertura.setBounds( 100, 100, 100, 30);
        textApertura.setBounds( 270, 100, 100, 30);
        
        labelVentas.setBounds( 100, 150, 100, 30);
        textVentas.setBounds( 270, 150, 100, 30);
        
        labelDevoluciones.setBounds( 100, 200, 100, 30);
        textDevoluciones.setBounds( 270, 200, 100, 30);
        
        labelEgresos.setBounds( 100, 250, 100, 30);
        textEgresos.setBounds( 270, 250, 100, 30);
        
        labelCierre.setBounds( 100, 300, 100, 30);
        textCierre.setBounds( 270, 300, 100, 30);
        
        Ventas.setBounds( 170, 400, 100, 30);
        
        
    }
    
}
