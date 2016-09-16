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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import static proyecto.r.Ventana.fontTitulo;

/**
 *
 * @author MCwar
 */
public class VentanaListaEgresos extends Ventana implements ActionListener, MouseListener{

    private final JLabel labelTitulo = new JLabel("Ventas");
    private final JLabel labelAl = new JLabel("al");
    private final JLabel labelTotal = new JLabel("Total:");
    
    private final JScrollPane scrollTabla = new JScrollPane();
    private final JTable tableEgresos = new JTable();
    
    private final JRadioButton rbuttonHoy = new JRadioButton("Hoy");
    private final JRadioButton rbuttonPeriodo = new JRadioButton("Periodo del");
    private final ButtonGroup bgFecha = new ButtonGroup();
    
    private final JTextField fieldFechaInicio = new JTextField();
    private final JTextField fieldFechaFin = new JTextField();
    private final JTextField fieldTotal = new JTextField();
    
    private final JButton buttonBuscar = new JButton("Buscar");
    
    public VentanaListaEgresos(){
        super(600, 450);
        configurar("");
    }
    
    public VentanaListaEgresos(String fecha){        
        super(600,500);        
        configurar(fecha);        
    }
    
    private void configurar(String fecha){
        buttonBuscar.addActionListener(this);
        tableEgresos.addMouseListener(this);
        
        labelTitulo.setFont(fontTitulo);
        bgFecha.add(rbuttonHoy);
        bgFecha.add(rbuttonPeriodo);        
        scrollTabla.setViewportView(tableEgresos);
        tableEgresos.setModel(new ModelEgresos(hoy(), hoy()));
        
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
        
        tableEgresos.setModel(new ModelEgresos(fechaInicio, fechaFin)); 
                
        ArrayList <Transaccion> ar = 
                ((ModelVentas)tableEgresos.getModel()).transacciones;
        
        for(int i = 0 ; 
                i < ar.size() ;
                i++){
            total += ar.get(i).total;
        }
        
        fieldTotal.setText(String.valueOf(total));
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
        
        new VentanaEgreso(
                1,                
                ((ModelEgresos)tableEgresos.getModel())
                .egresos.get(tableEgresos.rowAtPoint(e.getPoint())));
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

class ModelEgresos implements TableModel{

    public ArrayList <Egreso> egresos = new ArrayList();
    
    public ModelEgresos(String fechaInicio, String fechaFin){
        
        String sql = "SELECT * FROM EGRESOS "
                + "INNER JOIN USUARIOS ON EGRESOS.ID_USUARIO = USUARIOS.ID_USUARIO "
                + "WHERE EGRESOS.FECHA >= '" + fechaInicio + "' AND EGRESOS.FECHA <= '" + fechaFin + "'";
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            while(query.next()){
                egresos.add(new Egreso(
                        query.getInt("ID_EGRESO"),
                        query.getString("FECHA"),
                        query.getFloat("MONTO"),
                        query.getString("CONCEPTO"),
                        query.getString("NOMBRE_USUARIO")));
            }
        } catch (SQLException ex) {
            Ventana.reportarError(ex);
        }                
                
    }
    
    @Override
    public int getRowCount() {
        return egresos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0)
            return "Folio";
        else if(columnIndex == 1)    
            return "Fecha";
        else if(columnIndex == 2)
            return "Monto";
        else if(columnIndex == 3)
            return "Usuario";        
        else
            return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 1)
            return Integer.class;
        else if(columnIndex == 2)
            return Float.class;
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
            return egresos.get(rowIndex).id;
        else if(columnIndex == 1)
            return egresos.get(rowIndex).fecha;
        else if(columnIndex == 2)
            return egresos.get(rowIndex).monto;
        else if(columnIndex == 3)
            return egresos.get(rowIndex).usuario;        
        else return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
    @Override
    public void addTableModelListener(TableModelListener l) {}
    @Override
    public void removeTableModelListener(TableModelListener l) {}
    
}