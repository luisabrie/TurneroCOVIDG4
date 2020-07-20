package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import ec.edu.espol.turnerocovid19g4.util.CircularSimplyLinkedList;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PrimaryController implements Initializable {
    
    private MediaPlayer mediaPlayer;
    private Queue<HBox> colaTurno;
    private int contador; //contador de turno para mostrar
    //FXML
    @FXML
    private MediaView media;
    //Labels de turnos y puestos
    @FXML
    private HBox firstTurno;
    @FXML
    private HBox secondTurno;
    @FXML
    private HBox thirdTurno;
    @FXML
    private HBox fourthTurno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa los videos
        initVideos();
        colaTurno = new LinkedList<>();
        colaTurno.offer(firstTurno);
        colaTurno.offer(secondTurno);
        colaTurno.offer(thirdTurno);
        colaTurno.offer(fourthTurno);
        contador=0;
        Thread t = new Thread(new Refrescador());
        t.start();
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
    
    class Refrescador implements Runnable{
        private final Object l=new Object();
        @Override
        public void run() { 
            while(true){
                synchronized(l){
                    while(!Data.getInstance().getPuestosAtendiendo().isEmpty() && !Data.getInstance().getCitas().isEmpty()){
                        Puesto p = Data.getInstance().getPuestosAtendiendo().poll();
                        Cita c = Data.getInstance().getCitas().poll();
                        c.setMedico(p.getMedicoEncargado()); 
                        p.setCita(c);
                        //PatientsManagementController.getMapaPuesto().get(p);
                        HBox contenedor = colaTurno.poll();
                        Platform.runLater(()->{
                            Label l1 = (Label)contenedor.getChildren().get(0);
                            l1.setText(String.valueOf(contador++));
                            Label l2 = (Label)contenedor.getChildren().get(1);
                            l2.setText(p.getCodPuesto());
                        });
                        colaTurno.offer(contenedor);
                    }
                }
            }
        }
    }
     
}
