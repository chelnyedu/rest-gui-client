package com.taxtelecom.chelnyedu.dropwizardclient.myalert;

import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Created by user on 26.07.17.
 */
public class MyAlert {
    public void errorAlert(String s, IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!!!");
        alert.setContentText("Ooops, there was an error in " + s + " " + e.toString());
        alert.showAndWait();
    }

    public void noSelected() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No selection");
        alert.setHeaderText("No person Selected");
        alert.setContentText("Please select a person in the table");
        alert.showAndWait();
    }

    public void successAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setContentText("Contact was " + s);
        alert.showAndWait();
    }

    public void validationAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation message");
        alert.setContentText("Incorrect contact! Change contact");
        alert.showAndWait();
    }
}
