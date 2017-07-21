package com.taxtelecom.chelnyedu.dropwizardclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.taxtelecom.chelnyedu.dropwizardclient.client.ContactApi;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.07.17.
 */
public class App {
    private static ContactApi service;
    public static void main(String[] args) throws IOException {
        System.out.println("куку");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ContactApi.class);
//getAll--------------------------------------------------------
        Call<List<Contact>> call = service.getAllContact();

        List<Contact> contactList = call.execute().body();
        for (Contact c: contactList){
            System.out.println(c.getId()+" "+ c.getFirstName());
        }

        //getContact------------------------------------------------------
        Call<Contact> c1=service.getContact(4);
        Contact c=c1.execute().body();
        System.out.println(c.getId() +" "+c.getFirstName()+" "+c.getLastName()+" "+ c.getPhone()+" " + c.getMail()+" " +c.getComment());
//deleteContact------------------------------------------------------
        try {
            Call<Contact> del = service.deleteContact(17);
            System.out.println(service.deleteContact(17).execute().raw());
            //System.out.println("Contact was deleted");
        }catch (Throwable t){
            System.out.println("Something wrong! Contact was not deleted!!!!!!!!!!!!!!!!!!!!!");
        }
        //createContact---------------------------------------------------------------
        Contact cr=new Contact(1,"Olya", "Kuzmina", "9875123", "rere", "cool" );
        Call<Contact> create = service.createContact(cr);


        //updateContact--------------------------------------------------------------

        Contact contactforupdate = new Contact(4, "Jane", "Doe", "+987654321", "rere", "qwerty");
        Call<Contact> update=service.updateContact(4, contactforupdate);
        System.out.println(update.execute().raw());

    }

    public static ContactApi getApi(){
        return service;
    }
}
