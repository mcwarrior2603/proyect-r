/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author MCwar
 */
public class PanelLateral extends PanelInterfaz{

    private JLabel logo = new JLabel();
    private JPanel puntoDeVenta = new JPanel();
    private JScrollPane listaProductos;
    private JTable tablaVenta = new JTable();
    private ArrayList <Producto> productos = new ArrayList();
    private JLabel total = new JLabel("$0.0");
    
    @Override
    public void configurar(InterfazPrincipal gui){
        super.configurar(gui);
        setLayout(new BorderLayout(5, 5));
                
        configurarLogo();
        configurarPuntoDeVenta();
        
        add(logo, "North");
        add(puntoDeVenta, "Center");
                
    }
    
    private void configurarLogo(){
        logo.setIcon(new ImageIcon(new ImageIcon("multimedia/logotipo.png").getImage()
                .getScaledInstance(gui.getWidth() / 4, gui.getHeight() / 5, Image.SCALE_DEFAULT)));    
        
    }
    
    private void configurarPuntoDeVenta(){
        listaProductos = new JScrollPane();        
        
        puntoDeVenta.setLayout(new BorderLayout(5, 5));
        puntoDeVenta.setBackground(Color.GREEN);
        
        total.setFont(new Font("Arial", Font.BOLD, 50));
        total.setHorizontalAlignment(JLabel.RIGHT);  
        total.setAlignmentY(TOP_ALIGNMENT);
        total.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        total.setOpaque(true);
        total.setBackground(Color.WHITE);        
                
        tablaVenta.setModel(new ModelProductos(productos));
        listaProductos.setViewportView(tablaVenta);        
        tablaVenta.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaVenta.getColumnModel().getColumn(0).setMaxWidth((int)(gui.getWidth() / 4));
        
        
        puntoDeVenta.add(listaProductos, "Center");
        puntoDeVenta.add(total, "South");                                
        
    }
    
    private void actualizar(){
        float sumaTotal = 0;
        for(int i = 0 ; i < productos.size() ; i++){
            sumaTotal += (productos.get(i).precio * productos.get(i).cantidad);                        
        }
        
        total.setText("$" + sumaTotal);        
    }
    
    class ModelProductos implements TableModel{

        ArrayList <Producto> productos;        
        
        public ModelProductos(ArrayList <Producto> productos){
            this.productos = productos;
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
            if(columnIndex == 0)
                return "Producto";
            else if(columnIndex == 1)
                return "Cantidad";
            else 
                return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex == 0)
                return String.class;
            else if(columnIndex == 1)
                return Integer.class;
            else
                return Object.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex == 0)
                return productos.get(rowIndex).nombre;
            else if(columnIndex == 1)
                return productos.get(rowIndex).cantidad;
            else 
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