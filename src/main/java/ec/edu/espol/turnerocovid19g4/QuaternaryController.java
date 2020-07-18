/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author lfrei
 */
public class QuaternaryController implements Initializable {


    @FXML
    private Button bttCrear;
    @FXML
    private Button bttEliminar;
    @FXML
    private Button bttCerrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void crearPuesto(ActionEvent event) throws IOException {
        App.setRoot("crearPuesto");
    }

    @FXML
    private void eliminarPuesto(ActionEvent event) throws IOException {
        App.setRoot("eliminarPuesto");
    }

    @FXML
    private void cerrar(ActionEvent event) throws IOException {
        App.setRoot("primarySecond");
    }

}
