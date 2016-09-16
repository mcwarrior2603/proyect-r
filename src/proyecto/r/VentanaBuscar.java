///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package proyecto.r;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
///**
// *
// * @author MCwar
// */
//public class VentanaBuscar extends Ventana{            
//    
//    private final JLabel producto = new JLabel("Nombre:");
//    private final JButton botonBuscar = new JButton("Buscar");
//    private final JTextField nombreText = new JTextField ();
//        
//    private JPanel panelBusqueda = new JPanel();
//    private JPanel panelPrincipal;
//    private VentanaMainGUI gui;    
//    
//    private PanelProductos productos = new PanelProductos();
//    
//    public VentanaBuscar(VentanaMainGUI gui){                       
//        super(700,700);
//        
//        this.gui = gui;        
//                                
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
//        
//        panelPrincipal = (JPanel) getContentPane();                        
//        
//        panelPrincipal.setLayout(new BorderLayout());
//        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
//        panelPrincipal.add(productos, BorderLayout.CENTER);
//        
//        panelBusqueda.setLayout(new BorderLayout(15, 15));        
//        panelBusqueda.setBorder(BorderFactory.createEmptyBorder( 20, 20, 20, 20));       
//        panelBusqueda.add(producto, BorderLayout.WEST);
//        panelBusqueda.add(botonBuscar, BorderLayout.EAST);
//        panelBusqueda.add(nombreText, BorderLayout.CENTER);
//        
//        
//        
//        
//        
//    }
//    
//}
