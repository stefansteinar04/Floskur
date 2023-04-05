module is.verkefni4 {
    requires javafx.controls;
    requires javafx.fxml;


    exports is.verkefni4.vidmot;
    opens is.verkefni4.vidmot to javafx.fxml;
}