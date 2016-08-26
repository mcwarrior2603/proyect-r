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
public class Venta {
    int id;
    String fecha;
    String cajero;
    String hora;
    float total;
    
    ArrayList <Producto> productos = new ArrayList();
    
    public Venta(int id, String fecha, String cajero, String hora, float total){
        this.id = id;
        this.fecha = fecha;
        this.cajero = cajero;
        this.hora = hora;
        this.total = total;
    }
}
