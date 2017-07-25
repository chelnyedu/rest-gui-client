package com.taxtelecom.chelnyedu.dropwizardclient.gui;

import com.taxtelecom.chelnyedu.dropwizardclient.JerseyClient.JerseyClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PhonebookGUI extends Application {

    private ObservableList<Contact> contactList;
    private JerseyClient client = new JerseyClient();
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField phoneTextField;
    private TextField mailTextField;
    private TextField commentTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ResourceBundle bundle = ResourceBundle.getBundle("locale",new Locale("en"));
        primaryStage.setTitle(bundle.getString("title"));

        AnchorPane root = new AnchorPane();

        //LeftSide
        AnchorPane paneLeft = new AnchorPane();
        paneLeft.setPrefSize(300, 300);

        //Table
        final TableView table = new TableView();
        table.setPrefSize(300, 250);
        final TableColumn<Contact, String> firstName = new TableColumn(bundle.getString("column.FirstName"));
        firstName.setPrefWidth(150);
        TableColumn<Contact, String> lastName = new TableColumn(bundle.getString("column.LastName"));
        lastName.setPrefWidth(150);
        table.getColumns().addAll(firstName, lastName);

        //add contacts in table
        firstName.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));

        table.setItems(getContactList());

        //cell click action
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
           @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
               if (newValue != null) {
                   Contact selectedUser = (Contact) newValue;
                   firstNameTextField.setText(selectedUser.getFirstName());
                   lastNameTextField.setText(selectedUser.getLastName());
                   phoneTextField.setText(selectedUser.getPhone());
                   mailTextField.setText(selectedUser.getMail());
                   commentTextField.setText(selectedUser.getComment());
               } else {
                   firstNameTextField.clear();
                   lastNameTextField.clear();
                   phoneTextField.clear();
                   mailTextField.clear();
                   commentTextField.clear();
               }
            }
        });

        paneLeft.getChildren().add(table);

        //Left Buttons
        Button newContact = new Button(bundle.getString("button.AddContact"));
        Button deleteContact = new Button(bundle.getString("button.DeleteContact"));
        AnchorPane.setBottomAnchor(newContact, 5.0);
        AnchorPane.setBottomAnchor(deleteContact, 5.0);
        AnchorPane.setLeftAnchor(newContact, 5.0);
        AnchorPane.setRightAnchor(deleteContact, 5.0);
        paneLeft.getChildren().addAll(newContact, deleteContact);

        newContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    client.createContact(firstNameTextField.getText(), lastNameTextField.getText(),
                            phoneTextField.getText(), mailTextField.getText(), commentTextField.getText());

                    table.setItems(getContactList());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Contact delContact = (Contact) table.getSelectionModel().getSelectedItem();
                client.deleteContact(delContact.getId());

                table.setItems(getContactList());
            }
        });


        //RightSide
        AnchorPane paneRigth = new AnchorPane();
        paneRigth.setPrefSize(300, 300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);

        Label firstNameLabel = new Label(bundle.getString("label.FirstName"));
        firstNameTextField = new TextField();
        Label lastNameLabel = new Label(bundle.getString("label.LastName"));
        lastNameTextField = new TextField();
        Label phoneLabel = new Label(bundle.getString("label.Phone"));
        phoneTextField = new TextField();
        Label mailLabel = new Label(bundle.getString("label.Mail"));
        mailTextField = new TextField();
        Label commentLabel = new Label(bundle.getString("label.Comment"));
        commentTextField = new TextField();

        grid.add(firstNameLabel, 1, 1);
        grid.add(firstNameTextField, 2, 1);
        grid.add(lastNameLabel, 1, 2);
        grid.add(lastNameTextField, 2, 2);
        grid.add(phoneLabel, 1, 3);
        grid.add(phoneTextField, 2, 3);
        grid.add(mailLabel, 1, 4);
        grid.add(mailTextField, 2, 4);
        grid.add(commentLabel, 1, 5);
        grid.add(commentTextField, 2, 5);
        paneRigth.getChildren().add(grid);

        //Right button
        Button editButton = new Button(bundle.getString("button.EditContact"));
        AnchorPane.setBottomAnchor(editButton, 5.0);
        AnchorPane.setLeftAnchor(editButton, 75.0);
        paneRigth.getChildren().add(editButton);

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Contact updateContact = (Contact) table.getSelectionModel().getSelectedItem();

                    client.updateContact(updateContact.getId(), firstNameTextField.getText(), lastNameTextField.getText(),
                            phoneTextField.getText(), mailTextField.getText(), commentTextField.getText());

                    table.setItems(getContactList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //SplitPanel
        SplitPane pane = new SplitPane();
        pane.setLayoutX(5.0);
        pane.setLayoutY(5.0);
        pane.setPrefSize(600, 300);

        pane.getItems().addAll(paneLeft, paneRigth);
        root.getChildren().add(pane);


        primaryStage.setScene(new Scene(root, 610, 310));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ObservableList<Contact> getContactList() {
        List<Contact> arrayList = client.getAllContact();
        ObservableList<Contact> list = FXCollections.observableArrayList(arrayList);
        return list;
    }
}
