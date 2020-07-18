/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author lfrei
 */
public class EliminarPuestoController implements Initializable {

    @FXML
    private ComboBox comboPuesto;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Puesto puesto:Data.getInstance().getPuesto()){
            comboPuesto.getItems().add(puesto);
        }
    }    
    
    @FXML
    private void eliminarPuesto(){
        Puesto puesto=(Puesto) comboPuesto.getValue();
        if(puesto!=null && puesto.getMedicoEncargado()==null){
            Data.getInstance().getPuesto().remove(puesto);
        }else{
            //Muestra alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Eliminacion de puesto");
            alert.setContentText("Seleccionar un Puesto/Eliminar medico de Puesto");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void eliminarMedico(){
        Puesto puesto=(Puesto) comboPuesto.getValue();
        if(puesto!=null && puesto.getMedicoEncargado()!=null){
            puesto.setMedicoEncargado(null);
        }else{
            //Muestra alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Eliminacion de puesto");
            alert.setContentText("Seleccionar un Puesto");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cerrar() throws IOException{
        App.setRoot("quaternary");
    }
    
}
