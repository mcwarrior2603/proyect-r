/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

import javax.swing.ImageIcon;

/**
 *
 * @author MCwar
 */
public class Producto {
    
    public int idProducto;
    public String nombre;
    public float precio;
    public int idCategoria;    
    public String imagen;
    public int cantidad;
    
    public Producto(int idProducto, String nombre, float precio, int idCategoria, String imagen){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        
        cantidad = 0;
    }
    
    public Producto(Producto copia){
        this.idProducto = copia.idProducto;
        this.nombre = copia.nombre;
        this.precio = copia.precio;
        this.idCategoria = copia.idCategoria;
        this.imagen = copia.imagen;
        this.cantidad = 0;
    }
}
