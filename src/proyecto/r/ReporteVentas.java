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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    private JScrollPane scTsbla = new JScrollPane();
    
    private JPanel Tabla = new JPanel();
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
        
        datos.setModel(new ModelVentas());
        scTsbla.setViewportView(datos);
    
        add(datos);
        
        
        
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

class ModelVentas implements TableModel{

    
    ArrayList <String> productos = new ArrayList();
    
    ArrayList <Integer> cantidad = new ArrayList();
    
    public ModelVentas(){
        
        productos.add("papas");
        productos.add("Galletas");
        
        cantidad.add(1);
        cantidad.add(3);
        
    }
    
    @Override
    public int getRowCount() {
        
        return productos.size();
        
    }

    @Override
    public int getColumnCount() {
        return 2;
        
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0){
            return "Productos";
        }if(columnIndex == 1){
            return "Cantidad";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
         if(columnIndex == 0){
            return String.class;
        } if(columnIndex == 1){
            return String.class;
        }
        
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         if(columnIndex == 0){
            return productos.get(rowIndex);
        } if(columnIndex == 1){
            return cantidad.get(rowIndex);
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }
    
}