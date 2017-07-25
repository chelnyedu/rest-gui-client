package com.taxtelecom.chelnyedu.dropwizardclient.guiform;


import com.taxtelecom.chelnyedu.dropwizardclient.App;
import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label commentLabel;

    private App mainApp;

    public PhoneBookController(){

    }

    @FXML
    private void initialize() throws IOException {
        initData();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        tableContact.setItems(observableList);
    }



    public void initData() throws IOException {
        List<Contact> contactList = retrofitClient.contactList();
        for (int i=0; i<contactList.size();i++){
            observableList.add(contactList.get(i));
        }
        //ObservableList<Contact> observableList = FXCollections.observableArrayList(contactList);
    }

  /*  @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Contact> contactList = null;
        try {
            contactList = retrofitClient.contactList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Contact> observableList = FXCollections.observableArrayList(contactList);
        tableContact.setItems(observableList);
    }*/
}
