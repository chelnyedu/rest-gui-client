package com.taxtelecom.chelnyedu.dropwizardclient;

import com.taxtelecom.chelnyedu.dropwizardclient.client.ContactApi;
import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 18.07.17.
 */
public class App {
    public static ContactApi service;
    public static void main(String[] args) throws IOException {
        System.out.println("куку");
        RetrofitClient rc = new RetrofitClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ContactApi.class);

        //getContactByID
        Contact g = rc.gContact(4);
        System.out.println(g.getId() + " " + g.getFirstName() + " " + g.getLastName() + " " + g.getPhone() + " " + g.getMail() + " " + g.getComment());


        //getList
        List<Contact> lists = rc.contactList();
        for (Contact c : lists) {
            System.out.println(c.getId()+" "+ c.getFirstName());
        }


        //deleteContact
        System.out.println(rc.delContact(19));

        //createContact
        Contact cr=new Contact(1,"Olya", "Kuzmina", "9875123", "rere", "cool" );
        System.out.println(rc.creaContact(cr));

        //updateContact
        Contact contactforupdate = new Contact(4, "Jane", "Doe", "+987654321", "rere", "qwerty");
        System.out.println(rc.upContact(contactforupdate));
    }
    public static ContactApi getApi(){
        return service;
    }
}
