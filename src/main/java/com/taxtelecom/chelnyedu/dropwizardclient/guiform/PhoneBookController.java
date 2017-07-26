package com.taxtelecom.chelnyedu.dropwizardclient.guiform;

import com.taxtelecom.chelnyedu.dropwizardclient.App;
import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by user on 24.07.17.
 */
public class PhoneBookController implements Initializable{
    private ResourceBundle resourceBundle;
    RetrofitClient retrofitClient = new RetrofitClient();
    private ObservableList<Contact> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Contact> tableContact;
    @FXML
    private TableColumn<Contact, String> firstNameColumn;
    @FXML
    private TableColumn<Contact, String> lastNameColumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField commentField;


    public PhoneBookController(){

    }


    public void initData(){
        List<Contact> contactList = new ArrayList<>();
            try {
                contactList = retrofitClient.contactList();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!!!");
                alert.setContentText("Ooops, there was an error in filling listcontact!"+e.toString());
                alert.showAndWait();
            }

        for (int i=0; i<contactList.size();i++){
            observableList.add(contactList.get(i));
        }
    }


    public void showСontactDetails(Contact contact){
        if(contact!=null) {
            firstNameField.setText(contact.getFirstName());
            lastNameField.setText(contact.getLastName());
            phoneField.setText(contact.getPhone());
            mailField.setText(contact.getMail());
            commentField.setText(contact.getComment());
        }else {
            firstNameField.setText("");
            lastNameField.setText("");
            phoneField.setText("");
            mailField.setText("");
            commentField.setText("");
        }

    }


    public void handleDeleteContact() throws IOException{
        try{
           int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Dialog");
            confirm.setContentText("Are you sure about this?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK){
                deleteContact(selectedIndex);

            } else {
                confirm.close();
            }

        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No person Selected");
            alert.setContentText("Please select a person in the table");
            alert.showAndWait();
        }
    }

    public  void handleAddContact(){
        if (isValidContact()){
            Contact newContact = new Contact(1, firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), mailField.getText(), commentField.getText());
            addContact(newContact);
        }
    }

    public void handleUpdateContact() throws IOException{
        int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
        if (isValidContact()){
            Contact contactForUpdate = new Contact(selectedIndex,firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), mailField.getText(), commentField.getText() );
            updateContact(contactForUpdate);
        }


    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        this.resourceBundle = resources;
        //инициализация таблицы и привязка табличных значений к значениям модели
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>(("firstName")));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>(("lastName")));
        initData();

        tableContact.setItems(observableList);
        //изменить и убрать изначальную загрузку
        showСontactDetails(null);
        //слушатель
        tableContact.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue)->showСontactDetails(newValue));
    }

    private void deleteContact(int index){
        try {
            retrofitClient.delContact(index);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Ooops, there was an error in deleting! "+e.toString());
            alert.showAndWait();
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    private void addContact(Contact newContact){
        try {
            retrofitClient.creaContact(newContact);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setContentText("Contact was created!");
            alert.showAndWait();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Ooops, there was an error in adding new contact! "+e.toString());
            alert.showAndWait();
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    private boolean isValidContact(){
        if (firstNameField.getLength() < 2 ||
                lastNameField.getLength() <  2
                || phoneField.getText().matches("^[a-zA-Z]+$")
                || phoneField.getLength()<2 || mailField.getLength() <2){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation message");
            alert.setContentText("Incorrect contact! Change contact");
            alert.showAndWait();
            return false;
        }else return true;
    }

    private void updateContact(Contact contact){
        try {
            retrofitClient.upContact(contact);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setContentText("Contact was updated");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Ooops, there was an error in adding new contact! "+e.toString());
            alert.showAndWait();
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }
}
