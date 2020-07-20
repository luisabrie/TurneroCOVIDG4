/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import com.jfoenix.controls.JFXComboBox;
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
    private JFXComboBox comboPuesto;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Puesto puesto:Data.getInstance().getPuestos()){
            if (puesto.getCita() != null)
            comboPuesto.getItems().add(puesto+" {En Cita}");
            else comboPuesto.getItems().add(puesto);
        }
    }    
    
    @FXML
    private void eliminarPuesto() throws IOException{
        Puesto puesto=(Puesto) comboPuesto.getValue();
        if(puesto!=null && puesto.getMedicoEncargado()==null){
            Data.getInstance().getPuestos().remove(puesto);
            Data.getInstance().getPuestosAtendiendo().remove(puesto); //remove en cola?
            Data.getInstance().getMapaPuesto().remove(puesto);
            App.setRoot("menuPuesto");
            App.setTamano(290, 350);
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
        if(puesto!=null && puesto.getMedicoEncargado()!=null && puesto.getCita()==null){
            puesto.getMedicoEncargado().setOcupado(false);
            puesto.setMedicoEncargado(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Eliminacion Medico");
            alert.setContentText("Eliminacion de Medico exitoso");
            alert.showAndWait();
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
        App.setRoot("menuPuesto");
        App.setTamano(290, 350);
    }
    
}
