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
import javax.swing.JOptionPane;
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
    private JMenu menuProductos = new JMenu("Productos");
    private JMenu menuAyuda = new JMenu("Ayuda");

    private final JMenuItem cancelarVenta = new JMenuItem("Cancelar venta");
    private final JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
    private final JMenuItem salir = new JMenuItem("Salir");
    private final JMenuItem reporteVentas = new JMenuItem("Reporte por ventas");
    private final JMenuItem reporteProductos = new JMenuItem("Reporte por productos");
    private final JMenuItem añadirUsuario = new JMenuItem("Añadir");
    private final JMenuItem modificarUsuario = new JMenuItem("Modificar");
    private final JMenuItem eliminarUsuario = new JMenuItem("Eliminar");
    private final JMenuItem añadirProducto = new JMenuItem("Añadir");
    private final JMenuItem modificarProducto = new JMenuItem("Modificar");
    private final JMenuItem eliminarProducto = new JMenuItem("Eliminar");
    private final JMenuItem ayuda = new JMenuItem("Ayuda");
    private final JMenuItem acercaDe = new JMenuItem("Acerca de...");
    private final JMenuItem contacto = new JMenuItem("Contacto");
    
    @Override
    public void configurar(VentanaMainGUI gui){
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
        configurarMenuProductos();
        configurarMenuAyuda();
        
        barraDeMenu.add(menuArchivo);        
        barraDeMenu.add(menuReportes);
        barraDeMenu.add(menuUsuarios);
        barraDeMenu.add(menuProductos);
        barraDeMenu.add(menuAyuda);        
        
        menuArchivo.setFont(letraMenus);
        menuReportes.setFont(letraMenus);
        menuUsuarios.setFont(letraMenus);
        menuProductos.setFont(letraMenus);
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
        menuReportes.add(reporteVentas);
        menuReportes.add(reporteProductos);        
        
        reporteVentas.addActionListener(this);
        reporteProductos.addActionListener(this);
    }
    
    private void configurarMenuUsuarios(){
        menuUsuarios.add(añadirUsuario);
        menuUsuarios.add(modificarUsuario);
        menuUsuarios.add(eliminarUsuario);
        
        añadirUsuario.addActionListener(this);
        modificarUsuario.addActionListener(this);
        eliminarUsuario.addActionListener(this);
    }     
    
    private void configurarMenuProductos(){
        menuProductos.add(añadirProducto);
        menuProductos.add(modificarProducto);
        menuProductos.add(eliminarProducto);
        
        añadirProducto.addActionListener(this);
        modificarProducto.addActionListener(this);
        eliminarProducto.addActionListener(this);
    }
    
    private void configurarMenuAyuda(){
        menuAyuda.add(contacto);
        menuAyuda.addSeparator();
        menuAyuda.add(ayuda);
        menuAyuda.add(acercaDe);        
        
        contacto.addActionListener(this);
        ayuda.addActionListener(this);
        acercaDe.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelarVenta){
            gui.limpiarVenta();
        }else if(e.getSource() == cerrarSesion){
            new VentanaLogin();
            gui.setVisible(false);
            gui.dispose();
        }else if(e.getSource() == salir){
            gui.confirmarCerrado();
        }else if(e.getSource() == añadirUsuario){
            new VentanaUsuario(VentanaUsuario.AXADIR);            
        }else if(e.getSource() == modificarUsuario){
            new VentanaUsuario(VentanaUsuario.MODIFICAR);
        }else if(e.getSource() == eliminarUsuario){
            new VentanaUsuario(VentanaUsuario.ELIMINAR);
        }else if(e.getSource() == añadirProducto){
            new VentanaProducto(VentanaProducto.AXADIR, gui);
        }else if(e.getSource() == modificarProducto){
            new VentanaProducto(VentanaProducto.MODIFICAR, gui);            
        }else if(e.getSource() == eliminarProducto){
            new VentanaProducto(VentanaProducto.ELIMINAR, gui);
        }else if(e.getSource() == reporteVentas){
            if(gui.usuarioActivo.nivelDeAcceso > 1){                
                JOptionPane.showMessageDialog(null, "No tiene privilegios suficientes.");
                return;
            }
            new ListaDeVentas();
        }else if(e.getSource() == reporteProductos){
            if(gui.usuarioActivo.nivelDeAcceso > 1){                
                JOptionPane.showMessageDialog(null, "No tiene privilegios suficientes.");
                return;
            }
            new VentanaVentasProductos();
        }else if(e.getSource() == contacto){
            JOptionPane.showMessageDialog(null, "Para ayuda contactanos en mcwarrior.mendez@hotmail.com");
        }else if(e.getSource() == acercaDe){
            JOptionPane.showMessageDialog(null, "Version " + gui.version + "\nLiberada el " + gui.fechaVersion + ".");
        }            
    }
}
