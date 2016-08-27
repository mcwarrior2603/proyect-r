/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class ProductoFormulario extends Ventana implements ActionListener, FocusListener{
    
    public static final int AXADIR = 0;
    public static final int MODIFICAR = 1;
    public static final int ELIMINAR = 2;        
    
    private static Dimension dimensionVentana = new Dimension(370, 320);
    
    private JPanel contenedorGeneral;    
    private int uso;
    private boolean valido = false;
    
    private JLabel labelCheck = new JLabel();
    private JLabel labelTitulo = new JLabel("Control de productos");
    private JLabel labelNombre = new JLabel("Nombre");
    private JLabel labelPrecio = new JLabel("Precio");
    private JLabel labelCategoria = new JLabel("Categoria");
    private JLabel labelImagen = new JLabel("Imagen");
    
    private JTextField fieldNombre = new JTextField();
    private JTextField fieldPrecio = new JTextField();
    private JTextField fieldCategoria = new JTextField();
    private JTextField fieldImagen = new JTextField();
    
    private JButton buttonEliminar = new JButton("[X] Eliminar");
    private JButton buttonGuardar = new JButton("Guardar");
    private JButton buttonCancelar = new JButton("Cancelar");
 
    private ButtonGroup groupOpciones = new ButtonGroup();
    private JRadioButton rbuttonProducto = new JRadioButton("Producto");
    private JRadioButton rbuttonCategoria = new JRadioButton("Categoria");
    
    public ProductoFormulario(int uso){                
        
        rbuttonCategoria.setEnabled(false);
        fieldCategoria.setEnabled(false);
        
        this.uso = uso;
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);        
        
        addWindowListener(this);  
        rbuttonProducto.addActionListener(this);
        rbuttonCategoria.addActionListener(this);
        buttonEliminar.addActionListener(this);
        buttonGuardar.addActionListener(this);
        buttonCancelar.addActionListener(this);
        fieldNombre.addFocusListener(this);        
        
        this.contenedorGeneral = (JPanel) getContentPane();        
        contenedorGeneral.setLayout(null);
        
        labelTitulo.setFont(fontTitulo);
        
        groupOpciones.add(rbuttonProducto);
        groupOpciones.add(rbuttonCategoria);
                                       
        labelTitulo.setBounds(25, 15, 300, 40);
        rbuttonProducto.setBounds(15, 60, 100, 30);
        rbuttonCategoria.setBounds(120, 60, 100, 30);        
        labelNombre.setBounds(15, 95, 100, 30);
        fieldNombre.setBounds(120, 95, 200, 30);
        labelCheck.setBounds(325, 95, 30, 30);
        labelPrecio.setBounds(15, 130, 100, 30);
        fieldPrecio.setBounds(120, 130, 200, 30);
        labelCategoria.setBounds(15, 165, 100, 30);
        fieldCategoria.setBounds(120, 165, 200, 30);
        labelImagen.setBounds(15, 200, 100, 30);
        fieldImagen.setBounds(120, 200, 200, 30);  
        buttonEliminar.setBounds(10, 250, 100, 30);
        buttonGuardar.setBounds(125, 250, 100, 30);
        buttonCancelar.setBounds(230, 250, 100, 30);
        
        contenedorGeneral.add(labelTitulo);
        contenedorGeneral.add(rbuttonCategoria);
        contenedorGeneral.add(rbuttonProducto);
        contenedorGeneral.add(labelNombre);
        contenedorGeneral.add(fieldNombre);
        contenedorGeneral.add(labelCheck);
        contenedorGeneral.add(labelPrecio);
        contenedorGeneral.add(fieldPrecio);
        contenedorGeneral.add(labelCategoria);
        contenedorGeneral.add(fieldCategoria);
        contenedorGeneral.add(labelImagen);
        contenedorGeneral.add(fieldImagen);
        contenedorGeneral.add(buttonEliminar);
        contenedorGeneral.add(buttonGuardar);
        contenedorGeneral.add(buttonCancelar);
        
        configurarUso();
        
        contenedorGeneral.updateUI();
                        
    }
    
    private void configurarUso(){
        if(uso == AXADIR){
            buttonEliminar.setEnabled(false);
            labelTitulo.setText(labelTitulo.getText() + " - Añadir");
        }else if(uso == MODIFICAR){
            labelTitulo.setText(labelTitulo.getText() + " - Modificar");
        }else if(uso == ELIMINAR){
            labelTitulo.setText(labelTitulo.getText() + " - Eliminar");
            fieldPrecio.setEnabled(false);
            fieldCategoria.setEnabled(false);
            fieldImagen.setEnabled(false);
            buttonGuardar.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonEliminar){
            eliminar();
        }else if(e.getSource() == buttonGuardar){
            if(uso == AXADIR){
                añadir();
            }else{
                actualizar();
            }
        }else if(e.getSource() == buttonCancelar){
            confirmarCerrado();
        }
    }
    
    private void eliminar(){
        if(!valido)
            return;
        
        String sql = "DELETE FROM PRODUCTOS " +
                "WHERE NOMBRE='" + fieldNombre.getText() + "'";
        
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
            setVisible(false);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "No fue posible eliminar el producto");
        }
        
    }
    
    private void actualizar(){
        float precio = aFloat(fieldPrecio.getText(), "precio");
        
        if(!valido)
            return;
        if(precio == DEFAULT_AFLOAT)
            return;
        
        String sql = "UPDATE PRODUCTOS SET " + 
                "PRECIO=" + precio + "," +
                "ID_CATEGORIA=-1," +
                "IMAGEN='"+ fieldImagen.getText() + "'";
        sql += "WHERE NOMBRE='" + fieldNombre.getText() + "'";
        
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
            setVisible(false);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "No fue posible modificar el producto");
        }
    }
    
    private void añadir(){                                
        float precio = aFloat(fieldPrecio.getText(), "precio");
        
        if(!valido)        
            return;
        if(precio == DEFAULT_AFLOAT)
            return;
        
        String sql = "INSERT INTO PRODUCTOS(ID_CATEGORIA,NOMBRE,PRECIO,IMAGEN)";        
        sql += "VALUES(" +
                "-1," +
                "'" + fieldNombre.getText() + "'," +
                precio + "," +
                "'" + fieldImagen.getText() + "')";        
        
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Producto añadido correctamente");
            setVisible(false);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "No fue posible añadir el producto");
        }            
    }
    
    private void busquedaCorrecta(){
        labelCheck.setIcon(new ImageIcon(new ImageIcon("multimedia/check.png").getImage().
                            getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        valido = true;
    }
    
    private void busquedaIncorrecta(){
        labelCheck.setIcon(new ImageIcon(new ImageIcon("multimedia/failed.png").getImage().
                            getScaledInstance(30, 30, Image.SCALE_DEFAULT)));      
        valido = false;
    }
    
    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
        String sql = "SELECT * FROM PRODUCTOS WHERE NOMBRE='" + fieldNombre.getText() + "'";
        
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            if(query.next()){
                if(uso == AXADIR)
                    busquedaIncorrecta();
                else{
                    busquedaCorrecta();                
                    fieldPrecio.setText(query.getString("PRECIO"));
                    fieldCategoria.setText(query.getString("ID_CATEGORIA"));
                    fieldImagen.setText(query.getString("IMAGEN"));
                }
            }else{
                if(uso == AXADIR)
                    busquedaCorrecta();
                else
                    busquedaIncorrecta();
            }
        } catch (SQLException ex) {
            reportarError(ex);
        }
    }                
    
}
