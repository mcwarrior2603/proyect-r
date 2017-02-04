package proyecto.r;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Image;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author USUARIO FINAL
 */
public class VentanaLogin extends Ventana implements ActionListener {
    
    private final JPanel panelVentana;
    
    private final Label Usuario = new Label();
    private final Label labelContraseña = new Label();
    private final JPasswordField fieldPassword = new JPasswordField();
    private final TextField UsuarioText = new TextField();    
    //private final TextField PasswordText = new TextField();
    private final JButton BotonCerrar = new JButton();
    private final JButton BotonEntrar = new JButton();
    private final JLabel labelLogo = new JLabel();
    
    public VentanaLogin(){                
        super(400, 200, NOMBRE_SW + " - Login");
        
        panelVentana = (JPanel) getContentPane();
        
        BotonEntrar.addActionListener(this);
        BotonCerrar.addActionListener(this);
        fieldPassword.addActionListener(this);
        
        panelVentana.setBackground(Color.white);
        Usuario.setText("Usuario :");
        labelContraseña.setText("Password :");
        BotonCerrar.setText("Cerrar");
        BotonEntrar.setText("Entrar");  
        
        labelLogo.setIcon(
                new ImageIcon(new ImageIcon("multimedia/" + LOGO_DEV).
                        getImage().getScaledInstance(150, 50, Image.SCALE_DEFAULT)));
        
        panelVentana.add(Usuario);
        panelVentana.add(labelContraseña);
        panelVentana.add(BotonCerrar);
        panelVentana.add(BotonEntrar);
        panelVentana.add(UsuarioText);
        panelVentana.add(fieldPassword);  
        panelVentana.add(labelLogo);
        
        Usuario.setBounds(15, 20, 100, 30);
        labelContraseña.setBounds( 15, 60, 100, 30);
        fieldPassword.setBounds( 120, 60, 200, 30);
        UsuarioText.setBounds(120, 20, 200, 30);        
        BotonEntrar.setBounds(160, 115, 100, 30);
        BotonCerrar.setBounds(265, 115, 100, 30);
        labelLogo.setBounds(5, 120, 150, 60);
        
        UsuarioText.setEnabled(true);
        labelContraseña.setEnabled(true);                  
        
        UsuarioText.requestFocus();            
        
        cerrarVentanaCarga();
        
    }
    @Override
    protected boolean confirmarCerrado(){
        if(JOptionPane.showConfirmDialog(null, "¿Confirmar cerrado?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0){
            System.exit(0);
            return true;
        }
        return false;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {     
        if(e.getSource() == BotonEntrar || e.getSource() == fieldPassword){            
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
        
        ResultSet query = SQLConnection.buscar("SELECT * FROM USUARIOS WHERE NOMBRE_USUARIO = '" + UsuarioText.getText() + "'");
        try {                        
            if(query.next())
                if(BCrypt.checkpw(fieldPassword.getText(), query.getString("CONTRASEÑA"))){
                    setVisible(false);
                                       
                    new VentanaMainGUI(new Usuario(
                            query.getInt("ID_USUARIO"), 
                            query.getString("NOMBRE_USUARIO"),
                            query.getString("CONTRASEÑA"),
                            query.getInt("NIVEL_DE_ACCESO")));                                                            
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
        
    }

}