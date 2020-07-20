package ec.edu.espol.turnerocovid19g4;

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
    private static Stage stage1;
    private static Stage stage;
    private static Stage stage2;
    private static Scene scene2;

    @Override
    public void start(Stage s) throws IOException {
        stage=new Stage();
        scene = new Scene(loadFXML("administrationMenu"), 290, 375);
        stage.setScene(scene);
        stage.setTitle("TurneroCovid19G4");
        stage.show();
        escenario(scene1,stage1);
        stage2=new Stage();
        scene2 = new Scene(loadFXML("patientsManagement"), 1049, 594);
        stage2.setScene(scene2);
        stage2.show();
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
    
    static void escenario(Scene sc, Stage stage1) throws IOException{
        stage1=new Stage();
        sc=new Scene(loadFXML("queueManagement"),720,480);
        stage1.setScene(sc);
        stage1.setTitle("TurneroCovid19G4");
        stage1.show();
    }
    
    static void setroot(String fxml) throws IOException{
        scene1.setRoot(loadFXML(fxml));
    }
    static void setTamano(int a,int h){
        stage.setHeight(h);stage.setWidth(a);
    }

    public static void main(String[] args) {
        launch();
    }

}