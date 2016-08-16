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
/**
 *
 * @author USUARIO FINAL
 */
public class Login extends Ventana implements ActionListener {
    private final Dimension DimensionVentana = new Dimension(400,200);
    private final JPanel PanelVentana;
    
    private final Label Usuario = new Label();
    private final Label Password = new Label();
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
        Password.setText("Password :");
        BotonCerrar.setText("Cerrar");
        BotonEntrar.setText("Entrar");
        
        PanelVentana.add(Usuario);
        PanelVentana.add(Password);
        PanelVentana.add(BotonCerrar);
        PanelVentana.add(BotonEntrar);
        PanelVentana.add(UsuarioText);
        PanelVentana.add(PasswordText);
        
        Usuario.setBounds(15, 20, 100, 30);
        Password.setBounds(15, 60, 100, 30);
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
        
    }
}
