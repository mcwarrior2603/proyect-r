/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class VentanaListaProductos extends Ventana implements ActionListener{
        
    private final JLabel titulo = new JLabel("Ventas por producto");
    private final JLabel alcance = new JLabel("al");
    
    private final TextField fechaInicio = new TextField();
    private final TextField fechaTerminal = new TextField();
    
    private final JButton buttonBuscar = new JButton();
    private final JRadioButton diaActual = new JRadioButton("Hoy");
    private final JRadioButton diaPeriodo = new JRadioButton("Periodo");
    private final ButtonGroup bgPeriodo = new ButtonGroup();
    
    private final JTable datos = new JTable();
    private JScrollPane scTabla = new JScrollPane();
    
    private JPanel Tabla = new JPanel();
    private JPanel todo = new JPanel();   
    
    private JRadioButton turno1 = new JRadioButton();
    private JRadioButton turno2 = new JRadioButton();
    private JRadioButton turno3 = new JRadioButton();
    private JRadioButton reporteDiario = new JRadioButton();
    private ButtonGroup groupTurnos = new ButtonGroup();
    
    private VentanaMainGUI gui;    
    
    public VentanaListaProductos(VentanaMainGUI gui){
        super(700,500, NOMBRE_SW + " - Ventas:Producto");
        
        this.gui = gui;
        
        if(gui.ventProductos != null)
            gui.ventProductos.cerrar();
        gui.ventProductos = this;
                        
        todo = (JPanel) getContentPane();                                 
        
        datos.setModel(new ModelVentasProductos(hoy(), hoy(), 0));
        scTabla.setViewportView(datos);
        
        datos.getColumnModel().getColumn(1).setMaxWidth(100);
                          
        bgPeriodo.add(diaActual);
        bgPeriodo.add(diaPeriodo);
        diaActual.setSelected(true);                      
        
        titulo.setText("Ventas");
        alcance.setText("al");
        buttonBuscar.setText("Buscar");
        diaActual.setText("Hoy");
        diaPeriodo.setText("Periodo");        
        
        turno1.setText("Turno 1");
        turno2.setText("Turno 2");
        turno3.setText("Turno 3");
        reporteDiario.setText("Reporte diario");
        
        groupTurnos.add(turno1);
        groupTurnos.add(turno2);
        groupTurnos.add(turno3);
        groupTurnos.add(reporteDiario);
        reporteDiario.setSelected(true);
        
        fechaInicio.setBounds(235, 50, 100, 30);
        fechaTerminal.setBounds(375, 50, 100, 30);        
        diaActual.setBounds( 35, 50, 100, 30);
        diaPeriodo.setBounds( 140, 50, 100, 30);        
        alcance.setBounds(350, 50, 100, 30);
        buttonBuscar.setBounds(530, 50, 100, 30);
        titulo.setBounds(20, 10, 100, 30);                                
        turno1.setBounds(35, 85, 100, 30);
        turno2.setBounds(140, 85, 100, 30);
        turno3.setBounds(245, 85, 100, 30);    
        reporteDiario.setBounds(350, 85, 150, 30);
        scTabla.setBounds(15, 135, 670, 300);
        
        titulo.setFont(fontTitulo);
        
        todo.add(titulo);
        todo.add(buttonBuscar);
        todo.add(fechaInicio);
        todo.add(fechaTerminal);
        todo.add(alcance);
        todo.add(diaActual);
        todo.add(diaPeriodo);
        todo.add(scTabla);        
        todo.add(turno1);
        todo.add(turno2);
        todo.add(turno3);
        todo.add(reporteDiario);
        
        todo.updateUI();
        
        buttonBuscar.addActionListener(this);
    }

    @Override
    public void cerrar(){
        super.cerrar();
        gui.ventProductos = null;
        
    
    }   
    
    @Override
    protected boolean confirmarCerrado(){
        if(super.confirmarCerrado()){
            cerrar();
            return true;
        }            
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int turno = 0;
        if(turno1.isSelected())
            turno = 1;
        else if(turno2.isSelected())
            turno = 2;
        else if(turno3.isSelected())
            turno = 3;                       
        
        if(diaActual.isSelected())            
            datos.setModel(new ModelVentasProductos(hoy(), hoy(), turno));
        else
            datos.setModel(new ModelVentasProductos(
                    fechaInicio.getText().trim(), 
                    fechaTerminal.getText().trim(),
                    turno));
    }
    
}

class ModelVentasProductos implements TableModel{

    /**
    *Se implementa las listas con las que se manejara el producto y su cantidad 
    */    
    ArrayList <Producto> listaProductos = new ArrayList();
    
    public ModelVentasProductos(String fechaInicio, String fechaFin, int turno){
        /**
         * Se da la setencia SQL para encontrar dichos productos
         * Y la fecha en que se desea ver
         */
        try {
            String sql = "SELECT PRODUCTOS.ID_PRODUCTO, PRODUCTOS.NOMBRE, SUM(CANTIDAD) AS CANTIDAD FROM PRODUCTOS_VENTAS "
                    + "INNER JOIN PRODUCTOS ON PRODUCTOS.ID_PRODUCTO = PRODUCTOS_VENTAS.ID_PRODUCTO "
                    + "INNER JOIN VENTAS ON VENTAS.ID_VENTA = PRODUCTOS_VENTAS.ID_VENTA "                
                    + "WHERE VENTAS.FECHA >= '"  + fechaInicio + "' AND VENTAS.FECHA <= '" + fechaFin + "' ";
            if(turno >= 1 && turno <= 3)
                sql += "AND TURNO=" + turno + " ";
        
            sql+= "GROUP BY PRODUCTOS.ID_PRODUCTO " ;                                                                
            ResultSet query = SQLConnection.buscar(sql);
            while(query.next()){
                incrementoProductos(
                        query.getInt("ID_PRODUCTO"), 
                        query.getString("NOMBRE"),
                        query.getInt("CANTIDAD"));
            }
            
            sql = "SELECT PRODUCTOS.ID_PRODUCTO, PRODUCTOS.NOMBRE, SUM(CANTIDAD) AS CANTIDAD FROM PRODUCTOS_DEVOLUCIONES "
                    + "INNER JOIN PRODUCTOS ON PRODUCTOS.ID_PRODUCTO = PRODUCTOS_DEVOLUCIONES.ID_PRODUCTO "
                    + "INNER JOIN DEVOLUCIONES ON DEVOLUCIONES.ID_DEVOLUCION = PRODUCTOS_DEVOLUCIONES.ID_DEVOLUCION "                
                    + "WHERE DEVOLUCIONES.FECHA >= '"  + fechaInicio + "' AND DEVOLUCIONES.FECHA <= '" + fechaFin + "' ";            
            if(turno >= 1 && turno <= 3)
                sql += "AND TURNO=" + turno + " ";                   
            sql+= "GROUP BY PRODUCTOS.ID_PRODUCTO " ;
            query = SQLConnection.buscar(sql);
            while(query.next()){
                incrementoProductos(
                        query.getInt("ID_PRODUCTO"), 
                        query.getString("NOMBRE"),
                        (query.getInt("CANTIDAD") * -1));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
        
    }
    
    private void incrementoProductos(int id, String nombre, int incremento){
        boolean existente = false;
        for(int i = 0 ; (!existente) && (i < listaProductos.size()) ; i++){
            Producto tempActual = listaProductos.get(i);
            if(tempActual.idProducto == id){
                tempActual.cantidad += incremento;
                existente = true;
            }
        }
        if(!existente){
            listaProductos.add(new Producto(id, nombre, incremento));
        }
    }
    
    @Override
    public int getRowCount() {
        
        return listaProductos.size();
        
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
            return listaProductos.get(rowIndex).nombre;
        } if(columnIndex == 1){
            return listaProductos.get(rowIndex).cantidad;
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