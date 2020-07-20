package ec.edu.espol.turnerocovid19g4;

import ec.edu.espol.turnerocovid19g4.datos.Data;
import ec.edu.espol.turnerocovid19g4.datos.ManejoArchivo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Scene scene1;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primarySecond"), 1049, 594);
        stage.setScene(scene);
        stage.show();
        escenario(scene1);
    }
    
    @Override
    public void stop(){
        ManejoArchivo.savePuestos();
        ManejoArchivo.saveMedicos();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    static void escenario(Scene sc) throws IOException{
        Stage stage1=new Stage();
        sc=new Scene(loadFXML("primary"),1049,594);
        stage1.setScene(sc);
        stage1.show();
    }
    
    static void setroot(String fxml) throws IOException{
        scene1.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}