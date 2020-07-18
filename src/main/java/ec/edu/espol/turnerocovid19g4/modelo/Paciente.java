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

    public Paciente(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, Genero genero) {
        super(cedula, nombre, apellido);
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
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
    
    public enum Genero {
        HOMBRE,
        MUJER
    }
    
    
    
}
