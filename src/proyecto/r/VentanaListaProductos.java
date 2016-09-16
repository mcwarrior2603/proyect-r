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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
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
public class VentanaListaProductos extends Ventana{
        
    private final JLabel titulo = new JLabel();
    private final JLabel alcance = new JLabel();
    
    private final TextField fechaInicio = new TextField();
    private final TextField fechaTerminal = new TextField();
    
    private final JButton generador = new JButton();
    private final JRadioButton diaActual = new JRadioButton();
    private final JRadioButton diaPeriodo = new JRadioButton();
    private final ButtonGroup bgPeriodo = new ButtonGroup();
    
    private final JTable datos = new JTable();
    private JScrollPane scTabla = new JScrollPane();
    
    private JPanel Tabla = new JPanel();
    private JPanel todo = new JPanel();    
    
    
    
    public VentanaListaProductos(){
        super(700,500);
        
        generador.setEnabled(false);
        
        todo = (JPanel) getContentPane();                                 
        
        datos.setModel(new ModelVentasProductos(hoy(), hoy()));
        scTabla.setViewportView(datos);
        
        datos.getColumnModel().getColumn(1).setMaxWidth(100);
                          
        bgPeriodo.add(diaActual);
        bgPeriodo.add(diaPeriodo);
        diaActual.setSelected(true);
        
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
        
        scTabla.setBounds(15, 85, 670, 300);
        
        titulo.setFont(fontTitulo);
        
        todo.add(titulo);
        todo.add(generador);
        todo.add(fechaInicio);
        todo.add(fechaTerminal);
        todo.add(alcance);
        todo.add(diaActual);
        todo.add(diaPeriodo);
        todo.add(scTabla);        
             
        todo.updateUI();
    }

}

class ModelVentasProductos implements TableModel{

    
    ArrayList <String> productos = new ArrayList();
    
    ArrayList <Integer> cantidad = new ArrayList();
    
    public ModelVentasProductos(String fechaInicio, String fechaFin){
        
        String sql = "SELECT PRODUCTOS.NOMBRE, SUM(CANTIDAD) AS CANTIDAD FROM PRODUCTOS_VENTAS "
                + "INNER JOIN PRODUCTOS ON PRODUCTOS.ID_PRODUCTO = PRODUCTOS_VENTAS.ID_PRODUCTO "
                + "INNER JOIN VENTAS ON VENTAS.ID_VENTA = PRODUCTOS_VENTAS.ID_VENTA "                
                + "WHERE VENTAS.FECHA >= '"  + fechaInicio + "' AND VENTAS.FECHA <= '" + fechaFin + "'"
                + "GROUP BY PRODUCTOS.ID_PRODUCTO " ;
        
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            while(query.next()){
                productos.add(query.getString("NOMBRE"));
                cantidad.add(query.getInt("CANTIDAD"));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
        
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