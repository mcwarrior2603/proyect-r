/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class ProductoFormulario extends Ventana{
    
    public static final int AXADIR = 0;
    public static final int MODIFICAR = 1;
    public static final int ELIMINAR = 2;
    
    private static Dimension dimensionVentana = new Dimension(350, 320);
    
    private JPanel contenedorGeneral;    
    private int uso;
    
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
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(dimensionVentana);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);        
        
        addWindowListener(this);        
        
        this.uso = uso;
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
        contenedorGeneral.add(labelPrecio);
        contenedorGeneral.add(fieldPrecio);
        contenedorGeneral.add(labelCategoria);
        contenedorGeneral.add(fieldCategoria);
        contenedorGeneral.add(labelImagen);
        contenedorGeneral.add(fieldImagen);
        contenedorGeneral.add(buttonEliminar);
        contenedorGeneral.add(buttonGuardar);
        contenedorGeneral.add(buttonCancelar);
        
    }
    
    
    
}
