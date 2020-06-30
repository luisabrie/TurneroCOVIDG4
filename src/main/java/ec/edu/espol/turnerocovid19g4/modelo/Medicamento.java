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
class Medicamento {
    private String nombreGenerico;
    private String nombreComercial;
    private String ctd;

    public Medicamento(String nombreGenerico, String nombreComercial, String ctd) {
        this.nombreGenerico = nombreGenerico;
        this.nombreComercial = nombreComercial;
        this.ctd = ctd;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getCtd() {
        return ctd;
    }

    public void setCtd(String ctd) {
        this.ctd = ctd;
    }
    
    
}
