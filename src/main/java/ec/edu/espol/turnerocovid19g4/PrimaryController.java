package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PrimaryController implements Initializable {
    
    private MediaPlayer mediaPlayer;
    //FXML
    @FXML
    private MediaView media;
    //Labels de turnos y puestos
    @FXML
    private Label firstTurno;
    @FXML
    private Label secondTurno;
    @FXML
    private Label thirdTurno;
    @FXML
    private Label fourthTurno;
    @FXML
    private Label firstPuesto;
    @FXML
    private Label secondPuesto;
    @FXML
    private Label thirdPuesto;
    @FXML
    private Label fourthPuesto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa los videos
        initVideos();
    }
    
    private void initVideos(){
        mediaPlayer = new MediaPlayer(new Media(Data.getInstance().getVideo().toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            initVideos();
        });
        media.setMediaPlayer(mediaPlayer);
    }

}
