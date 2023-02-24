module is.pipemania {
    requires javafx.controls;
    requires javafx.fxml;
    requires algs4;

    opens is.pipemania.vidmot to javafx.fxml;
    exports is.pipemania.vidmot;
}


