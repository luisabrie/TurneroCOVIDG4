/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import ec.edu.espol.turnerocovid19g4.datos.Data;
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
        diagnostico = new LinkedList<>();
    }
    
    public Sintoma getSintoma() {
        return sintoma;
    }
    public int getPrioridad(){
        return sintoma.getPrioridad();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    } 

    public LinkedList<Diagnostico> getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(LinkedList<Diagnostico> diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public String toString() {
        return "Cita{" + "paciente=" + paciente + ", medico=" + medico + ", sintoma=" + sintoma + ", diagnostico=" + diagnostico + ", receta=" + receta + '}';
    }
    
    
}
