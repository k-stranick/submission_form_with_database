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

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

// Map?

/**
 * collects data from the provided UI elements
 */
public class FormDataCollector {
    public FormData collectFormData(
            TextField searchBarTextField,
            TextField firstNameTextField,
            TextField lastNameTextField,
            TextField phoneTextField,
            DatePicker birthdayDatePicker,
            TextField emailTextField,
            TextField streetAddressTextField,
            TextField cityTextField,
            StatesChoiceBox statesChoiceBox,
            TextField zipCodeField,
            TextArea notesTextArea) {
        FormData formData = new FormData();
        String idText = searchBarTextField.getText().trim();
        formData.setId(idText.isEmpty() ? 0 : Integer.parseInt(idText));  // Default ID to 0 if empty
        formData.setFirstName(firstNameTextField.getText());
        formData.setLastName(lastNameTextField.getText());
        formData.setPhone(phoneTextField.getText());
        formData.setBirthday(birthdayDatePicker.getValue());
        formData.setEmail(emailTextField.getText());
        formData.setAddress(streetAddressTextField.getText());
        formData.setCity(cityTextField.getText());
        formData.setState(statesChoiceBox.getValue());
        formData.setZipCode(zipCodeField.getText());
        formData.setNotes(notesTextArea.getText());

        return formData;
    }
}
