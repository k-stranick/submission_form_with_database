/**==================================================
 Author: Kyle Stranick
 Class: ITN261
 Class Section: 201
 Assignment: Final Project
 Notes: there are some cool features need to go over and update this summer
 use as project piece for resume
 4/23/24
 =====================================================

 Code adapted from: check resources

 ===================================================== */
package dtcc.itn261.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//FUTURE TASKS
// read login information externally
// read SQL scripts from file
// work on date picker
public class InformationFormMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        DatabaseManager.initializeDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(InformationFormMain.class.getResource("information-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1526, 677);
        stage.setTitle("Information Form");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}