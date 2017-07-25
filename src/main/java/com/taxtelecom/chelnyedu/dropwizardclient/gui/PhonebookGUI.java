package com.taxtelecom.chelnyedu.dropwizardclient.gui;

import com.taxtelecom.chelnyedu.dropwizardclient.JerseyClient.JerseyClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class PhonebookGUI extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
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

        AnchorPane root = new AnchorPane();

        //LeftSide
        AnchorPane paneLeft = new AnchorPane();
        paneLeft.setPrefSize(300, 300);

        //Table
        TableView table = new TableView();
        table.setPrefSize(300, 250);
        TableColumn<Contact, String> firstName = new TableColumn("First Name");
        firstName.setPrefWidth(150);
        TableColumn<Contact, String> lastName = new TableColumn("Last Name");
        lastName.setPrefWidth(150);
        table.getColumns().addAll(firstName, lastName);

        //add contacts in table
        firstName.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));

        ObservableList<Contact> contact = getContactList();
        table.setItems(contact);


        paneLeft.getChildren().add(table);

        //Left Buttons
        Button newContact = new Button("Add contact");
        Button deleteContact = new Button("Delete contact");
        AnchorPane.setBottomAnchor(newContact, 5.0);
        AnchorPane.setBottomAnchor(deleteContact, 5.0);
        AnchorPane.setLeftAnchor(newContact, 5.0);
        AnchorPane.setRightAnchor(deleteContact, 5.0);
        paneLeft.getChildren().addAll(newContact, deleteContact);


        //RightSide
        AnchorPane paneRigth = new AnchorPane();
        paneRigth.setPrefSize(300, 300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);

        Label firstNameLabel = new Label(" First Name: ");
        firstNameTextField = new TextField();
        Label lastNameLabel = new Label(" Last Name: ");
        lastNameTextField = new TextField();
        Label phoneLabel = new Label(" Phone: ");
        phoneTextField = new TextField();
        Label mailLabel = new Label(" Mail: ");
        mailTextField = new TextField();
        Label commentLabel = new Label(" Comment: ");
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
        Button editButton = new Button("Change contact");
        AnchorPane.setBottomAnchor(editButton, 5.0);
        AnchorPane.setLeftAnchor(editButton, 75.0);
        paneRigth.getChildren().add(editButton);


        //SplitPanel
        SplitPane pane = new SplitPane();
        pane.setLayoutX(5.0);
        pane.setLayoutY(5.0);
        pane.setPrefSize(600, 300);

        pane.getItems().addAll(paneLeft, paneRigth);
        root.getChildren().add(pane);


        primaryStage.setTitle("PhoneBook");
        primaryStage.setScene(new Scene(root, 610, 310));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ObservableList<Contact> getContactList() {
        List<Contact> arrayList = client.getAllContact();
        ObservableList<Contact> list = FXCollections.observableArrayList(arrayList);
        return list;
    }

    private void showContactDetails() {
        Contact contact = new Contact(1, "2", "3", "4", "5", "6");

        firstNameTextField.setText(contact.getFirstName());
        lastNameTextField.setText(contact.getLastName());
        phoneTextField.setText(contact.getPhone());
        mailTextField.setText(contact.getMail());
        commentTextField.setText(contact.getComment());
    }


}
