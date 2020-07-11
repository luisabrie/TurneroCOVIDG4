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
import java.io.File;
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
        mediaPlayer.pause();
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
        
        //Cargando lista con sintomas 
        cargarSintomas();
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
    
}
