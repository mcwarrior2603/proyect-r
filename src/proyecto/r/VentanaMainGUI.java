/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MCwar
 */
public class VentanaMainGUI extends Ventana{
                        
    public static String version = "1.3.1";
    public static String fechaVersion = "20/09/2016";
    
    private JLabel logo = new JLabel();
    
    private JPanel panelPrincipal;
    public PanelMenus panelMenus = new PanelMenus();    
    public PanelLateral panelLateral = new PanelLateral();   
    public PanelProductos panelProductos = new PanelProductos(); 
    
    public ArrayList <Producto> productosVenta = new ArrayList();        
    
    public boolean cobrando = false;
    public Usuario usuarioActivo;
    
    public Timer timerRecordatorio = new Timer();;
    
    public VentanaMainGUI(Usuario usuarioActivo){                        
        super(300, 300);
        
        this.usuarioActivo = usuarioActivo;                
                                
        setExtendedState(MAXIMIZED_BOTH);                
        setResizable(true);                
        
        panelPrincipal = (JPanel) getContentPane();
        panelPrincipal.setLayout(new BorderLayout(15, 15));
        panelPrincipal.setBackground(new Color(0xfffc00));                
        
        panelMenus.configurar(this);
        panelLateral.configurar(this, productosVenta);
        panelProductos.configurar(this);
        
        panelPrincipal.add(panelProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelLateral, BorderLayout.WEST);
        panelPrincipal.add(panelMenus, BorderLayout.NORTH);                                        
        
        panelLateral.actualizar();
        
        panelPrincipal.updateUI();
        
        if(!checkAperturaDeCaja()){
            if(JOptionPane.showConfirmDialog(null, "¿Registrar corte de caja?\n"
                    + "Usted lo puede hacer manualmente después o\n"
                    + "le recordaremos de nuevo en unos minutos.", 
                    "Corte de caja", JOptionPane.YES_NO_OPTION) == 0){
                new VentanaGuardarApertura(this);
            }else{    
                activarRecordatorio();
            }   
        }

    }                    
    
    private void activarRecordatorio(){                       
        timerRecordatorio.schedule(new TimerTask() {
            @Override
            public void run() {
                recordatorioApertura();
            }
        }, 300000);
    }
    
    private int buscarProducto(Producto ing){
        int i;      
        for(i = 0 ; i < productosVenta.size() ; i++){
            if(productosVenta.get(i).idProducto == ing.idProducto)
                break;
        }        
        return i;
    }
            
    @Override
    public void confirmarCerrado(){
        if(JOptionPane.showConfirmDialog(null, "¿Confirmar cerrado?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0)
                System.exit(0);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {    
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_F5){
            panelProductos.actualizarPanel();            
        }
    }
    
    
    /**
     * Recibe una cadena la cual se usará para pasar como parámetro
     * al método de la clase PanelProductos encargado de actualizar
     * los productos mostrados.
     * @param aBuscar Subcadena que se usará para buscar productos
     */
    public void buscarProductos(String aBuscar){
        panelProductos.actualizarPanel(aBuscar);
    }
    
    
    /**
     * Carga los porductos existentes en la base de datos
     */
    public void cargarProductos(){
        panelProductos.cargarProductos();        
    }
    
    public boolean guardarDevolucion(float total){
        int id = -1;
        String sql = "INSERT INTO DEVOLUCIONES(FECHA, ID_USUARIO, HORA, TOTAL) "
                + "VALUES(" 
                + "'" + hoy() + "', "
                + usuarioActivo.id + ", "
                + "'" + horaNow() + "', "
                + " " + total 
                + ")";
        
        if(!SQLConnection.actualizar(sql))
            return false;
        
        sql = "SELECT * FROM DEVOLUCIONES ORDER BY ID_DEVOLUCION DESC LIMIT 1";
        ResultSet ultimoId = SQLConnection.buscar(sql);
        
        try {
            ultimoId.next();
            id = ultimoId.getInt("ID_DEVOLUCION");
            System.out.println(id);            
        } catch (SQLException ex) {
            reportarError(ex);
        }
        
        for(int i = 0 ; i < productosVenta.size() ; i++){
            sql = "INSERT INTO PRODUCTOS_DEVOLUCIONES(ID_DEVOLUCION, ID_PRODUCTO, CANTIDAD) "
                    + "VALUES("
                    + id + ", "
                    + productosVenta.get(i).idProducto + ", "
                    + productosVenta.get(i).cantidad
                    + ")";
            
            if(!SQLConnection.actualizar(sql))
                return false;
        }
        
        return true;                
                
    }
    
    public boolean guardarVenta(float total){                                                
        int id;
        
        String sql = "INSERT INTO VENTAS(ID_USUARIO, FECHA, HORA, TOTAL)"
                + "VALUES("
                + usuarioActivo.id + ","
                + "'" + hoy() + "', "
                + "'" + horaNow() + "',"
                + total 
                + ")";
        
        if(!SQLConnection.actualizar(sql))
            return false;
        
        ResultSet ultimoId = SQLConnection.buscar("SELECT * FROM VENTAS ORDER BY ID_VENTA DESC LIMIT 1");
        try {
            ultimoId.next();
            id = ultimoId.getInt("ID_VENTA");
        } catch (SQLException ex) {
            reportarError(ex);
            return false;
        }        
        
        for(int i = 0 ; i < productosVenta.size() ; i++){
            sql = "INSERT INTO PRODUCTOS_VENTAS(ID_PRODUCTO, ID_VENTA, CANTIDAD)"
                    + "VALUES("
                    + productosVenta.get(i).idProducto + ","
                    + id + ","
                    + productosVenta.get(i).cantidad + ")";
            
            if(!SQLConnection.actualizar(sql))
                return false;
        }
            
        return true;
    }
    
    public void limpiarVenta(){
        panelLateral.limpiarVenta();
    }
    
    public void añadirProducto(Producto ing){
        int index = buscarProducto(ing);                
        
        if(index != productosVenta.size()){
            productosVenta.get(index).cantidad++;
        }else{
            productosVenta.add(new Producto(ing));
            productosVenta.get(productosVenta.size() - 1).cantidad++;            
            panelLateral.tablaVenta.setSize(
                panelLateral.tablaVenta.getWidth(),
                panelLateral.tablaVenta.getHeight() + 30);
        }                     
        
        panelLateral.actualizar();                
    }
    
    public void disminuirProducto(Producto ing){
        int index = buscarProducto(ing);               
        
        if(index != productosVenta.size()){
            productosVenta.get(index).cantidad--;        
            if(productosVenta.get(index).cantidad == 0){
                productosVenta.remove(index);
            }                     
            
            panelLateral.actualizar();            
        }                                
    }
    
    public void eliminarProducto(Producto ing){
        int index = buscarProducto(ing);                
        
        if(index != productosVenta.size()){
            productosVenta.remove(index);  
            panelLateral.actualizar();            
        }
        
        
    }
    
    /**
     * Revisa si ya existe una apertura de caja registrada
     * para el dia en que se está trabajando
     * @return true si ya existe, false en caso contrario
     */
    public boolean checkAperturaDeCaja(){
        
        String sql = "SELECT * FROM CORTES_CAJA WHERE FECHA = '" + hoy() + "'";
        
        ResultSet query = SQLConnection.buscar(sql);
        
        try {
            if(query.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            reportarError(ex);
            return false;            
        }
        
    }
    
    private void recordatorioApertura(){
        if(checkAperturaDeCaja())
            return;
        
        switch(JOptionPane.showConfirmDialog(
                null, 
                "Aún no registra la apertura de caja\n"
                + "¿Quiere hacerlo en este momento?\n"
                + "Presione cancelar para no\n"
                + "volver a mostrar este mensaje.", 
                "Recordatorio", 
                JOptionPane.YES_NO_CANCEL_OPTION)){
            case 0:
                new VentanaGuardarApertura(this);
                break;
            case 1:
                activarRecordatorio();
                break;                        
        }
    }        
    
}
