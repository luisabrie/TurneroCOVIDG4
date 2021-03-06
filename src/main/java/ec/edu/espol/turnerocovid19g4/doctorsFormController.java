/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.datos.ManejoArchivo;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author lfrei
 */
public class doctorsFormController {
    
    @FXML
    private JFXTextField nombreTexto;
    
    @FXML
    private JFXTextField apellidosTexto;
    
    @FXML
    private JFXTextField cedulaTexto;
    
    @FXML
    private JFXTextField especialidadTexto;
    
    @FXML
    private JFXButton bttGuardar;
    
    @FXML
    private void guardar() throws IOException{
        String nombre=nombreTexto.getText();
        String apellidos=apellidosTexto.getText();
        String cedula=cedulaTexto.getText();
        String especialidad=especialidadTexto.getText();
        if (cedula.trim().length()>0 && nombre.trim().length()>0 && apellidos.trim().length()>0 
                && especialidad.trim().length()>0){
            Medico persona = new Medico(nombre,apellidos,cedula,especialidad);
            Data.getInstance().getMedicos().add(persona);
            ManejoArchivo.registrarMedico(persona);
            App.setRoot("administrationMenu");
            App.setTamano(290, 375);
        }else{
            //Muestra alerta
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ingreso de persona");
            alert.setContentText("Todos los campos son obligatorios");
            alert.showAndWait();
        }
    };
    
    @FXML
    private void cerrar() throws IOException{
        App.setRoot("administrationMenu");
        App.setTamano(290, 375);
    };
    
    
}
