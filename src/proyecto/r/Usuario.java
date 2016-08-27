/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.r;

/**
 *
 * @author MCwar
 */
public class Usuario {
    
    public int id;
    public String nombre;
    public int nivelDeAcceso;
    
    public Usuario(int id, String nombre, int nivelDeAcceso){
        this.id = id;
        this.nombre = nombre;
        this.nivelDeAcceso = nivelDeAcceso;
    }
    
}
