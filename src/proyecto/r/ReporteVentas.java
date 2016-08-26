/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO FINAL
 */
public class ReporteVentas extends Ventana{
    
    private final Dimension ventana = new Dimension(700,500);
    private final JLabel titulo = new JLabel();
    private final JLabel alcance = new JLabel();
    private final TextField fechaInicio = new TextField();
    private final TextField fechaTerminal = new TextField();
    private final JButton generador = new JButton();
    private JPanel todo = new JPanel();    
    
    
    
    public ReporteVentas(){
        
        setResizable(false);
        addWindowListener(this);
        setLayout(null);
        setPreferredSize(ventana);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        pack();        
        setLocationRelativeTo(null);                        
        setVisible(true);        
        
        todo = (JPanel) getContentPane();
        
        todo.setLayout(null);                        
        
        titulo.setText("Ventas");
        alcance.setText("al");
        generador.setText("Generar Repeorte");
        
        fechaInicio.setBounds( 445, 50, 100, 30);
        fechaTerminal.setBounds(585, 50, 100, 30);
        
        alcance.setBounds(560, 50, 100, 30);
        generador.setBounds(30, 500, 150, 30);
        titulo.setBounds(20, 10, 100, 30);                        
        
        titulo.setFont(fontTitulo);
        
        todo.add(titulo);
        todo.add(generador);
        todo.add(fechaInicio);
        todo.add(fechaTerminal);
        todo.add(alcance);
    }
}
