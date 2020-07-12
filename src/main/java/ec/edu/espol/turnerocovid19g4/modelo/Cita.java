/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import java.util.LinkedList;

/**
 *
 * @author mbpretina
 */
public class Cita {
    private Paciente paciente;
    private Medico medico;
    private Sintoma sintoma;
    private LinkedList<Diagnostico> diagnostico;
    private Receta receta;
    
    public Cita(Paciente paciente, Sintoma sintoma){
        this.paciente = paciente;
        this.sintoma = sintoma;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }
    
    public int getPrioridad(){
        return sintoma.getPrioridad();
    }
    
    
}
