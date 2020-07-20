/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import com.jfoenix.controls.JFXButton;
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
    private JFXButton fPaciente;
    @FXML
    private JFXButton fDoctor;
    @FXML
    private JFXButton fPuesto;
    @FXML
    private JFXButton fAtencion;
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
        App.setTamano(330, 490);
    }

    @FXML
    private void formDoctor() throws IOException {
        App.setRoot("tertiary");
        App.setTamano(330, 280);
    }

    @FXML
    private void adminPuesto() throws IOException {
        App.setRoot("quaternary");
        App.setTamano(290, 350);
    }

    @FXML
    private void atencionCliente() throws IOException {
        App.setRoot("fifth");
    }

}
