/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Puesto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author mbpretina
 */
public class PuestoBotonController implements Initializable {
    
    @FXML
    private Button lbPuestoID;
    
    private Puesto puesto;
    
    private Node node; 

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setPuesto(Puesto puesto){
        this.puesto = puesto;
        if (lbPuestoID != null){
            lbPuestoID.setText(puesto.getCodPuesto());
            Cita cita = puesto.getCita();
            if (cita == null) {
            }
            else {
            }
        }
    }
    
    public void puestoSeleccionado(){
    }

    public Button getLbPuestoID() {
        return lbPuestoID;
    }

    public void setLbPuestoID(Button lbPuestoID) {
        this.lbPuestoID = lbPuestoID;
    }

    void puestoNoSeleccionado() {
        System.out.println("Eliminando"+lbPuestoID.getText());
    }

    public Puesto getPuesto() {
        return puesto;
    }
    
    
    
    
}
