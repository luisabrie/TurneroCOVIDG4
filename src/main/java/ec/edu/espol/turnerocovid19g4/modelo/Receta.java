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
public class Receta {
    private LinkedList<MedicamentoAsignado> medicamentoAsignado;

    public Receta() {
        this.medicamentoAsignado = new LinkedList<>();
    }

    public LinkedList<MedicamentoAsignado> getMedicamentoAsignado() {
        return medicamentoAsignado;
    }

    public void setMedicamentoAsignado(LinkedList<MedicamentoAsignado> medicamentoAsignado) {
        this.medicamentoAsignado = medicamentoAsignado;
    }
    
    
}
