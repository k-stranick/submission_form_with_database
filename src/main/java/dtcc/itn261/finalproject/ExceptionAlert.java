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

import javafx.scene.control.Alert;
// REFACTOR TO SWITCH STATEMENTS TO EASE CODE

// logic for Alert class
// use public enum next to make code easier to read will do after submission
public class ExceptionAlert {
    private static final String SQL_ERROR = "SQL Exception ";

    private ExceptionAlert() {
    }

    public static void showFileNotFoundAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File ERROR");
        alert.setHeaderText("File Not Found");
        alert.setContentText("The file you are trying to access does not exist.");
        alert.showAndWait();
    }

    public static void showIOAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IO Error");
        alert.setHeaderText("ERROR");
        alert.setContentText("An error occurred while creating the log file");
        alert.showAndWait();
    }

    public static void missingInformationAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Missing Information\nPlease fill in all required fields.");
        alert.show();
    }

    public static void fileSavedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("New Entry Created");
        alert.showAndWait();
    }

    public static void invalidEmailAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Invalid Email\nPlease enter a valid email address.");
        alert.show();
    }

    public static void invalidPhoneAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Invalid Phone Number\nPlease enter a valid phone number.");
        alert.show();
    }

    public static void invalidZipCodeAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Invalid Zip Code\nPlease enter a valid Zip Code.");
        alert.show();
    }

    public static void invalidDateAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Invalid Date\nPlease enter a valid day MM/dd/yyyy.");
        alert.show();
    }

    public static void helpMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("I will add this one day");
        alert.show();
    }


    public static void showSqlQueryAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(SQL_ERROR);
        alert.setContentText("Error: Failed to execute query.");
        alert.showAndWait();
    }

    public static void showSqlConnectionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(SQL_ERROR);
        alert.setContentText("An error occurred while try to access the database.");
        alert.showAndWait();
    }

    public static void showDataBaseCreationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Database Created.");
        alert.showAndWait();
    }

    public static void sqlErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(SQL_ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void couldNotSubmitAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(SQL_ERROR);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static void showFailedInsertionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(SQL_ERROR);
        alert.setContentText("User with same EMAIL or PHONE NUMBER exists.");
        alert.showAndWait();
    }

    public static void invalidIdFormat(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid ID");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static void successfulUpdate(int userId) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Success");
        alert.setContentText("Successfully updated user ID: " + userId);
        alert.showAndWait();
    }

    public static void fileDeletedAlert(int userId) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Deleted");
        alert.setContentText("Successfully deleted user: " + userId);
    }
}