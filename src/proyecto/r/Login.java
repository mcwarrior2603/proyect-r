/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author USUARIO FINAL
 */
public class Login extends Ventana implements ActionListener {
    private final Dimension DimensionVentana = new Dimension(400,200);
    private final JPanel PanelVentana;
    
    private final Label Usuario = new Label();
    private final JPasswordField Contraseña = new JPasswordField();
    private final TextField UsuarioText = new TextField();
    private final TextField PasswordText = new TextField();
    private final JButton BotonCerrar = new JButton();
    private final JButton BotonEntrar = new JButton();
    
    public Login(){
        
        addWindowListener(this);
        setLayout(null);
        setPreferredSize(DimensionVentana);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        
        PanelVentana = (JPanel) getContentPane();
        
        BotonEntrar.addActionListener(this);
        BotonCerrar.addActionListener(this);
        
        Usuario.setText("Usuario :");
        Contraseña.setText("Password :");
        BotonCerrar.setText("Cerrar");
        BotonEntrar.setText("Entrar");
        
        PanelVentana.add(Usuario);
        PanelVentana.add(Contraseña);
        PanelVentana.add(BotonCerrar);
        PanelVentana.add(BotonEntrar);
        PanelVentana.add(UsuarioText);
        PanelVentana.add(PasswordText);
        
        Usuario.setBounds(15, 20, 100, 30);
        Contraseña.setBounds(15, 60, 100, 30);
        UsuarioText.setBounds(120, 20, 200, 30);
        PasswordText.setBounds(120, 60, 200, 30);
        BotonEntrar.setBounds(140, 115, 100, 30);
        BotonCerrar.setBounds(245, 115, 100, 30);
        
        UsuarioText.setEnabled(true);
        PasswordText.setEnabled(true);  
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {     
        if(e.getSource() == BotonEntrar){            
            Check();
        }else if(e.getSource() == BotonCerrar){
            confirmarCerrado();
        }
    }
    private void Check(){
        if(UsuarioText.getText().isEmpty() || UsuarioText.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Asegurese de haber ingresado\nun usuario y contraseña.", "Información faltane", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ResultSet query = SQLConnection.buscar("SELECT * FROM USERS WHERE NOMBRE_USUARIO = '" + UsuarioText.getText() + "'");
        try {                        
            if(query.next())
                if(BCrypt.checkpw(PasswordText.getText(), query.getString("CONTRASEÑA"))){
                    setVisible(false);

                    new InterfazPrincipal(new Usuario(query.getInt("ID_USUARIO"), query.getString("NOMBRE_USUARIO"),
                            query.getString("NOMBRE"), query.getInt("NIVEL_DE_ACCESO")));                    

                    dispose();                    
                }else{
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }
            else{
                JOptionPane.showMessageDialog(null, "Usuario inexistente");
            }
            
        } catch (SQLException ex) {
            reportarError(ex);
        }
        
        SQLConnection.cerrarConexion();
        
        
    }

}
