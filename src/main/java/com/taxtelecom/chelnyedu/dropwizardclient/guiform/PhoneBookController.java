package com.taxtelecom.chelnyedu.dropwizardclient.guiform;

import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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


    public void initData() throws IOException {
        List<Contact> contactList = retrofitClient.contactList();
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
        int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
        retrofitClient.delContact(selectedIndex);
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    public  void handleAddContact() throws IOException {
        Contact newContact = new Contact(1, firstNameField.getText(), lastNameField.getText(),
                phoneField.getText(), mailField.getText(), commentField.getText());
        retrofitClient.creaContact(newContact);
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }

    public void handleUpdateContact() throws IOException{
        int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
        Contact contactForUpdate = new Contact(selectedIndex,firstNameField.getText(), lastNameField.getText(),
                phoneField.getText(), mailField.getText(), commentField.getText() );
        retrofitClient.upContact(contactForUpdate);
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        this.resourceBundle = resources;
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //инициализация таблицы и привязка табличных значений к значениям модели
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>(("firstName")));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>(("lastName")));
        tableContact.setItems(observableList);
        //изменить и убрать изначальную загрузку
        showСontactDetails(null);
        //слушатель
        tableContact.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue)->showСontactDetails(newValue));
    }
}
