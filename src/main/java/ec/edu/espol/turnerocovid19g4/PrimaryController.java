package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import java.io.File;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PrimaryController implements Initializable {
    public static List<Sintoma> sintomas;
    public static List<Medico> medicos;
    public static Queue<Puesto> puestos;
    public static PriorityQueue<Cita> citas;
    private MediaPlayer mediaPlayer;
    //FXML
    @FXML
    private Button Paciente;
    @FXML
    private Button Doctor;
    @FXML
    private Button Puesto;
    @FXML
    private Button Atencion;
    @FXML
    private MediaView media;
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        mediaPlayer.stop();
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializar los videos en ventana principal
        List<String> videoPaths = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/videos.txt"))){
            String line;
            while((line=bf.readLine())!=null){
                videoPaths.add(line);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        String path = new File("src/media/videoFutbolFinal.mp4").getAbsolutePath();
        mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        media.setMediaPlayer(mediaPlayer);
        //Cargando Estructuras de Datos
        if(sintomas == null ) cargarSintomas();
        if(medicos == null) cargarMedicos();
        if(puestos == null) cargarPuestos();
        if( citas == null) citas = new PriorityQueue<>(
                (Cita c1, Cita c2)-> c1.getPrioridad() - c2.getPrioridad());
    }
    
    public void cargarMedicos(){
        medicos = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/medicos.txt"))){
            String line;
            while((line=bf.readLine())!=null){
                String[] datos = line.split("\\|");
                medicos.add(new Medico(datos[0],datos[1],datos[2],datos[3]));  
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void cargarSintomas(){
        sintomas = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/sintomas.txt"))){
            String line;
            while((line=bf.readLine())!=null){
                String[] datos = line.split("\\|");
                Sintoma sintoma = new Sintoma(datos[0],Integer.parseInt(datos[1]));
                sintomas.add(sintoma);  
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void cargarPuestos(){
        puestos = new LinkedList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("src/puestos.txt"))){
            String line;
            while((line=bf.readLine())!=null){
                puestos.add(new Puesto(line));
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    /**
     * Cambio de Ventanas
     */    
    
    //Formulario para crear Pacientes
    @FXML
    private void vistaPaciente() throws IOException{
        mediaPlayer.stop();
        App.setRoot("secondary");
    };
    //Formulario para crear Doctores
    @FXML
    private void vistaDoctor() throws IOException{
        App.setRoot("tertiary");
    };
    //Formulario para crear Puestos
    @FXML
    private void vistaPuesto() throws IOException{
        App.setRoot("quaternary");
    };
    //Vista para simular atencion a Pacientes
    @FXML
    private void vistaAtencion() throws IOException{
        App.setRoot("fifth");
    };
}
