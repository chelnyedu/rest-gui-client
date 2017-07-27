package com.taxtelecom.chelnyedu.dropwizardclient.guiform;

import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.myalert.MyAlert;
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
 * Generate GUI form from fxml and control on all action at form
 */
public class PhoneBookController implements Initializable{
    private ResourceBundle resourceBundle;
    RetrofitClient retrofitClient = new RetrofitClient();
    public ObservableList<Contact> observableList = FXCollections.observableArrayList();
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


    MyAlert myAlert = new MyAlert();


    public PhoneBookController(){

    }

    /**
     * fill observableList
     */
    public void initData(){
        List<Contact> contactList = new ArrayList<>();
            try {
                contactList = retrofitClient.getListContact();
            } catch (IOException e) {
                String s = "filling list contact";
                myAlert.errorAlert(s, e);
            }

        for (int i=0; i<contactList.size();i++){
            observableList.add(contactList.get(i));
        }
        tableContact.setItems(observableList);
    }


    /**
     * fill TextFields
     * @param contact
     */
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

    /**
     * Button of deleting Contact
     */
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
            myAlert.noSelected();
        }
    }

    /**
     * Button of creating contact
     */
    public  void handleAddContact(){
        if (isValidContact()){
            Contact newContact = new Contact(1, firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), mailField.getText(), commentField.getText());
            addContact(newContact);
        }
    }

    /**
     * Button of updating Contact
     */
    public void handleUpdateContact() throws IOException{
        int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
        if (isValidContact()){
            Contact contactForUpdate = new Contact(selectedIndex,firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), mailField.getText(), commentField.getText() );
            updateContact(contactForUpdate);
        }

    }

    /**
     * initialize with start appkication
     * listener to TableView
     */

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

   /*public void tryShowData() {
                initData();
       tableContact.setItems(observableList);
    }*/

    /**
     * Method of deleting contact.
     */
    private void deleteContact(int index){
        try {
            retrofitClient.deleteContact(index);
        } catch (IOException e) {
            String s = "deleting";
            myAlert.errorAlert(s, e);
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    /**
     * Method of adding contact.
     */
    private void addContact(Contact newContact){
        try {
            retrofitClient.createContact(newContact);
            myAlert.successAlert("created!");
        }catch (IOException e){
            String s = "adding new contact!";
            myAlert.errorAlert(s, e);
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    /**
     * Validation of TextField
     */
    private boolean isValidContact(){
        if (firstNameField.getLength() < 2 ||
                lastNameField.getLength() <  2
                || phoneField.getText().matches("^[a-zA-Z]+$")
                || phoneField.getLength()<2 || mailField.getLength() <2){
            myAlert.validationAlert();
            return false;
        }else return true;
    }

    /**
     * Method of updating contact.
     */
    private void updateContact(Contact contact){
        try {
            retrofitClient.updateContact(contact);
            myAlert.successAlert("updated!");
        } catch (IOException e) {
            String s = "updating contact!";
            myAlert.errorAlert(s, e);
        }
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }
}
