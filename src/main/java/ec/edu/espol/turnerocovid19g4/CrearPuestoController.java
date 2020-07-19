/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.datos.ManejoArchivo;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author lfrei
 */
public class CrearPuestoController implements Initializable {


    @FXML
    private Button bttGuardar;
    @FXML
    private Button bttCerrar;
    @FXML
    private TextField txtNumero;
    @FXML
    private ComboBox comboMedico;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Medico medico:Data.getInstance().getMedicos()){
            if(!medico.isOcupado()) comboMedico.getItems().add(medico);
        }
    }    
    
    @FXML
    private void guardarPuesto(ActionEvent event) throws IOException {
        String numero=txtNumero.getText();
        Medico medico=(Medico)comboMedico.getValue();
        if (numero.trim().length()>0){
            Puesto puesto=new Puesto(numero);
            if(medico!=null){ 
                medico.setOcupado(true);
                puesto.setMedicoEncargado(medico);
                Data.getInstance().getPuestosAtendiendo().offer(puesto);
            }
            ManejoArchivo.registrarPuesto(puesto);
            Data.getInstance().getPuestos().add(puesto);
            App.setRoot("quaternary");
        }else{
            //Muestra alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Creacion de puesto");
            alert.setContentText("Campo Numero es obligatorio");
            alert.showAndWait();
        }
        
    };

    @FXML
    private void cerrar(ActionEvent event) throws IOException {
        App.setRoot("quaternary");
    };
    

}
