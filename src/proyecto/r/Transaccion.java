/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import java.util.ArrayList;

/**
 *
 * @author MCwar
 */
public class Transaccion{
    public static final String VENTA = "Venta";
    public static final String DEVOLUCION = "Devolucion";
    
    int id;
    String fecha;
    String cajero;
    String hora;
    float total;
    String concepto;        
    
    public Transaccion(int id, String concepto, String fecha, String cajero, String hora, float total){
        this.id = id;
        this.concepto = concepto;
        this.fecha = fecha;
        this.cajero = cajero;
        this.hora = hora;
        this.total = total;
        
        
    }
}
