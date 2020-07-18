/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

/**
 *
 * @author mbpretina
 */
public class Medico extends Persona{
    
    private String especialidad;

    public Medico(String nombre, String apellido, String cedula, String especialidad) {
        super(cedula, nombre, apellido);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    @Override
    public String toString() {
        return getNombre();
    }
    
    
    
}
