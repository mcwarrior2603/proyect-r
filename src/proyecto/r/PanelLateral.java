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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class PanelLateral extends PanelInterfaz implements MouseListener, ActionListener{
        
    private JScrollPane listaProductos;
    public JTable tablaVenta = new JTable();        
    private JLabel labelTotal = new JLabel("$0.0");
    private JLabel labelLogo = new JLabel();    
    
    private JButton buttonDevolución = new JButton("Venta");
    
    private JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel puntoDeVenta = new JPanel();
    private JPanel panelLista = new JPanel();
    private JPanel panelTotal = new JPanel();    
    
    private VentanaCobrar ventaActual = null;            
    private ArrayList <Producto> productos;
    private boolean devolucion = true;
    
    private static final Font letraTabla = new Font("Arial", Font.PLAIN, 15);
        
    /**
     * Configura los componentes del panel
     * @param gui Interfaz principal que contiene el panel
     * @param productos Productos contenidos en la venta
     */
    public void configurar(VentanaMainGUI gui, ArrayList <Producto> productos){
        
        super.configurar(gui);
        setLayout(new BorderLayout(5, 5));
                
        labelTotal.addMouseListener(this);
        buttonDevolución.addActionListener(this);
        
        this.productos = productos;
        
        configurarLogo();
        configurarPuntoDeVenta();                
        
        panelLogo.add(labelLogo);
        panelLogo.setOpaque(false);
        
        add(panelLogo, "North");
        add(puntoDeVenta, "Center");
                
    }
    /**
     * Se sentencia el metodo para colocar el logo en la ventana
     */
    private void configurarLogo(){
        
        labelLogo.setIcon(new ImageIcon(new ImageIcon("multimedia/logotipo_super.png").getImage()
                .getScaledInstance(gui.getWidth() / 4, gui.getHeight() / 5, Image.SCALE_DEFAULT)));    
        
    }
    /**
     * Se coloca el borde para cada elemento que conforma la ventana 
     * Se le da color a la ventana
     */
    private void configurarPuntoDeVenta(){               
        
        puntoDeVenta.setLayout(new BorderLayout(5, 5));
        puntoDeVenta.setBackground(Color.GREEN);
        
        //Coloqué el JLabel en un panel para poder separarlo del borde del panel
        labelTotal.setFont(new Font("Arial", Font.BOLD, 50));
        labelTotal.setHorizontalAlignment(JLabel.RIGHT);  
        labelTotal.setAlignmentY(TOP_ALIGNMENT);
        labelTotal.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        labelTotal.setOpaque(true);
        labelTotal.setBackground(Color.WHITE);                    
        
        buttonDevolución.setFont(Ventana.fontTitulo);        
        intercambiarVenta();
        
        panelTotal.setOpaque(false);
        panelTotal.setBorder(new EmptyBorder(5,5,5,5));
        panelTotal.setLayout(new BorderLayout(5, 5));
        panelTotal.add(labelTotal, "Center");   
        panelTotal.add(buttonDevolución, "West");
                                               
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
    /**
     * Actualiza y suma los precios de la tabla
     */
    public void actualizar(){
        
        float sumaTotal = 0;
        for(int i = 0 ; i < productos.size() ; i++){
            sumaTotal += (productos.get(i).precio * productos.get(i).cantidad);                        
        }
        
        labelTotal.setText("$" + sumaTotal); 
        
        updateUI();
                
    }        
    /**
     * Limpia la ventana y la vuelve a cero
     */
    public void limpiarVenta(){
        
        productos.clear();
        actualizar();
        devolucion = false;
        buttonDevolución.setText("Venta");
        
    }
    
    /**
     * Este metodo permite intercambiar entre devolucion y venta
     */
    public void intercambiarVenta(){
        
        devolucion = !devolucion;
        if(devolucion)
            buttonDevolución.setText("Devolucion");
        else 
            buttonDevolución.setText("Venta");
    }
    /**
     * Si se encuentra una ventatna activa se cierra y se abre una nueva 
     */
    public void disposeVentaGUI(){
        if(ventaActual != null){
            ventaActual.cerrar();
            ventaActual = null;
        }
            
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {          
        
        disposeVentaGUI();
            
        float fTotal = Ventana.aFloat(labelTotal.getText().substring(1), "Total");                                    
        if(fTotal == 0f || fTotal == Ventana.DEFAULT_AFLOAT)
            return;
                
        if(devolucion){
            if(gui.guardarDevolucion(fTotal)){
                JOptionPane.showMessageDialog(null, "Devolución guardada :)");
                limpiarVenta();
            }else{
                JOptionPane.showMessageDialog(null, "No se ha podido guardar la devolución");
            }         
        }else{            
            ventaActual = new VentanaCobrar(fTotal, gui);            
            gui.cobrando = true;
        }
        
    }        
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonDevolución)
            intercambiarVenta();
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
