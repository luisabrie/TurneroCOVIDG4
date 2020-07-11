package ec.edu.espol.turnerocovid19g4;

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
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PrimaryController implements Initializable {
    
    @FXML
    private Button Paciente;
    
    @FXML
    private void vistaPaciente() throws IOException{
        App.setRoot("secondary");
    };
    
    @FXML
    private Button Doctor;
    
    @FXML
    private void vistaDoctor() throws IOException{
        App.setRoot("tertiary");
    };
    
    @FXML
    private Button Puesto;
    
    @FXML
    private void vistaPuesto() throws IOException{
        App.setRoot("quaternary");
    };
    
    @FXML
    private Button Atencion;
    
    @FXML
    private void vistaAtencion() throws IOException{
        App.setRoot("fifth");
    };
    
    public static List<Sintoma> sintomas;
    @FXML
    private MediaView media;
    private MediaPlayer mediaPlayer;
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //para inicializar videos
        //mediaPlayer = new MediaPlayer(new Media(/*"file:/C:/Users/DELL/Pictures/video1.mp4"*/));
        //mediaPlayer.setAutoPlay(true);
        //media.setMediaPlayer(mediaPlayer);
        //agregando sintomas
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
    
}
