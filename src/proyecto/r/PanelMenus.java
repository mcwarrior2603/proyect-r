/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author MCwar
 */
public class PanelMenus extends PanelInterfaz implements ActionListener{        
    
    private Font letraMenus = new Font("Arial", Font.PLAIN, 15);
    
    private JMenuBar barraDeMenu = new JMenuBar();        
    
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenu menuReportes = new JMenu("Reportes");
    private JMenu menuUsuarios = new JMenu("Usuario");
    private JMenu menuAyuda = new JMenu("Ayuda");

    private JMenuItem cancelarVenta = new JMenuItem("Cancelar venta");
    private JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
    private JMenuItem salir = new JMenuItem("Salir");
    private JMenuItem ventasDeHoy = new JMenuItem("Ventas de hoy");
    private JMenuItem ventas = new JMenuItem("Ventas");
    private JMenuItem añadir = new JMenuItem("Añadir");
    private JMenuItem modificar = new JMenuItem("Modificar");
    private JMenuItem eliminar = new JMenuItem("Eliminar");
    private JMenuItem ayuda = new JMenuItem("Ayuda");
    private JMenuItem acercaDe = new JMenuItem("Acerca de...");
    
    @Override
    public void configurar(InterfazPrincipal gui){
        super.configurar(gui);                
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 2));
        
        barraDeMenu.setOpaque(false);
        menuArchivo.setOpaque(false);
        menuReportes.setOpaque(false);                
        menuUsuarios.setOpaque(false);
        menuAyuda.setOpaque(false);
        
        configurarMenuArchivo();
        configurarMenuReportes();
        configurarMenuUsuarios();
        
        barraDeMenu.add(menuArchivo);        
        barraDeMenu.add(menuReportes);
        barraDeMenu.add(menuUsuarios);
        barraDeMenu.add(menuAyuda);
        
        menuArchivo.setFont(letraMenus);
        menuReportes.setFont(letraMenus);
        menuUsuarios.setFont(letraMenus);
        menuAyuda.setFont(letraMenus);                
        
        add(barraDeMenu);
        
    }
    
    private void configurarMenuArchivo(){
        menuArchivo.add(cancelarVenta);
        menuArchivo.add(cerrarSesion);
        menuArchivo.addSeparator();
        menuArchivo.add(salir);
        
        cancelarVenta.addActionListener(this);
        cerrarSesion.addActionListener(this);
        salir.addActionListener(this);
                
    }
    
    private void configurarMenuReportes(){
        menuReportes.add(ventasDeHoy);
        menuReportes.add(ventas);        
        
        ventasDeHoy.addActionListener(this);
        ventas.addActionListener(this);
    }
    
    private void configurarMenuUsuarios(){
        menuUsuarios.add(añadir);
        menuUsuarios.add(modificar);
        menuUsuarios.add(eliminar);
        
        añadir.addActionListener(this);
        modificar.addActionListener(this);
        eliminar.addActionListener(this);
    }     
    
    private void configurarMenuAyuda(){
        menuAyuda.add(ayuda);
        menuAyuda.add(acercaDe);
        
        ayuda.addActionListener(this);
        acercaDe.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelarVenta){
            gui.productosVenta.clear();
        }else if(e.getSource() == cerrarSesion){
            new Login();
            gui.setVisible(false);
            gui.dispose();
        }else if(e.getSource() == salir){
            gui.confirmarCerrado();
        }            
    }
}
