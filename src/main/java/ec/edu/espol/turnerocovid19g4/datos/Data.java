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
    private List<Puesto> puestos; //todos los puestos
    private Queue<Puesto> puestosAtendiendo; //puestos que tienen medico
    private PriorityQueue<Cita> citas;
    private CircularSimplyLinkedList<File> videos;
    private Iterator<File> vidIt;
    
    private Data(){
        medicos = ManejoArchivo.cargarMedicos(medicos);
        sintomas = ManejoArchivo.cargarSintomas(sintomas);
        puestos =  ManejoArchivo.cargarPuestos(puestos);
        puestosAtendiendo=(Queue<Puesto>) puestos; //Inicializar vacia o solo puestos con medicos?
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
        return puestosAtendiendo.offer(puesto);
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }
    
    public List<Medico> getMedicos(){
        return medicos;
    }
    
    public List<Puesto> getPuestos(){
        return puestos;
    }
    // TODO : Crear iterador, desplazarlo cada nuevo video
    public File getVideo() {
        return vidIt.next();
    }


}
