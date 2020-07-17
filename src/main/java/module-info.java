module ec.edu.espol.turnerocovid19g4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;

    opens ec.edu.espol.turnerocovid19g4 to javafx.fxml;
    exports ec.edu.espol.turnerocovid19g4;
}