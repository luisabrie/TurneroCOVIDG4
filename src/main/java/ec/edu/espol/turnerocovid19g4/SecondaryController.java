package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.modelo.Cita;
import ec.edu.espol.turnerocovid19g4.modelo.Paciente;
import ec.edu.espol.turnerocovid19g4.modelo.Paciente.Genero;
import ec.edu.espol.turnerocovid19g4.modelo.Sintoma;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
        Genero genero = (Genero)comboGenero.getValue();
        LocalDate fecha=fechaNac.getValue();
        Sintoma sintoma = (Sintoma)comboSintoma.getValue();
        if (cedula.trim().length()>0 && nombre.trim().length()>0 && apellidos.trim().length()>0 
                && sintoma!=null && genero!=null && fecha!=null){
            Paciente persona = new Paciente(fecha,genero,cedula,nombre,apellidos);
            //Repeticion de sintoma en paciente y en cita
            Data.getInstance().nuevaCita(new Cita(persona,sintoma));
            //Se podria agregar un estado de cita para guardarlo en txt y cargarlo al cerrar sistema
            registrarPaciente(persona);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboGenero.getItems().add(Genero.HOMBRE);
        comboGenero.getItems().add(Genero.MUJER);
        for(Sintoma sintoma: Data.getInstance().getSintomas()){
            comboSintoma.getItems().add(sintoma);
        }
    }
    
    public void registrarPaciente(Paciente paciente){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/pacientes.txt", true))){
            String info = paciente.getNombre()+"|"+paciente.getApellido()+"|"+paciente.getCedula();
            bw.append(info+"\n"); 
    	}catch(IOException ex) {
    		System.out.println(ex.getMessage());
    	}
    }

    
}