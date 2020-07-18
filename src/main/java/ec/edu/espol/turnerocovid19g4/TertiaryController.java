/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

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
public class TertiaryController {
    
    @FXML
    private TextField nombreTexto;
    
    @FXML
    private TextField apellidosTexto;
    
    @FXML
    private TextField cedulaTexto;
    
    @FXML
    private TextField especialidadTexto;
    
    @FXML
    private Button bttGuardar;
    
    @FXML
    private void guardar() throws IOException{
        String nombre=nombreTexto.getText();
        String apellidos=apellidosTexto.getText();
        String cedula=cedulaTexto.getText();
        String especialidad=especialidadTexto.getText();
        if (cedula.trim().length()>0 && nombre.trim().length()>0 && apellidos.trim().length()>0 
                && especialidad.trim().length()>0){
            Medico persona = new Medico(nombre,apellidos,cedula,especialidad);
            registrarMedico(persona);
            App.setRoot("primarySecond");
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
        App.setRoot("primarySecond");
    };
    
    public void registrarMedico(Medico doctor){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/medicos.txt", true))){
            String info = doctor.getNombre()+"|"+doctor.getApellido()+"|"+doctor.getCedula()+"|"+doctor.getEspecialidad();
            bw.append(info+"\n");
    	}catch(IOException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
    
}
