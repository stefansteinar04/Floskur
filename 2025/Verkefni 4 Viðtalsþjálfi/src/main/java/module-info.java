module hi.interview {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;


    exports hi.interview.vidmot;
    opens hi.interview.vidmot to javafx.fxml;
}