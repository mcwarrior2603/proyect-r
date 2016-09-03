/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author MCwar
 */
public class VentanaUsuario extends Ventana implements ActionListener, FocusListener{
        
    public static final int AXADIR = 0;
    public static final int MODIFICAR = 1;
    public static final int ELIMINAR = 2;    
                
    public static Dimension dimensionVentana = new Dimension(420, 350);    
    private boolean valido = true;
    private String master;    
    public int uso;
    
    public  JPanel contenedorGeneral;
    
    private JLabel labelCheck = new JLabel();
    private JLabel labelTitulo = new JLabel("Control de usuarios");
    private JLabel labelNombre = new JLabel("Nombre de usuario");
    private JLabel labelContraseña = new JLabel("Contraseña");
    private JLabel labelConfirmar = new JLabel("Confirme contraseña");
    private JLabel labelPermisos = new JLabel("Permisos");
    private JLabel labelAutorizar = new JLabel("Contraseña general");
    
    private JTextField fieldNombre = new JTextField();
    private JPasswordField fieldContraseña = new JPasswordField();
    private JPasswordField fieldConfirmar = new JPasswordField();
    private JPasswordField fieldAutorizar = new JPasswordField();
    
    private JButton buttonGuardar = new JButton("Guardar");
    private JButton buttonCancelar = new JButton("Cancelar");
    private JButton buttonEliminar = new JButton("Eliminar [X]");
    
    private JComboBox comboPermisos = new JComboBox();        
    
    public VentanaUsuario(int uso){
        
        this.master = Ventana.obtenerMaster();
        this.uso = uso;
        
        setLayout(null);
        setPreferredSize(dimensionVentana);
        setResizable(false);        
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
                
        addWindowListener(this);
                        
        contenedorGeneral = (JPanel) getContentPane();
        
        labelTitulo.setFont(fontTitulo);              
        
        comboPermisos.addItem("Cajero");
        comboPermisos.addItem("Administrador");        
        
        fieldNombre.addFocusListener(this);
        fieldContraseña.addFocusListener(this);
        fieldConfirmar.addFocusListener(this);
        buttonEliminar.addActionListener(this);
        buttonGuardar.addActionListener(this);
        buttonCancelar.addActionListener(this);
        
        configurarPosiciones();
        
        contenedorGeneral.add(labelTitulo);
        contenedorGeneral.add(labelNombre);
        contenedorGeneral.add(labelContraseña);
        contenedorGeneral.add(labelConfirmar);
        contenedorGeneral.add(labelPermisos);
        contenedorGeneral.add(labelAutorizar);
        contenedorGeneral.add(fieldNombre);
        contenedorGeneral.add(fieldContraseña);
        contenedorGeneral.add(fieldConfirmar);
        contenedorGeneral.add(fieldAutorizar);
        contenedorGeneral.add(buttonEliminar);
        contenedorGeneral.add(buttonGuardar);
        contenedorGeneral.add(buttonCancelar);
        contenedorGeneral.add(labelCheck);
        contenedorGeneral.add(comboPermisos);
        
        configurarComponentes(uso);
        
        contenedorGeneral.updateUI();                
    }        
    
    private void configurarPosiciones(){
        labelTitulo.setBounds(35, 15, 200, 50);
        labelNombre.setBounds(15, 70, 200, 30);        
        labelContraseña.setBounds(15, 105, 200, 30);
        labelConfirmar.setBounds(15, 140, 200, 30);
        labelPermisos.setBounds(15, 175, 200, 30);
        labelAutorizar.setBounds(15, 210, 200, 30);
        fieldNombre.setBounds(170, 70, 200, 30);
        fieldContraseña.setBounds(170, 105, 200, 30);
        fieldConfirmar.setBounds(170, 140, 200, 30);
        comboPermisos.setBounds(170, 175, 200, 30);
        fieldAutorizar.setBounds(170, 210, 200, 30);
        buttonEliminar.setBounds(25, 255, 100, 30);
        buttonGuardar.setBounds(175, 255, 100, 30);
        buttonCancelar.setBounds(280, 255, 100, 30);        
        labelCheck.setBounds(375, 70, 30, 30);
    }

    private void configurarComponentes(int uso){
        if(uso == AXADIR){
            buttonEliminar.setEnabled(false);            
        }else if(uso == MODIFICAR){
            
        }else if(uso == ELIMINAR){
            fieldContraseña.setEnabled(false);
            fieldConfirmar.setEnabled(false);
            comboPermisos.setEnabled(false);
            buttonGuardar.setEnabled(false);
        }
        
        master = obtenerMaster();
    }        
    
    @Override
    protected void confirmarCerrado(){
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Confirmacíon",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch(opcion){
            case 0:
                if(!añadirUsuario())
                    return;
                setVisible(false);
                dispose();
                break;
            case 1:
                setVisible(false);
                dispose();
                break;                                                                
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonGuardar){
            if(uso == AXADIR){
                if(!checkValido())      
                    return;
                if(!checkContraseña())
                    return;                
                if(!checkAutorizacion())
                    return;
                            
                añadirUsuario();
            }else if(uso == MODIFICAR){
                if(!checkValido())
                    return;
                if(!checkContraseña())
                    return;
                if(!checkAutorizacion())
                    return;
                
                modificarUsuario();                
            }
        }else if(e.getSource() == buttonCancelar){
            if(!fieldNombre.getText().isEmpty()){
                confirmarCerrado();
            }else{
                setVisible(false);
                dispose();
            }
        }else if(e.getSource() == buttonEliminar){
            if(!checkValido())
                return;
            if(!checkAutorizacion())
                return;            
            eliminarUsuario();
        }        
    }

    private boolean eliminarUsuario(){
        
        String sql = "DELETE FROM USUARIOS WHERE NOMBRE_USUARIO = '" + fieldNombre.getText() + "'";
            
        if(SQLConnection.actualizar(sql)){
           JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
           setVisible(false);
           dispose();
           return true;
        }else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");            
            return false;
        }
    }
    
    private boolean añadirUsuario(){
        String sql = "INSERT INTO USUARIOS(NOMBRE_USUARIO, CONTRASEÑA, NIVEL_DE_ACCESO) ";
             
        sql += "VALUES(";
        sql += "'" + fieldNombre.getText() + "',";
        sql += "'" + BCrypt.hashpw(fieldContraseña.getText(), BCrypt.gensalt()) + "',";        
        if(((String)comboPermisos.getSelectedItem()) == "Administrador")
            sql += "1";
        else 
            sql += "2";
        sql += ")";
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Usuario añadido correctamente");
            setVisible(false);
            dispose();
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error! :(");
            return false;
        }
    }
    
    private boolean modificarUsuario(){
        String sql = "UPDATE USUARIOS SET "
                + "CONTRASEÑA='" + BCrypt.hashpw(fieldContraseña.getText(), BCrypt.gensalt()) + "',"
                + "NIVEL_DE_ACCESO=" + obtenerNivelAcceso() + " "
                + "WHERE NOMBRE_USUARIO = '" + fieldNombre.getText() + "'";                
        
        if(SQLConnection.actualizar(sql)){
            JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
            setVisible(false);
            dispose();
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error :(");
            return false;
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == fieldContraseña)
            fieldContraseña.setText("");
        if(e.getSource() == fieldConfirmar)
            fieldConfirmar.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == fieldNombre){
            ResultSet query = SQLConnection.buscar("SELECT * FROM USUARIOS WHERE NOMBRE_USUARIO = '" + fieldNombre.getText() + "'");
            if(query == null)
                return;            
            try {
                if(query.next()){
                    if(uso == AXADIR)
                        busquedaIncorrecta();
                    else
                        busquedaCorrecta();
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
    
    private boolean checkAutorizacion(){
        if(!BCrypt.checkpw(fieldAutorizar.getText(), master)){
            JOptionPane.showMessageDialog(null, "La contraseña de autorización no es correcta");
            return false;
        }
        
        return true;        
    }
    
    private boolean checkContraseña(){
        if(!fieldContraseña.getText().equals(fieldConfirmar.getText())){
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden :(");
            return false;
        }   
        
        return true;
    }
    
    private boolean checkValido(){
        if(!valido){
            JOptionPane.showMessageDialog(null, "Usuario inválido :(");
            return false;
        } 
        
        return true;
    }
    
    private int obtenerNivelAcceso(){
        if(((String)comboPermisos.getSelectedItem()).equalsIgnoreCase("ADMINISTRADOR"))
            return 1;
        else if(((String)comboPermisos.getSelectedItem()).equalsIgnoreCase(("CAJERO")))
            return 2;
        
        else return -1;
    }
}
