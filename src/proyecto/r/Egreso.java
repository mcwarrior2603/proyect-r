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
public class Egreso {
    int id;
    String fecha;
    float monto;
    String concepto;
    String usuario;
    
    public Egreso(int id, String fecha, float monto, String concepto, String usuario){
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.concepto = concepto;
        this.usuario = usuario;
    }
}
