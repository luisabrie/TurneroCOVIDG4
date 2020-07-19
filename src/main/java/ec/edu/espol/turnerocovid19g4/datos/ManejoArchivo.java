/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4.datos;

import ec.edu.espol.turnerocovid19g4.App;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Paciente;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import ec.edu.espol.turnerocovid19g4.util.CircularSimplyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public static List<Puesto> cargarPuestos(List<Puesto> puestos,Queue<Puesto> colaPuesto){
        puestos = new LinkedList<>();
        
        File file = obtenerArchivoDesdeRecursos("puestos.txt");

        if (file == null) return null;
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);//Borrar
                String[] info = line.split("\\|");
                if(info.length == 2){
                    Medico medico=Data.getInstance().recorrerMedicos(info[1]);
                    Puesto p=new Puesto(info[0],medico);
                    puestos.add(p);
                    colaPuesto.offer(p);
                }else puestos.add(new Puesto(info[0])); // ¿Se tiene que agregar el puesto inmediatamente? ¿Hay puestos prestablecidos con doctor?
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
    // Se elimino cargarCitasRezagadas porque en el archivo solo se tiene que guardar la informacion del paciente
    private static File obtenerArchivoDesdeRecursos(String archivo) {
        
        
        URL resource = App.class.getResource("datos/"+ archivo);
        System.out.println(resource.getPath());
        if (resource == null) {
            throw new IllegalArgumentException("¡No se ha encontrado el archivo: "+archivo+"!");
        } else {
            return new File(resource.getFile());
        }

    }
    // TODO : Mejorar esto
    public static void registrarPaciente(Paciente paciente){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/pacientes.txt", true))){
            String info = paciente.getNombre()+"|"+paciente.getApellido()+"|"+paciente.getCedula();
            bw.append(info+"\n"); 
    	}catch(IOException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    
    public static void registrarMedico(Medico doctor){
        File file = obtenerArchivoDesdeRecursos("medicos.txt");
        try(FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw)){
            String info = doctor.getNombre()+"|"+doctor.getApellido()+"|"+doctor.getCedula()+"|"+doctor.getEspecialidad();
            bw.append(info+"\n");
    	}catch(IOException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    
    
    public static void registrarPuesto(Puesto puesto){
        File file = obtenerArchivoDesdeRecursos("puestos.txt");
        try(FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw)){
            
            String info = puesto.getCodPuesto();
            if(puesto.getMedicoEncargado()!=null) info=info+"|"+puesto.getMedicoEncargado();
            bw.append(info+"\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    //Metodos para finalizar el sistema
    public static void savePuestos(){
        File file = obtenerArchivoDesdeRecursos("puestos.txt");
        try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw)){
            
            for(Puesto p: Data.getInstance().getPuestos())
                bw.append(p.getCodPuesto()+"\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void saveMedicos(){
        File file = obtenerArchivoDesdeRecursos("medicos.txt");
        try(FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw)){
            
            for(Medico m: Data.getInstance().getMedicos())
                bw.append(m.getNombre()+"|"+m.getApellido()+"|"+m.getCedula()+"|"+m.getEspecialidad()+"\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
