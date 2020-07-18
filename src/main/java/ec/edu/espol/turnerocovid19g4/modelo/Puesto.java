/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

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

    @Override
    public String toString() {
        return codPuesto;
    }
    
    
    
}
