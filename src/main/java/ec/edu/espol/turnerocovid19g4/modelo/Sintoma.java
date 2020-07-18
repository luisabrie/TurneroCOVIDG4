/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import java.util.Objects;

/**
 *
 * @author mbpretina
 */
public class Sintoma {
    private String nombre;
    private int prioridad;
    
    public Sintoma(String nombre, int prioridad){
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    public Sintoma(String nombre){
        this.nombre = nombre;
        busquedaPrioridad();
    }
    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }
    private void busquedaPrioridad() {
        // TODO : Validar si no se encuentra un sintoma que esta en el archivo
        // en la lista de sintomas
        this.prioridad = Data.getInstance().getSintomas().get(
                Data.getInstance().getSintomas().indexOf(this)).prioridad;
    }
    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sintoma other = (Sintoma) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
}
