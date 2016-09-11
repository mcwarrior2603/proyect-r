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
    public String password;
    public int nivelDeAcceso;
    
    public Usuario(int id, String nombre, String password, int nivelDeAcceso){
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.nivelDeAcceso = nivelDeAcceso;
    }
    
}
