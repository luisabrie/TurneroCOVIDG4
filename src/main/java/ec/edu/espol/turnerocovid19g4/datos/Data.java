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
    private Queue<Puesto> puestos;
    private PriorityQueue<Cita> citas;
    private CircularSimplyLinkedList<File> videos;
    
    private Data(){
        medicos = ManejoArchivo.cargarMedicos(medicos);
        sintomas = ManejoArchivo.cargarSintomas(sintomas);
        puestos =  ManejoArchivo.cargarPuestos(puestos);
        videos =  ManejoArchivo.cargarVideos(videos);
        System.out.println(videos);
    }
    
    public static Data getInstance(){
        return instancia;
    }
    public boolean nuevaCita(Cita cita){
        return citas.offer(cita);
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }
    // TODO : Crear iterador, desplazarlo cada nuevo video
    public File getVideo() {
        return videos.get(1);
    }


}
