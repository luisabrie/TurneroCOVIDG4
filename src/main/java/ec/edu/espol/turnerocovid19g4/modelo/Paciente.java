/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.modelo;

import java.time.LocalDate;

/**
 *
 * @author mbpretina
 */
public class Paciente extends Persona{
    
    private LocalDate fechaNacimiento;
    private Genero genero;
    private Sintoma sintoma;

    public Paciente(LocalDate fechaNacimiento, Genero genero, String cedula, String nombre, String apellido, Sintoma sintoma) {
        super(cedula, nombre, apellido);
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.sintoma=sintoma;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }
    
    public enum Genero {
        HOMBRE,
        MUJER
    }
    
    
    
}
