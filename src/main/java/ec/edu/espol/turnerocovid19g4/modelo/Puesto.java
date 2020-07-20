/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import ec.edu.espol.turnerocovid19g4.modelo.Medico;

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

    @Override
    public String toString() {
        return codPuesto;
    }

    public Cita getCita() {
        return cita;
    }
    
}
