package com.taxtelecom.chelnyedu.dropwizardclient.guiform;

import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 24.07.17.
 */
public class PhoneBookController{
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
    @FXML
    private Button buttonDel;


    public PhoneBookController(){

    }

    @FXML
    private void initialize() throws IOException {
        initData();
        //инициализация таблицы и привязка табличных значений к значениям модели
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        tableContact.setItems(observableList);

        showСontactDetails(null);
        //слушатель
        tableContact.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue)->showСontactDetails(newValue));

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
            firstNameField.setText(" ");
            lastNameField.setText(" ");
            phoneField.setText(" ");
            mailField.setText(" ");
            commentField.setText(" ");
        }

    }


    public void handleDeleteContact() throws IOException{
        int selectedIndex = tableContact.getSelectionModel().getSelectedItem().getId();
        retrofitClient.delContact(selectedIndex);
        tableContact.getItems().removeAll(observableList);
        initData();
        tableContact.setItems(observableList);
    }


}
