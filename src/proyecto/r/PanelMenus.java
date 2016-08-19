/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author MCwar
 */
public class PanelMenus extends PanelInterfaz implements MenuListener{        
    
    private Font letraMenus = new Font("Arial", Font.PLAIN, 15);
    
    private JMenuBar barraDeMenu = new JMenuBar();        
    
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenu menuReportes = new JMenu("Reportes");
    private JMenu menuUsuarios = new JMenu("Usuario");
    private JMenu menuAyuda = new JMenu("Ayuda");
    
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
        
        menuArchivo.addMenuListener(this);
        menuReportes.addMenuListener(this);
        menuUsuarios.addMenuListener(this);
        menuAyuda.addMenuListener(this);
        
        add(barraDeMenu);
        
    }
    
    private void configurarMenuArchivo(){
        menuArchivo.add("Cancelar venta");
        menuArchivo.add("Cerrar sesión");
        menuArchivo.addSeparator();
        menuArchivo.add("Salir");
                
    }
    
    private void configurarMenuReportes(){
        menuReportes.add("Ventas de hoy");
        menuReportes.add("Ventas generales");        
    }
    
    private void configurarMenuUsuarios(){
        menuUsuarios.add("Añadir");
        menuUsuarios.add("Modificar");
        menuUsuarios.add("Eliminar");
    }  

    @Override
    public void menuSelected(MenuEvent e) {        
        System.out.println("Selected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("Deselected");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("Canceled");
    }
    
}
