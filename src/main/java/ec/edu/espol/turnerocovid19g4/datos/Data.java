/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.datos;

import ec.edu.espol.turnerocovid19g4.PuestoBotonController;
import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import ec.edu.espol.turnerocovid19g4.util.CircularSimplyLinkedList;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.collections.ObservableMap;

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
    private ObservableMap<Puesto,PuestoBotonController> mapaPuesto;
    
    private Data(){
        medicos = ManejoArchivo.cargarMedicos(medicos);
        sintomas = ManejoArchivo.cargarSintomas(sintomas);
        puestosAtendiendo=new LinkedList<>(); 
        puestos =  ManejoArchivo.cargarPuestos(puestos,puestosAtendiendo);        
        videos =  ManejoArchivo.cargarVideos(videos);
        vidIt = videos.iterator();
        citas = new PriorityQueue<>(
                (Cita c1, Cita c2)-> c1.getPrioridad() - c2.getPrioridad());
        for(Puesto puesto: puestos){
            if(puesto.getNombre()!=null){
                Medico m=recorrerMedicos(puesto.getNombre());
                m.setOcupado(true);
                puesto.setMedicoEncargado(m);
            }
        }
    }

    public ObservableMap<Puesto, PuestoBotonController> getMapaPuesto() {
        return mapaPuesto;
    }

    public void setMapaPuesto(ObservableMap<Puesto, PuestoBotonController> mapaPuesto) {
        this.mapaPuesto = mapaPuesto;
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
    
    public Medico recorrerMedicos(String nombre){
        for(Medico medico:medicos){
            if(medico.getNombre().equals(nombre)){
                return medico;
            }
        }
        return null;
    }
    
    public Queue<Puesto> getPuestosAtendiendo(){
        return puestosAtendiendo;
    }
    
    // TODO : Crear iterador, desplazarlo cada nuevo video
    public File getVideo() {
        return vidIt.next();
    }

    public PriorityQueue<Cita> getCitas() {
        return citas;
    }

    
}
