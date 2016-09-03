/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author MCwar
 */
public class PanelLateral extends PanelInterfaz implements MouseListener{
        
    private JScrollPane listaProductos;
    public JTable tablaVenta = new JTable();        
    private JLabel total = new JLabel("$0.0");
    private JLabel logo = new JLabel();
    
    private JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel puntoDeVenta = new JPanel();
    private JPanel panelLista = new JPanel();
    private JPanel panelTotal = new JPanel();
    
    private CobrarVentana ventaActual = null;
    
    private static final Font letraTabla = new Font("Arial", Font.PLAIN, 15);
    
    private ArrayList <Producto> productos;
        
        
    public void configurar(InterfazPrincipal gui, ArrayList <Producto> productos){
        super.configurar(gui);
        setLayout(new BorderLayout(5, 5));
                
        total.addMouseListener(this);
        
        this.productos = productos;
        
        configurarLogo();
        configurarPuntoDeVenta();                
        
        panelLogo.add(logo);
        panelLogo.setOpaque(false);
        
        add(panelLogo, "North");
        add(puntoDeVenta, "Center");
                
    }
    
    private void configurarLogo(){
        logo.setIcon(new ImageIcon(new ImageIcon("multimedia/logotipo_super.png").getImage()
                .getScaledInstance(gui.getWidth() / 4, gui.getHeight() / 5, Image.SCALE_DEFAULT)));    
        
    }
    
    private void configurarPuntoDeVenta(){               
        
        puntoDeVenta.setLayout(new BorderLayout(5, 5));
        puntoDeVenta.setBackground(Color.GREEN);
        
        //Coloqué el JLabel en un panel para poder separarlo del borde del panel
        total.setFont(new Font("Arial", Font.BOLD, 50));
        total.setHorizontalAlignment(JLabel.RIGHT);  
        total.setAlignmentY(TOP_ALIGNMENT);
        total.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        total.setOpaque(true);
        total.setBackground(Color.WHITE);    
        
        panelTotal.setOpaque(false);
        panelTotal.setBorder(new EmptyBorder(5,5,5,5));
        panelTotal.setLayout(new BorderLayout());
        panelTotal.add(total, "Center");        
                                               
        tablaVenta.setMaximumSize(new Dimension((int)(gui.getWidth() / 4.5), 0));
        tablaVenta.setModel(new ModelProductos(productos));                
        tablaVenta.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaVenta.getColumnModel().getColumn(0).setMaxWidth((int)(gui.getWidth() / 4));
        tablaVenta.setFont(letraTabla);
        tablaVenta.setRowHeight(30);        
        tablaVenta.setShowVerticalLines(false);
        tablaVenta.setShowHorizontalLines(false);        
        tablaVenta.setBackground(Color.WHITE);
        
        listaProductos = new JScrollPane(tablaVenta); 
        
        listaProductos.setBorder(new EmptyBorder(5,5,5,5));                        
        listaProductos.getViewport().setOpaque(false);
        listaProductos.setBackground(Color.WHITE);                
        
        panelLista.setLayout(new BorderLayout());
        panelLista.add(listaProductos);
        
        puntoDeVenta.add(panelLista, "Center");
        puntoDeVenta.add(panelTotal, "South");                                
        
    }        
    
    public void actualizar(){
        float sumaTotal = 0;
        for(int i = 0 ; i < productos.size() ; i++){
            sumaTotal += (productos.get(i).precio * productos.get(i).cantidad);                        
        }
        
        total.setText("$" + sumaTotal); 
        
        updateUI();
    }        
    
    @Override
    public void mouseClicked(MouseEvent e) {                  
        if(!gui.cobrando){
            float fTotal = Ventana.aFloat(total.getText().substring(1), "Total");                    
            
            if(fTotal == 0f || fTotal == Ventana.DEFAULT_AFLOAT)
                return;
            
            ventaActual = new CobrarVentana(fTotal, gui);            
            gui.cobrando = true;
        }else{
            ventaActual.setVisible(false);
            ventaActual.dispose();
            
            float fTotal = Ventana.aFloat(total.getText().substring(1), "Total");                    
            
            if(fTotal == 0f || fTotal == Ventana.DEFAULT_AFLOAT)
                return;
            
            ventaActual = new CobrarVentana(fTotal, gui);            
            gui.cobrando = true;
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    
    
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
