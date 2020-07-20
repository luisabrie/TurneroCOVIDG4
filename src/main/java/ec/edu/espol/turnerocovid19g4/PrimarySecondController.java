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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author lfrei
 */
public class PrimarySecondController implements Initializable {


    @FXML
    private Button fPaciente;
    @FXML
    private Button fDoctor;
    @FXML
    private Button fPuesto;
    @FXML
    private Button fAtencion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void formPaciente() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void formDoctor() throws IOException {
        App.setRoot("tertiary");
    }

    @FXML
    private void adminPuesto() throws IOException {
        App.setRoot("quaternary");
    }

    @FXML
    private void atencionCliente() throws IOException {
        App.setRoot("patientsManagement");
    }

}
