/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO FINAL
 */
public class PanelNumerico extends JFrame implements WindowListener {
    
    private final Dimension x = new Dimension(235,325);
    
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
    private final JButton vacio = new JButton("");
    
    public PanelNumerico(){
        
        addWindowListener(this);
        setLayout(null);
        setPreferredSize(x);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        
        add(cero);
        add(primero);
        add(segundo);
        add(tercero);
        add(cuarto);
        add(quinto);
        add(sexto);
        add(septimo);
        add(octavo);
        add(noveno);
        
        add(punto);
        add(vacio);
        
        primero.setBounds( 20, 20, 50, 50);
        segundo.setBounds(90, 20, 50, 50);
        tercero.setBounds( 160, 20, 50, 50);
        cuarto.setBounds( 20, 90, 50, 50);
        quinto.setBounds( 90, 90, 50, 50);
        sexto.setBounds( 160, 90, 50, 50);
        septimo.setBounds( 20, 160, 50, 50);
        octavo.setBounds( 90, 160, 50, 50);
        noveno.setBounds(160, 160, 50, 50);
        cero.setBounds(20, 230, 50, 50);
        vacio.setBounds(90, 230, 50, 50);
        punto.setBounds(160, 230, 50, 50);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}
