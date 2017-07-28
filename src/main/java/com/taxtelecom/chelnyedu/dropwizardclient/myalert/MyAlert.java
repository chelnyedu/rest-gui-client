package com.taxtelecom.chelnyedu.dropwizardclient.myalert;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Class with all type of alerts in gui
 */
public class MyAlert {
    /**
     * Alert about some error in work with server
     */
    public void errorAlert(String s, IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!!!");
        alert.setContentText("Ooops, there was an error in " + s);
        alert.getDialogPane().setPrefSize(400,250);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0,0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
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
