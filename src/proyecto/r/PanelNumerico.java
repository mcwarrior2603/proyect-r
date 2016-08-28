///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package proyecto.r;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//import java.math.BigDecimal;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
///**
// *
// * @author USUARIO FINAL
// */
//public class PanelNumerico extends JFrame implements ActionListener{
//    
//    private final Dimension x = new Dimension(235,325);
//    
//    private final JButton cero = new JButton("0");
//    private final JButton primero = new JButton("1");
//    private final JButton segundo = new JButton("2");
//    private final JButton tercero = new JButton("3");
//    private final JButton cuarto = new JButton("4");
//    private final JButton quinto = new JButton("5");
//    private final JButton sexto = new JButton("6");
//    private final JButton septimo = new JButton("7");
//    private final JButton octavo = new JButton("8");
//    private final JButton noveno = new JButton("9");        
//    
//    private final JButton punto = new JButton(".");
//    private final JButton borrar = new JButton("C");
//    
//    private Cobrar objetivo;
//    
//    private boolean isPuntoActivo = false;
//    private float decimales = 1;
//    private BigDecimal numeroActual = BigDecimal.ZERO;
//    
//    public PanelNumerico(Cobrar objetivo){
//        
//        this.objetivo = objetivo;
//                
//        setLayout(null);
//        setPreferredSize(x);
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        setResizable(false);
//        setUndecorated(true);
//        pack();
//        setVisible(true);    
//        ((JPanel)getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        
//        cero.addActionListener(this);
//        primero.addActionListener(this);
//        segundo.addActionListener(this);
//        tercero.addActionListener(this);
//        cuarto.addActionListener(this);
//        quinto.addActionListener(this);
//        sexto.addActionListener(this);
//        septimo.addActionListener(this);
//        octavo.addActionListener(this);
//        noveno.addActionListener(this);
//        borrar.addActionListener(this);
//        punto.addActionListener(this);
//        
//        add(cero);
//        add(primero);
//        add(segundo);
//        add(tercero);
//        add(cuarto);
//        add(quinto);
//        add(sexto);
//        add(septimo);
//        add(octavo);
//        add(noveno);
//        
//        add(punto);
//        add(borrar);
//        
//        primero.setBounds( 20, 20, 50, 50);
//        segundo.setBounds(90, 20, 50, 50);
//        tercero.setBounds( 160, 20, 50, 50);
//        cuarto.setBounds( 20, 90, 50, 50);
//        quinto.setBounds( 90, 90, 50, 50);
//        sexto.setBounds( 160, 90, 50, 50);
//        septimo.setBounds( 20, 160, 50, 50);
//        octavo.setBounds( 90, 160, 50, 50);
//        noveno.setBounds(160, 160, 50, 50);
//        cero.setBounds(20, 230, 50, 50);
//        borrar.setBounds(90, 230, 50, 50);
//        punto.setBounds(160, 230, 50, 50);
//    }
//
//    public void addFieldDeEfecto(Cobrar objetivo){
//        this.objetivo = objetivo;
//    }
//
//    private void actualizar(){
//        objetivo.actualizarPago(numeroActual.toString());
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int valor = 0;
//        
//        if(e.getSource() == punto){
//            isPuntoActivo = true;
//            decimales /= 10;
//            return;
//        }else if(e.getSource() == borrar){
//            numeroActual = BigDecimal.ZERO;
//            isPuntoActivo = false;
//            decimales = 1; 
//            actualizar();
//        }        
//        
//        if(e.getSource() == cero){            
//            valor = 0;         
//        }else if(e.getSource() == primero){
//            valor = 1;
//        }else if(e.getSource() == segundo){
//            valor = 2;            
//        }else if(e.getSource() == tercero){
//            valor = 3;
//        }else if(e.getSource() == cuarto ){
//            valor = 4;            
//        }else if(e.getSource() == quinto){
//            valor = 5;            
//        }else if(e.getSource() == sexto){
//            valor = 6;
//        }else if(e.getSource() == septimo){
//            valor = 7;
//        }else if(e.getSource() == octavo){
//            valor = 8;
//        }else if(e.getSource() == noveno){
//            valor = 9;
//        }           
//        
//        if(isPuntoActivo){
//            numeroActual = (numeroActual.add(BigDecimal.valueOf(decimales * valor)));
//            decimales /= 10;
//        }else{
//            numeroActual = (numeroActual.movePointRight(1).add(BigDecimal.valueOf(valor)));
//        }
//        
//        actualizar();
//    }        
//}
