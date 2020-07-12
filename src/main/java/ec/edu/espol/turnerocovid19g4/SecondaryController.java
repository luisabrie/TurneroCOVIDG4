package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.modelo.Paciente;
import ec.edu.espol.turnerocovid19g4.modelo.Paciente.Genero;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable{

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private TextField nombreTexto;
    
    @FXML
    private TextField apellidosTexto;
    
    @FXML
    private TextField cedulaTexto;
    
    @FXML
    private DatePicker fechaNac ;
    
    @FXML
    private ComboBox comboGenero ;
    
    @FXML
    private ComboBox comboSintoma;
    
    @FXML
    private Button bttGuardar;
    
    @FXML
    private void guardar() throws IOException{
        String nombre=nombreTexto.getText();
        String apellidos=apellidosTexto.getText();
        String cedula=cedulaTexto.getText();
        Genero genero=(Genero) comboGenero.getValue();
        LocalDate fecha=fechaNac.getValue();
        String sintoma=(String)comboSintoma.getValue();
        if (cedula.trim().length()>0 && nombre.trim().length()>0 && apellidos.trim().length()>0 && sintoma.trim().length()>0){
            Paciente persona = new Paciente(fecha,genero,cedula,nombre,apellidos,sintoma);
            //Falta relacionar los controladores para ir almacenando todo en un lista.
            App.setRoot("primary");
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
        App.setRoot("primary");
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //comboGenero.getItems().add("HOMBRE");
        //comboGenero.getItems().add("MUJER");
        comboGenero.getItems().setAll(Genero.values());
        
        comboSintoma.getItems().add("vomito");
        comboSintoma.getItems().add("fiebre");
        comboSintoma.getItems().add("resfrio");
        comboSintoma.getItems().add("congestion nasal");
        comboSintoma.getItems().add("tos");
        comboSintoma.getItems().add("dolor en el pecho");
        comboSintoma.getItems().add("dolor de garganta");
        comboSintoma.getItems().add("irritacion de piel");
        comboSintoma.getItems().add("dificultad respiratoria");
        comboSintoma.getItems().add("diarrea");
        comboSintoma.getItems().add("nauseas");
        comboSintoma.getItems().add("cansancio");
    }
}