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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    private final JRadioButton diaActual = new JRadioButton();
    private final JRadioButton diaPeriodo = new JRadioButton();
    private final JTable datos = new JTable();
    private DefaultTableModel model;
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
        
        //datos.getColumnModel().addColumn(1).setPreferredWidth(100);
        
        model = (DefaultTableModel) datos.getModel();
        model.addRow(new Object[]{"","","",""});
        model.setNumRows(2);
        
        
        titulo.setText("Ventas");
        alcance.setText("al");
        generador.setText("Generar Repeorte");
        diaActual.setText("Hoy");
        diaPeriodo.setText("Periodo");
        
        
        fechaInicio.setBounds( 445, 50, 100, 30);
        fechaTerminal.setBounds(585, 50, 100, 30);
        
        diaActual.setBounds( 35, 50, 100, 30);
        diaPeriodo.setBounds( 140, 50, 100, 30);
        
        alcance.setBounds(560, 50, 100, 30);
        generador.setBounds(30, 420, 150, 30);
        titulo.setBounds(20, 10, 100, 30);                        
        
        titulo.setFont(fontTitulo);
        
        todo.add(titulo);
        todo.add(generador);
        todo.add(fechaInicio);
        todo.add(fechaTerminal);
        todo.add(alcance);
        todo.add(diaActual);
        todo.add(diaPeriodo);
        todo.add(datos, "Center");
        
    }

}
