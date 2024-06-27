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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// setup menu bar
public class FormController {
    @FXML
    private TableView<FormData> tableView;
    @FXML
    private TableColumn<FormData, String> idCol;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<FormData, String> firstNameCol;
    @FXML
    private TableColumn<FormData, String> lastNameCol;
    @FXML
    private TableColumn<FormData, String> phoneNumberCol;
    @FXML
    private TableColumn<FormData, LocalDate> birthdayCol;
    @FXML
    private TableColumn<FormData, String> emailCol;
    @FXML
    private TableColumn<FormData, String> addressCol;
    @FXML
    private TableColumn<FormData, String> cityCol;
    @FXML
    private TableColumn<FormData, String> stateCol;
    @FXML
    private TableColumn<FormData, String> zipCodeCol;
    @FXML
    private TableColumn<FormData, String> notesCol;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField streetAddressTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private StatesChoiceBox statesChoiceBox;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private CustomButtons submitButton;
    @FXML
    private CustomButtons clearButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private CustomButtons searchButton;
    @FXML
    private Button refreshButton;
    private UtilityFunctions utilityLogic;

    public FormController() {
        // no args constructor
    }

    /**
     * Initializes the controller class.
     * This method is called automatically
     * after the fxml file has been loaded.
     */
    @FXML
    protected void initialize() {
        List<Object> textFields = Arrays.asList(firstNameTextField, lastNameTextField, phoneTextField, birthdayDatePicker, emailTextField, streetAddressTextField,
                cityTextField, zipCodeField, notesTextArea, searchField
        );

        // Set up the table columns and their associated data fields
        setupTableColumns();
        this.utilityLogic = new UtilityFunctions(statesChoiceBox, textFields);

        // Clear the form and update the table view when the clear button is clicked
        clearButton.setOnAction(e -> {
            utilityLogic.clearForm();
            updateTableView();
        });

        // Search for a record and update the table view when the search button is clicked
        searchButton.setOnAction(e -> {
            handleSearchButton();
            updateTableView();
        });
        // Submit the form data and update the table view when the submit button is clicked

        submitButton.setOnAction(e -> {
            handleSubmitButton();
            updateTableView();
        });

        // Delete a record and update the table view when the delete button is clicked
        deleteButton.setOnAction(e -> {
            handleDeleteButton();
            updateTableView();
        });

        // Refresh the table view when the refresh button is clicked
        refreshButton.setOnAction(e -> updateTableView());

        // Update the form data and update the table view when the update button is clicked
        updateButton.setOnAction(e -> handleUpdateButton());
    }

    /**
     * Updates the table view with the latest data from the database.
     *
     * @see DatabaseManager#fetchDataFromDatabase()
     */
    public void updateTableView() {
        ObservableList<FormData> updatedData = DatabaseManager.fetchDataFromDatabase();
        tableView.setItems(updatedData);
        tableView.refresh();
    }

    /**
     * Returns a FormData object populated with the latest form inputs.
     *
     * @return a FormData object containing the latest form inputs
     */
    protected FormData returnFormData() {
        FormDataCollector collectionOfInputData = new FormDataCollector();

        return collectionOfInputData.collectFormData(searchField, firstNameTextField, lastNameTextField, phoneTextField, birthdayDatePicker, emailTextField,
                streetAddressTextField, cityTextField, statesChoiceBox, zipCodeField, notesTextArea);
    }

    private boolean isValidDate(LocalDate birthdayDatePicker) {
        return birthdayDatePicker != null;
    }

    /**
     * This function checks if the given email is valid by using a regular expression.
     * The regular expression pattern checks if the email contains a username,
     * an '@' symbol, a domain name, and a top-level domain
     * (e.g., .com, .net, etc.).
     * If the email matches this pattern, the function returns true; otherwise, it returns false.
     *
     * @param email the email to be validated
     * @return true if the email is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3}";
        return email.matches(emailRegex);
    }

    /**
     * This function checks if the given phone number is valid by using a regular expression.
     * The regular expression pattern checks if the phone number is 10 digits long,
     * or if the phone number is in the format (xxx) xxx-xxxx.
     * If the phone number matches this pattern, the function returns true; otherwise, it returns false.
     */
    private boolean isValidPhone(String phone) {
        String phoneRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{7}";
        return phone.matches(phoneRegex);
    }

    /*
    Checks if the given zipCode string matches the specified regular expression pattern.
    If the zipCode matches the pattern, the method returns true; otherwise, it returns false.
    The regular expression pattern checks if the zipCode is 5 digits long,
    or if it is in the format (5 digits) followed by an optional 4-digit extension.
    */
    private boolean isValidZipCode(String zipCode) {
        String zipRegex = "^\\d{5}[-\\s]?(?:\\d{4})?$";
        return zipCode.matches(zipRegex);
    }

    private boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    private boolean isNotSelected(String states) {
        return Objects.equals(states, "Select A State");
    }

    private boolean validateFormData() {
        if (isEmpty(firstNameTextField.getText()) || isEmpty(lastNameTextField.getText()) || isEmpty(streetAddressTextField.getText()) || isEmpty(cityTextField.getText()) ||
                isEmpty(zipCodeField.getText()) || isNotSelected(statesChoiceBox.getValue()) || isEmpty(phoneTextField.getText())) {
            ExceptionAlert.missingInformationAlert();
            return false;
        } else if (!isValidEmail(emailTextField.getText())) {
            ExceptionAlert.invalidEmailAlert();
            return false;
        } else if (!isValidDate(birthdayDatePicker.getValue())) {
            ExceptionAlert.invalidDateAlert();
            return false;
        } else if (!isValidPhone(phoneTextField.getText())) {
            ExceptionAlert.invalidPhoneAlert();
            return false;
        } else if (!isValidZipCode(zipCodeField.getText())) {
            ExceptionAlert.invalidZipCodeAlert();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Sets up the table columns and their associated data fields.
     */
    @FXML
    private void setupTableColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        DatabaseManager.updateTableView(tableView);
    }

    /**
     * This method handles the submission of the form data.
     * It first validates the form data,
     * then saves the form data, inserts it into the database, updates the table view, clears the form,
     * and displays a success alert.
     * If an exception occurs during the process, it logs the error and
     * displays a general error alert.
     */

    private void handleSubmitButton() {
        ObservableList<FormData> dataList = FXCollections.observableArrayList(DatabaseManager.fetchDataFromDatabase());
        if (validateFormData()) {
            FormData formData = returnFormData();  // Update formData with the latest form inputs
            try {
                utilityLogic.saveForm(returnFormData()); // save to CSV file for backup
                DatabaseManager.insertRecord(formData);  // Attempt to insert formData into the database
                tableView.setItems(dataList);
                utilityLogic.clearForm();
                ExceptionAlert.fileSavedAlert();
            } catch (Exception e) {
                ExceptionAlert.couldNotSubmitAlert("Could not save entry: " + e.getMessage());  // A general error alert for other exceptions
                LogUtility.error("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the deletion of a record based on the ID entered into the search field.
     * This method first checks if the search field is not null and contains a non-empty value.
     * It attempts to parse the ID from the search field, delete the corresponding record from the database,
     * and then update the GUI components to reflect the changes.
     * It shows an alert on successful deletion,
     * or if no record is found for the given ID. If the ID format is invalid,
     * it shows an appropriate error alert.
     */
    private void handleDeleteButton() {
        if (searchField != null && !searchField.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(searchField.getText().trim());  // Parse the ID from the search field
                boolean success = DatabaseManager.deleteRecord(id);  // Attempt to delete the record by ID
                if (success) {
                    Platform.runLater(() -> {
                        updateTableView();  // Update the table view to reflect the deletion
                        utilityLogic.clearForm();  // Clear all the form fields
                        ExceptionAlert.fileDeletedAlert(id);  // Show a success alert

                    });
                } else {
                    Platform.runLater(() ->
                            ExceptionAlert.invalidIdFormat("No record found for ID: " + id)  // Now this alert will correctly show
                    );
                }
            } catch (NumberFormatException ex) {
                Platform.runLater(() ->
                                ExceptionAlert.invalidIdFormat("Invalid ID format: " + ex.getMessage())
                        // Optionally show a user alert here
                );
            }
        }
    }

    /**
     * Handles updating a record with the data entered in the form fields based on the ID entered into the search field.
     * It validates the form data before attempting the update.
     * If the form data is valid and an ID is provided,
     * it constructs a FormData object from the form inputs and attempts to update the record in the database.
     * If successful, it updates the table view.
     * It shows error alerts if the ID format is invalid or if no ID is entered.
     */
    private void handleUpdateButton() {
        try {
            if (validateFormData()) { // Assuming this method checks for form completeness
                FormData formData = returnFormData(); // This should construct FormData from form inputs
                if (formData != null && !searchField.getText().isEmpty()) {
                    int id = Integer.parseInt(searchField.getText());
                    DatabaseManager.updateRecord(formData, id);
                    updateTableView();
                } else {
                    ExceptionAlert.invalidIdFormat("Invalid ID format or No ID Entered.");
                }
            }
        } catch (NumberFormatException ex) {
            LogUtility.error("Error parsing ID from search field: " + ex.getMessage());
        } catch (Exception ex) {
            LogUtility.error("An error occurred while updating record: " + ex.getMessage());
        }
    }

    /**
     * Handles the search operation to find and display a record by ID entered into the search field.
     * If the ID is valid and corresponds to an existing record,
     * the method updates the table view to show only this record.
     * If no record is found, it clears the table and shows an error alert.
     * This method ensures that the input is parsed
     * correctly as an integer, handling any format errors appropriately with alerts.
     */
    private void handleSearchButton() {
        try {
            if (searchField != null && !searchField.getText().trim().isEmpty()) {
                int id = Integer.parseInt(searchField.getText().trim());  // Ensure input is trimmed and parsed as integer
                FormData formData = DatabaseManager.fetchDataById(id);  // Fetch data
                if (formData != null) {
                    Platform.runLater(() -> {
                        tableView.getItems().clear();  // Clear existing data
                        tableView.getItems().add(formData);  // Add the fetched data
                    });
                } else {
                    Platform.runLater(() -> {
                        ExceptionAlert.invalidIdFormat("No data found for ID: " + id);
                        tableView.getItems().clear();  // Optional: clear table if no data found
                    });
                }
            }
        } catch (NumberFormatException ex) {
            Platform.runLater(() -> ExceptionAlert.invalidIdFormat("Invalid ID format: " + ex.getMessage()));
        }
    }

    public void handleCloseProgram() {
        UtilityFunctions.closeProgram();
    }

    public void showHelp() {
        ExceptionAlert.helpMenu();
    }

    /**
     * Handles the action of selecting a row in the table view when the user double-clicks on it.
     * This method is triggered by a MouseEvent and checks if the action was a double click.
     * If a row is double-clicked, the method retrieves the FormData object associated with the selected row.
     * It then populates the form fields with the data from the selected record, allowing for easy viewing or editing.
     * This makes interacting with table data more intuitive and efficient by directly linking table selection
     * to form manipulation.
     *
     * @param mouseEvent the MouseEvent that is triggered by this method, used to check the click count
     */
    @FXML
    public void handleSelectedRow(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) { // Check for double-click
            FormData selectedRow = tableView.getSelectionModel().getSelectedItem();
            if (selectedRow != null) {
                firstNameTextField.setText(selectedRow.getFirstName());
                lastNameTextField.setText(selectedRow.getLastName());
                phoneTextField.setText(selectedRow.getPhone());
                birthdayDatePicker.setValue(selectedRow.getBirthday());
                emailTextField.setText(selectedRow.getEmail());
                streetAddressTextField.setText(selectedRow.getAddress());
                cityTextField.setText(selectedRow.getCity());
                statesChoiceBox.setValue(selectedRow.getState());
                zipCodeField.setText(selectedRow.getZipCode());
                notesTextArea.setText(selectedRow.getNotes());
                searchField.setText(String.valueOf(selectedRow.getId()));
            }
        }
    }
}
