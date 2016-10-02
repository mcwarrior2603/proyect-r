/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author MCwar
 */
public class VentanaTransaccion extends Ventana{                
    
    private JLabel labelTitulo = new JLabel();
    private JLabel labelFolio = new JLabel("Folio:");
    private JLabel labelFecha = new JLabel("Fecha:");
    private JLabel labelCajero = new JLabel("Cajero:");
    private JLabel labelHora = new JLabel("Hora:");
    private JLabel labelTotal = new JLabel("Total:");
    
    private JTextField fieldFolio = new JTextField();
    private JTextField fieldFecha = new JTextField();
    private JTextField fieldCajero = new JTextField();
    private JTextField fieldHora = new JTextField();
    private JTextField fieldTotal = new JTextField();
    
    private JTable tableProductos = new JTable();
    private JScrollPane scrollProductos = new JScrollPane();
    
    private JPanel panelPrincipal;
    
    private Transaccion transaccionActiva;
    private VentanaMainGUI gui;
    
    private static final Color COLOR_COMPONENTES = new Color(0xD9D9D9);
    
    public VentanaTransaccion(VentanaMainGUI gui, Transaccion ventaActiva){
        super(500, 600, NOMBRE_SW + " - " + ventaActiva.concepto);
        
        this.transaccionActiva = ventaActiva;                        
        this.gui = gui;
        
        if(gui.ventTransaccion != null)
            gui.ventTransaccion.cerrar();
        gui.ventTransaccion = this;
        
        panelPrincipal = (JPanel)getContentPane();        
        panelPrincipal.setBackground(Color.WHITE);
        
        configurarComponentes();
        
        labelTitulo.setFont(fontTitulo);
        fieldTotal.setFont(fontTitulo);
        
        panelPrincipal.add(labelTitulo);
        panelPrincipal.add(labelFolio);
        panelPrincipal.add(fieldFolio);
        panelPrincipal.add(labelFecha);
        panelPrincipal.add(fieldFecha);
        panelPrincipal.add(labelCajero);
        panelPrincipal.add(fieldCajero);
        panelPrincipal.add(labelHora);
        panelPrincipal.add(fieldHora);
        panelPrincipal.add(scrollProductos);
        panelPrincipal.add(labelTotal);
        panelPrincipal.add(fieldTotal);
        
        asignarValores();
        
    }
    
    private void configurarComponentes(){
        labelTitulo.setText(transaccionActiva.concepto);
        
        tableProductos.setModel(new ModelProductos(transaccionActiva));
        scrollProductos.setViewportView(tableProductos);
        
        labelTitulo.setBounds(25, 10, 200, 30);
        labelFolio.setBounds(15, 45, 100, 30);
        fieldFolio.setBounds(120, 45, 100, 30);
        labelFecha.setBounds(320, 45, 100, 30);
        fieldFecha.setBounds(370, 45, 100, 30);
        labelCajero.setBounds(15, 80, 150, 30);
        fieldCajero.setBounds(120, 80, 150, 30);
        labelHora.setBounds(15, 115, 100, 30);
        fieldHora.setBounds(120, 115, 100, 30);
        scrollProductos.setBounds(15, 150, 470, 350);
        labelTotal.setBounds(280, 505, 100, 30);
        fieldTotal.setBounds(325, 510, 150, 40);
                
        fieldFolio.setEditable(false);
        fieldFecha.setEditable(false);
        fieldCajero.setEditable(false);
        fieldHora.setEditable(false);
        fieldTotal.setEditable(false);
        
        fieldFolio.setBackground(COLOR_COMPONENTES);
        fieldFecha.setBackground(COLOR_COMPONENTES);
        fieldCajero.setBackground(COLOR_COMPONENTES);
        fieldHora.setBackground(COLOR_COMPONENTES);
        fieldTotal.setBackground(COLOR_COMPONENTES);
    }
    
    private void asignarValores(){
        fieldFolio.setText(String.valueOf(transaccionActiva.id));
        fieldFecha.setText(transaccionActiva.fecha);
        fieldCajero.setText(transaccionActiva.cajero);
        fieldHora.setText(transaccionActiva.hora);
        fieldTotal.setText(String.valueOf(transaccionActiva.total));
    }
    
    @Override
    public void cerrar(){
        super.cerrar();
        gui.ventTransaccion = null;
    }
    
    @Override
    protected boolean confirmarCerrado(){
        if(super.confirmarCerrado()){
            cerrar();
            return true;
        }
        return false;
    }
    
    class ModelProductos implements TableModel{

        ArrayList <Producto> productos = new ArrayList();
        Transaccion transaccionActiva;
        
        String sql = "";
        
        public ModelProductos(Transaccion param){
            transaccionActiva = param;
            
            if(transaccionActiva.concepto == transaccionActiva.VENTA){                
                sql = "SELECT * FROM PRODUCTOS_VENTAS " 
                        + "INNER JOIN PRODUCTOS ON PRODUCTOS.ID_PRODUCTO = PRODUCTOS_VENTAS.ID_PRODUCTO "
                        + "INNER JOIN VENTAS ON VENTAS.ID_VENTA = PRODUCTOS_VENTAS.ID_VENTA "
                        + "WHERE VENTAS.ID_VENTA = " + transaccionActiva.id;
            }else{
                sql = "SELECT * FROM PRODUCTOS_DEVOLUCIONES "
                        + "INNER JOIN PRODUCTOS ON PRODUCTOS.ID_PRODUCTO = PRODUCTOS_DEVOLUCIONES.ID_PRODUCTO "
                        + "INNER JOIN DEVOLUCIONES ON DEVOLUCIONES.ID_DEVOLUCION = PRODUCTOS_DEVOLUCIONES.ID_DEVOLUCION "
                        + "WHERE DEVOLUCIONES.ID_DEVOLUCION = " + transaccionActiva.id;
            }
            ResultSet query = SQLConnection.buscar(sql);
            
            try {
                while(query.next()){
                    productos.add(new Producto(
                            query.getInt("ID_PRODUCTO"), 
                            query.getString("NOMBRE"), 
                            query.getFloat("PRECIO"), 
                            query.getInt("ID_CATEGORIA"),
                            query.getString("IMAGEN"), 
                            query.getInt("CANTIDAD")));
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
            return 4;
        }

        @Override
        public String getColumnName(int columnIndex) {
            if(columnIndex == 0)
                return "Cantidad";
            else if(columnIndex == 1)
                return "Producto";
            else if(columnIndex == 2)
                return "P. Unitario";
            else if(columnIndex == 3)
                return "P. Total";
            
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex == 0)
                return Integer.class;
            else if(columnIndex == 1)
                return String.class;
            else if(columnIndex == 2)
                return Float.class;
            else if(columnIndex == 3)
                return Float.class;
            
            return Object.class;
        }        

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex == 0)
                return productos.get(rowIndex).cantidad;
            else if(columnIndex == 1)
                return productos.get(rowIndex).nombre;
            else if(columnIndex == 2)
                return productos.get(rowIndex).precio;
            else if(columnIndex == 3)
                return (productos.get(rowIndex).precio * productos.get(rowIndex).cantidad);            
            return "";
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
        @Override
        public void addTableModelListener(TableModelListener l) {}
        @Override
        public void removeTableModelListener(TableModelListener l) {}
        
    }
    
}
