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

import javafx.scene.control.ChoiceBox;

public class StatesChoiceBox extends ChoiceBox<String> {

    public StatesChoiceBox() { // constructs a choice box with State information
        super();
        populateStateList();
    }

    public void populateStateList() {
        this.getItems().clear(); // Clear existing items if necessary.
        for (States state : States.values()) {
            this.getItems().add(state.toString());
        }
        this.setValue(States.SELECTION.toString()); // Set default value
    }
}