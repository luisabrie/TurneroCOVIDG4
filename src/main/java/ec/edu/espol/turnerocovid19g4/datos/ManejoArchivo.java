/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.datos;

import ec.edu.espol.turnerocovid19g4.App;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import ec.edu.espol.turnerocovid19g4.util.CircularSimplyLinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author mbpretina
 * En teoria se deberia manejar una especie de interfaz y crear clases para cada carga, pero...
 */
public class ManejoArchivo {
    public static List<Medico> cargarMedicos(List<Medico> medicos){
        medicos = new ArrayList<>();
        File file = obtenerArchivoDesdeRecursos("medicos.txt");
        if (file == null) return null;
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split("\\|");
                medicos.add(new Medico(datos[0],datos[1],datos[2],datos[3]));
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return medicos;
    }
    public static List<Sintoma> cargarSintomas(List<Sintoma> sintomas){
        sintomas = new ArrayList<>();
        
        File file = obtenerArchivoDesdeRecursos("sintomas.txt");

        if (file == null) return null;
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split("\\|");
                Sintoma sintoma = new Sintoma(datos[0],Integer.parseInt(datos[1]));
                sintomas.add(sintoma); 
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return sintomas;
    }
    public static Queue<Puesto> cargarPuestos(Queue<Puesto> puestos){
        puestos = new LinkedList<>();
        // TODO : Usar el manejo de recursos de java
        
        File file = obtenerArchivoDesdeRecursos("puestos.txt");

        if (file == null) return null;
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                puestos.add(new Puesto(line)); // ¿Se tiene que agregar el puesto inmediatamente? ¿Hay puestos prestablecidos con doctor?
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return puestos;
    }
    
    public static CircularSimplyLinkedList<File> cargarVideos(CircularSimplyLinkedList<File> videos){
        videos = new CircularSimplyLinkedList<>();
        URL url = App.class.getResource("media");
        if (url == null) {
            throw new IllegalArgumentException("¡No se ha encontrado la carpeta media!");
        } else {
            String path = url.getPath();

            File[] files = new File(path).listFiles();
            for (File f : files) {
                System.out.println(f);
                videos.addLast(f);
                }
            }
        return videos;
        }
    private static File obtenerArchivoDesdeRecursos(String archivo) {
        
        
        URL resource = App.class.getResource("datos/"+ archivo);
        System.out.println(resource.getPath());
        if (resource == null) {
            throw new IllegalArgumentException("¡No se ha encontrado el archivo: "+archivo+"!");
        } else {
            return new File(resource.getFile());
        }

    }
    //¿Debe haber un metodo de carga de paciente con citas?citas = new PriorityQueue<>(
    //            (Cita c1, Cita c2)-> c1.getPrioridad() - c2.getPrioridad());
}
