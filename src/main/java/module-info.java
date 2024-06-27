module dtcc.itn.finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens dtcc.itn261.finalproject to javafx.fxml;
    exports dtcc.itn261.finalproject;
}