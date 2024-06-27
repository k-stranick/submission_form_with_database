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

import javafx.application.Platform;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UtilityFunctions {
    private final File filePath = new File("src/main/resources/test.csv");
    private final StatesChoiceBox statesComboBox;
    private final List<Object> textFields;

    public UtilityFunctions(StatesChoiceBox statesComboBox, List<Object> textFields) {
        this.statesComboBox = statesComboBox;
        this.textFields = textFields;
    }

    private static String getFormattedCsvString(FormData formData) {
        String dateString = (formData.getBirthday() != null) ? formData.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
        String csvLine;
        csvLine = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                formData.getFirstName(),
                formData.getLastName(),
                formData.getPhone(),
                dateString,
                formData.getEmail(),
                formData.getAddress(),
                formData.getCity(),
                formData.getState(),
                formData.getZipCode(),
                formData.getNotes());

        return csvLine;
    }

    public static void closeProgram() {
        Platform.exit();
    }

    public void clearForm() {
        statesComboBox.setValue(String.valueOf(States.SELECTION));
        for (Object textField : textFields) {
            if (textField instanceof TextInputControl textInputControl) {
                textInputControl.clear();
            } else if (textField instanceof DatePicker datePicker) {
                datePicker.setValue(null);
            }
        }
    }

    public void saveForm(FormData formData) throws IOException {// save as csv
        checkFileAvailability(filePath);
        String csvLine = getFormattedCsvString(formData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // Append mode
            writer.write(csvLine); // Append the new line to the CSV
        } catch (IOException e) {
            LogUtility.error(e.getMessage());
            ExceptionAlert.showIOAlert();
        }
    }

    private void checkFileAvailability(File file) throws IOException {
        if (!file.exists() && !file.createNewFile()) {
            ExceptionAlert.showFileNotFoundAlert();
            LogUtility.error("Failed to create file: " + file.getAbsolutePath());
            throw new IOException("Failed to create file: " + file.getAbsolutePath());
        }
    }
}
