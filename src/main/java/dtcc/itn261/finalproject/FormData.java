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

import javafx.beans.property.*;
import java.time.LocalDate;

/**
 * The FormData class is designed for managing the data of a form in a JavaFX application.
 * It uses JavaFX properties to automatically update the UI when data changes and to facilitate easy data binding.
 * Each form attribute like ID, name, phone number, etc., is represented by a property.
 * This allows direct integration with JavaFX components and supports responsive and interactive UIs.
 * Not fully used but will implement when I refactor my final this summer
 */
public class FormData {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phone;
    private final SimpleObjectProperty<LocalDate> birthday;
    private final StringProperty email;
    private final StringProperty address;
    private final StringProperty city;
    private final StringProperty state;
    private final StringProperty zipCode;
    private final StringProperty notes;

    // No Parameter Constructor
    public FormData() {
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<>();
        this.email = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.zipCode = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
    }

    // Getters and Setters
    public int getId() {
        return id.get();
    }

    void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getState() {
        // Adding a null check for safety
        return !"Select A State".equals(state.get()) ? state.get() : "";
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}
