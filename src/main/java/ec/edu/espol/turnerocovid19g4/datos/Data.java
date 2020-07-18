/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.datos;

import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import ec.edu.espol.turnerocovid19g4.util.CircularSimplyLinkedList;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author mbpretina
 */
public class Data {
    
    private static Data instancia = new Data();
    private List<Sintoma> sintomas;
    private List<Medico> medicos;
    private List<Puesto> puest;
    private Queue<Puesto> puestos;
    private PriorityQueue<Cita> citas;
    private CircularSimplyLinkedList<File> videos;
    private Iterator<File> vidIt;
    
    private Data(){
        medicos = ManejoArchivo.cargarMedicos(medicos);
        sintomas = ManejoArchivo.cargarSintomas(sintomas);
        puest =  ManejoArchivo.cargarPuestos(puest);
        puestos=(Queue<Puesto>) puest;
        videos =  ManejoArchivo.cargarVideos(videos);
        vidIt = videos.iterator();
        citas = new PriorityQueue<>(
                (Cita c1, Cita c2)-> c1.getPrioridad() - c2.getPrioridad());
    }
    
    public static Data getInstance(){
        return instancia;
    }
    public boolean nuevaCita(Cita cita){
        return citas.offer(cita);
    }
    public boolean nuevoPuesto(Puesto puesto){
        return puestos.offer(puesto);
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }
    
    public List<Medico> getMedicos(){
        return medicos;
    }
    
    public List<Puesto> getPuest(){
        return puest;
    }
    // TODO : Crear iterador, desplazarlo cada nuevo video
    public File getVideo() {
        return vidIt.next();
    }


}
