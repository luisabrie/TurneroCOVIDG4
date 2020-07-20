/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.modelo.Medico;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
/**
 * FXML Controller class
 *
 * @author lfrei
 */
public class AsignarPuestoController implements Initializable {


    @FXML
    private JFXComboBox comboPuesto;
    @FXML
    private JFXComboBox comboMedico;
    @FXML
    private JFXButton bttAsignar;
    @FXML
    private JFXButton bttCerrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Puesto puesto:Data.getInstance().getPuestos()){
            if(puesto.getMedicoEncargado()==null){
                comboPuesto.getItems().add(puesto);
            }
        }
        for(Medico medico:Data.getInstance().getMedicos()){
            if(!medico.isOcupado()) comboMedico.getItems().add(medico);
        }
    }    
    
    @FXML
    private void asignarMedico(ActionEvent event) throws IOException {
        Puesto puesto=(Puesto)comboPuesto.getValue();
        Medico medico=(Medico)comboMedico.getValue();
        if(puesto!=null && medico!=null){
            medico.setOcupado(true);
            puesto.setMedicoEncargado(medico);
            Data.getInstance().getPuestosAtendiendo().offer(puesto);
            App.setRoot("quaternary");
        }else{
            //Muestra alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Asignacion de medico");
            alert.setContentText("Campo Medico/Puesto es obligatorio");
            alert.showAndWait();
        }
    }

    @FXML
    private void cerrar(ActionEvent event) throws IOException {
         App.setRoot("quaternary");
    }

}
