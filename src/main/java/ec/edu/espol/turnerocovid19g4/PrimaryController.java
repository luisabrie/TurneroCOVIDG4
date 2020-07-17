package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
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
        mediaPlayer = new MediaPlayer(new Media(Data.getInstance().getVideo().toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        media.setMediaPlayer(mediaPlayer);
        //Cargando Estructuras de Datos
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
