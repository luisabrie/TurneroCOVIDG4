/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import java.util.Objects;

/**
 *
 * @author Alejandro
 */
public class Puesto {
    private Medico medicoEncargado;
    private Cita cita;
    private final String codPuesto;
    
    public Puesto(String codPuesto){
        this.codPuesto = codPuesto;
    }
    
    public Puesto(String codPuesto, Medico medico){
        this.codPuesto = codPuesto;
        medicoEncargado = medico;
    }

    public void setMedicoEncargado(Medico medicoEncargado) {
        this.medicoEncargado = medicoEncargado;
    }

    public void setCita (Cita cita) {
        this.cita = cita;
    }

    public Medico getMedicoEncargado() {
        return medicoEncargado;
    }

    public String getCodPuesto() {
        return codPuesto;
    }

    public Cita getCita() {
        return cita;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Puesto other = (Puesto) obj;
        if (!Objects.equals(this.codPuesto, other.codPuesto)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return codPuesto;
    }
    
    
    
}
