/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author MCwar
 */

public class VentanaListaVentas extends Ventana implements ActionListener, MouseListener{        
    
    private final JLabel labelTitulo = new JLabel("Ventas");
    private final JLabel labelAl = new JLabel("al");
    private final JLabel labelTotal = new JLabel("Total:");    
    
    private final JScrollPane scrollTabla = new JScrollPane();
    private final JTable tableVentas = new JTable();
    
    private final JRadioButton rbuttonHoy = new JRadioButton("Hoy");
    private final JRadioButton rbuttonPeriodo = new JRadioButton("Periodo del");
    private final ButtonGroup bgFecha = new ButtonGroup();
    
    private final JTextField fieldFechaInicio = new JTextField();
    private final JTextField fieldFechaFin = new JTextField();
    private final JTextField fieldTotal = new JTextField();
    
    private final JButton buttonBuscar = new JButton("Buscar");
    
    private VentanaMainGUI gui;
    
    public VentanaListaVentas(VentanaMainGUI gui){
        super(600, 450, NOMBRE_SW + " - Ventas");
        configurar(gui, "");
    }
    
    public VentanaListaVentas(VentanaMainGUI gui, String fecha){        
        super(600,430, NOMBRE_SW + " - Ventas");        
        configurar(gui, fecha);        
    }
    
    private void configurar(VentanaMainGUI gui, String fecha){        
        
        this.gui = gui;                
        
        if(gui.ventVentas != null)
            gui.ventVentas.cerrar();
        gui.ventVentas = this;
        
        buttonBuscar.addActionListener(this);
        tableVentas.addMouseListener(this);
        
        labelTitulo.setFont(fontTitulo);
        bgFecha.add(rbuttonHoy);
        bgFecha.add(rbuttonPeriodo);        
        scrollTabla.setViewportView(tableVentas);
        tableVentas.setModel(new ModelVentas(hoy(), hoy()));
        
        rbuttonHoy.setSelected(true);
        
        configurarPosiciones();
        
        add(labelTitulo);
        add(rbuttonHoy);
        add(rbuttonPeriodo);
        add(fieldFechaInicio);
        add(labelAl);
        add(fieldFechaFin);
        add(buttonBuscar);
        add(scrollTabla);
        add(labelTotal);
        add(fieldTotal);
        
        fieldTotal.setEditable(false);
        
        ((JPanel)getContentPane()).updateUI();
        
        if(!fecha.trim().isEmpty()){
            buscar(fecha, fecha);
        }
        
    }
    
    private void configurarPosiciones(){
        labelTitulo.setBounds(25, 15, 100, 30);
        rbuttonHoy.setBounds(15, 50, 100, 30);
        rbuttonPeriodo.setBounds(120, 50, 100, 30);
        fieldFechaInicio.setBounds(225, 50, 75, 30);
        labelAl.setBounds(305, 50, 20, 30);
        fieldFechaFin.setBounds(330, 50, 75, 30);
        buttonBuscar.setBounds(410, 50, 100, 30);
        scrollTabla.setBounds(15, 90, 550, 250);
        labelTotal.setBounds(400, 345, 50, 30);
        fieldTotal.setBounds(455, 345, 100, 30);
    }   

    private void buscar(){
        buscar(hoy(), hoy());
    }
    
    private void buscar(String fechaInicio, String fechaFin){
        float total = 0;
        
        tableVentas.setModel(new ModelVentas(fechaInicio, fechaFin)); 
        
        ArrayList <Transaccion> ar = 
                ((ModelVentas)tableVentas.getModel()).transacciones;
        
        for(int i = 0 ; 
                i < ar.size() ;
                i++){
            if(ar.get(i).concepto == Transaccion.VENTA)
                total += ar.get(i).total;
            else 
                total -= ar.get(i).total;
        }
        
        fieldTotal.setText(String.valueOf(total));
    }
    
    @Override
    public void cerrar(){
        super.cerrar();
        gui.ventVentas = null;
    }
    
    @Override
    protected boolean confirmarCerrado(){
        System.out.println("Cerrando");
        if(super.confirmarCerrado()){
            cerrar();
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonBuscar){
            if(rbuttonHoy.isSelected()){
                buscar();
            }else{
                if(!checkFecha(fieldFechaInicio.getText())){
                    JOptionPane.showMessageDialog(null, "Fecha de inicio incorrecta.\n"
                            + "El formato correcto es AAAA/MM/DD");
                    return;
                }
                if(!checkFecha(fieldFechaFin.getText())){
                    JOptionPane.showMessageDialog(null, "Fecha de fin incorrecta.\n"
                            + "El formato correcto es AAAA/MM/DD");
                    return;
                }
                buscar(fieldFechaInicio.getText(), 
                        fieldFechaFin.getText());
            }
        }            
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() != 2)
            return;
        
        new VentanaTransaccion(
                gui,
                ((ModelVentas)tableVentas.getModel()).
                        transacciones.get(
                                tableVentas.rowAtPoint(e.getPoint())));
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}

class ModelVentas implements TableModel{

    ArrayList <Transaccion> transacciones = new ArrayList();
    
    public ModelVentas(String fechaInicio, String fechaFin){
        
        String sql = "SELECT * FROM VENTAS "
                + "INNER JOIN USUARIOS ON VENTAS.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "WHERE VENTAS.FECHA >= '" + fechaInicio + "' AND VENTAS.FECHA <= '" + fechaFin + "'";
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            while(query.next()){
                transacciones.add(new Transaccion(
                        query.getInt("ID_VENTA"),
                        Transaccion.VENTA,
                        query.getString("FECHA"),
                        query.getString("NOMBRE_USUARIO"),
                        query.getString("HORA"),
                        query.getFloat("TOTAL")));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
        
        sql = "SELECT * FROM DEVOLUCIONES "
                + "INNER JOIN USUARIOS ON DEVOLUCIONES.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "WHERE DEVOLUCIONES.FECHA >= '" + fechaInicio + "' AND DEVOLUCIONES.FECHA <= '" + fechaFin + "'";
        query = SQLConnection.buscar(sql);
        try {
            while(query.next()){
                transacciones.add(new Transaccion(
                        query.getInt("ID_DEVOLUCION"), 
                        Transaccion.DEVOLUCION, 
                        query.getString("FECHA"), 
                        query.getString("NOMBRE_USUARIO"), 
                        query.getString("HORA"),
                        query.getFloat("TOTAL")));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }
                
    }
    
    @Override
    public int getRowCount() {
        return transacciones.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0)
            return "Concepto";
        else if(columnIndex == 1)    
            return "Folio";
        else if(columnIndex == 2)
            return "Cajero";
        else if(columnIndex == 3)
            return "Fecha";
        else if(columnIndex == 4)
            return "Hora";
        else if(columnIndex == 5)            
            return "Total";
        else 
            return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 1)
            return Integer.class;
        else 
            return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return transacciones.get(rowIndex).concepto;
        else if(columnIndex == 1)
            return transacciones.get(rowIndex).id;
        else if(columnIndex == 2)
            return transacciones.get(rowIndex).cajero;
        else if(columnIndex == 3)
            return transacciones.get(rowIndex).fecha;
        else if(columnIndex == 4)
            return transacciones.get(rowIndex).hora;
        else if(columnIndex == 5)
            return transacciones.get(rowIndex).total;
        else return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
    @Override
    public void addTableModelListener(TableModelListener l) {}
    @Override
    public void removeTableModelListener(TableModelListener l) {}
    
}
