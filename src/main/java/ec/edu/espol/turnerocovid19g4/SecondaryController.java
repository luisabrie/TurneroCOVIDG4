package ec.edu.espol.turnerocovid19g4;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SecondaryController {

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
    private Button bttGuardar;
    
    @FXML
    private void guardar(){
        
    };
    @FXML
    private void cerrar(){
        
    };
}