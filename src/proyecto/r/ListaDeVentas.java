/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class ListaDeVentas extends Ventana implements ActionListener, MouseListener{
    
    private static final Dimension dimensionVentana = new Dimension(600,400);        
    
    private final JLabel labelTitulo = new JLabel("Ventas");
    private final JLabel labelAl = new JLabel("al");
    
    private final JScrollPane scrollTabla = new JScrollPane();
    private final JTable tableVentas = new JTable();
    
    private final JRadioButton rbuttonHoy = new JRadioButton("Hoy");
    private final JRadioButton rbuttonPeriodo = new JRadioButton("Periodo del");
    private final ButtonGroup bgFecha = new ButtonGroup();
    
    private final JTextField fieldFechaInicio = new JTextField();
    private final JTextField fieldFechaFin = new JTextField();
    
    private final JButton buttonBuscar = new JButton("Buscar");
    
    public ListaDeVentas(){
        
        setPreferredSize(dimensionVentana);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        
        addWindowListener(this);  
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
        
        ((JPanel)getContentPane()).updateUI();
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
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonBuscar){
            if(rbuttonHoy.isSelected()){
                tableVentas.setModel(new ModelVentas(hoy(), hoy()));
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
                tableVentas.setModel(new ModelVentas(
                        fieldFechaInicio.getText(), 
                        fieldFechaFin.getText()));
            }
        }            
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() != 2)
            return;
        
        new VentaFormulario(((ModelVentas)tableVentas.getModel()).ventas.get(tableVentas.rowAtPoint(e.getPoint())));
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

    ArrayList <Venta> ventas = new ArrayList();
    
    public ModelVentas(String fechaInicio, String fechaFin){
        
        String sql = "SELECT * FROM VENTAS "
                + "INNER JOIN USUARIOS ON VENTAS.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "WHERE VENTAS.FECHA >= '" + fechaInicio + "' AND " + "VENTAS.FECHA <= '" + fechaFin + "'";
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            while(query.next()){
                ventas.add(new Venta(
                        query.getInt("ID_VENTA"),
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
        return ventas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0)
            return "Folio";
        else if(columnIndex == 1)
            return "Cajero";
        else if(columnIndex == 2)
            return "Fecha";
        else if(columnIndex == 3)
            return "Hora";
        else 
            return "Total";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0)
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
            return ventas.get(rowIndex).id;
        else if(columnIndex == 1)
            return ventas.get(rowIndex).cajero;
        else if(columnIndex == 2)
            return ventas.get(rowIndex).fecha;
        else if(columnIndex == 3)
            return ventas.get(rowIndex).hora;
        else
            return ventas.get(rowIndex).total;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
    @Override
    public void addTableModelListener(TableModelListener l) {}
    @Override
    public void removeTableModelListener(TableModelListener l) {}
    
}
