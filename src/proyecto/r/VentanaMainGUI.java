/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MCwar
 */
public class VentanaMainGUI extends Ventana{
                            
    public VentanaCobrar ventCobrar;
    public VentanaConfiguracion ventConfiguracion;
    public VentanaCorteDeCaja ventCorte;
    public VentanaEgreso ventEgreso;
    public VentanaGuardarApertura ventApertura;
    public VentanaListaEgresos ventEgresos;
    public VentanaListaProductos ventProductos;
    public VentanaListaVentas ventVentas;
    public VentanaProducto ventProducto;
    public VentanaTransaccion ventTransaccion;    
    public VentanaUsuario ventUsuario;        
    
    private JLabel logo = new JLabel();
    
    private JPanel panelPrincipal;
    public PanelMenus panelMenus = new PanelMenus();    
    public PanelLateral panelLateral = new PanelLateral();   
    public PanelProductos panelProductos = new PanelProductos(); 
    
    public ArrayList <Producto> productosVenta = new ArrayList();        
    
    public boolean cobrando = false;
    public Usuario usuarioActivo;
    public int turnoActual = 1;
    
    public File archivoConfiguracion = new File("archivos/configuracion.txt");
    public Color colorFondo = new Color(0xFFFFFF);
    public Color colorPanel = new Color(0xFFFFFF);
    public Color colorBoton = new Color(0xFFFFFF);
    public String logotipo;
    public int minutosRecordatorio = 0;
    
    public Timer timerRecordatorio = new Timer();;
    
    public VentanaMainGUI(Usuario usuarioActivo){                        
        super(300, 300, NOMBRE_SW);                                                                     
        
        panelPrincipal = (JPanel) getContentPane();
        panelPrincipal.updateUI();                                          
        
        this.usuarioActivo = usuarioActivo;                                
        
        setResizable(true);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);                                                
                
        panelPrincipal.setLayout(new BorderLayout(15, 15));
        panelPrincipal.setBackground(colorFondo);  
                
        panelMenus.configurar(this);
        panelLateral.configurar(this, productosVenta);
        panelProductos.configurar(this);
        
        panelPrincipal.add(panelProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelLateral, BorderLayout.WEST);
        panelPrincipal.add(panelMenus, BorderLayout.NORTH);                                        
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {

            boolean recovered = false;
                    
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {                                                 
                if(e.getKeyCode() == KeyEvent.VK_F5){
                    panelProductos.actualizarPanel();            
                }else if(e.getKeyCode() == KeyEvent.VK_F12){                    
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                        panelLateral.savePedido();
                }else if(e.getKeyCode() == KeyEvent.VK_F11){                    
                    if(e.getID() == KeyEvent.KEY_PRESSED && !recovered){
                        panelLateral.recoverPedido();
                        recovered = true;
                    }else if(e.getID() == KeyEvent.KEY_RELEASED){
                        recovered = false;
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_F2 
                        && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) 
                        && e.getID() == KeyEvent.KEY_RELEASED){
                    limpiarVenta();
                } 
                return false;
            }                        
        });
        
        panelLateral.actualizar();        
        cargarConfiguracion();
                
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
        if(minutosRecordatorio == 0)
            minutosRecordatorio = 5;
        
        timerRecordatorio.schedule(new TimerTask() {
            @Override
            public void run() {
                recordatorioApertura();
            }
        }, 60000 * minutosRecordatorio);        
    }
             
    public void cargarConfiguracion(){
        
        int r, g, b;        
        BufferedReader reader;        
        
        try{
            reader = new BufferedReader(new FileReader(archivoConfiguracion));
            
            //Color fondo
            r = Integer.parseInt(reader.readLine());
            g = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());
            colorFondo = new Color(r, g, b);
            
            //Color panel
            r = Integer.parseInt(reader.readLine());
            g = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());
            colorPanel = new Color(r, g, b);
            
            //Color Botones
            r = Integer.parseInt(reader.readLine());
            g = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());
            colorBoton = new Color(r, g, b);
            
            //Logotipo
            logotipo = reader.readLine();
            
            //Minutos entre cada recordatorio
            minutosRecordatorio = Integer.parseInt(reader.readLine());
            
            configurarColores();
            
        } catch(FileNotFoundException|NumberFormatException ex){
            JOptionPane.showMessageDialog(null, 
                    "No existe un archivo de configuración válido.\n"
                            + "Por favor configure el programa");
            new VentanaConfiguracion(this, VentanaConfiguracion.CONFIGURACION_INICIAL);
        } catch (IOException ex) { 
            System.out.println("Error");
        }
    }   
    
    public void configurarColores(){
        panelPrincipal = (JPanel) getContentPane();
        
        panelPrincipal.setBackground(colorFondo);
        panelLateral.configurarColores(colorPanel);
        panelMenus.configurarColores(colorPanel);
        panelProductos.configurarColores(colorPanel);
        panelPrincipal.updateUI();
    }
        
    /**
     * Cierra las ventanas añadidas a la interfaz principal
     * @return true En caso de que todo haya sido cerrado correctamente
     * @return false En caso de que alguna ventana no haya podido ser cerrada
     */
    public boolean cerrarVentanasSecundarias(){
        if(ventApertura != null){
            ventApertura.cerrar();
            if(ventApertura != null)
                return false;
        }
        if(ventCobrar != null){
            ventCobrar.cerrar();
            if(ventCobrar != null)
                return false;
        }
        if(ventConfiguracion != null){
            ventConfiguracion.cerrar();
            if(ventConfiguracion != null)
                return false;
        }
        if(ventCorte != null){
            ventCorte.cerrar();
            if(ventCorte != null)
                return false;
        }
        if(ventEgreso != null){
            ventEgreso.toFront();
            ventEgreso.confirmarCerrado();
            if(ventEgreso != null)
                return false;
        }
        if(ventEgresos != null){
            ventEgresos.cerrar();            
            if(ventEgresos != null)
                return false;
        }
        if(ventProducto != null){
            ventProducto.toFront();
            ventProducto.confirmarCerrado();
            if(ventProducto != null)
                return false;
        } 
        if(ventProductos != null){
            ventProductos.cerrar();
            if(ventProductos != null)
                return false;
        }
        if(ventTransaccion != null){
            ventTransaccion.toFront();
            ventTransaccion.confirmarCerrado();            
            if(ventTransaccion != null)
                return false;
        }
        if(ventVentas != null){
            ventVentas.cerrar();
            if(ventVentas != null)
                return false;
        }
        if(ventUsuario != null){
            ventUsuario.toFront();
            ventUsuario.confirmarCerrado();
            if(ventUsuario != null)
                return false;
        }
        
        
        return true;
    }
        
    public void cerrarSesion(){
        if(!cerrarVentanasSecundarias()){
            return;
        }
        
        super.cerrar();        
        new VentanaLogin();
        
    }
    
    @Override
    public boolean confirmarCerrado(){
        
        if(!cerrarVentanasSecundarias()){
            return false;
        }
        
        if(JOptionPane.showConfirmDialog(null, "¿Confirmar cerrado?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0){
            System.exit(0);
            return true;
        }
        
        return false;
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
    
    /**
     * Elimina los productos de la venta mostrada actualmente
     */
    public void limpiarVenta(){
        panelLateral.limpiarVenta();
    }
    
    /**
     * Busca si un producto en la lista de productos que se están
     * vendiendo para realizar la acción correspondiente sobre la venta     
     * @param ing El producto que se está buscando
     * @return El índice en que se encuentra el producto
     * @return El tamaño de la lista si no existe
     */
    private int buscarProducto(Producto ing){
        int i;      
        for(i = 0 ; i < productosVenta.size() ; i++){
            if(productosVenta.get(i).idProducto == ing.idProducto)
                break;
        }        
        return i;
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
