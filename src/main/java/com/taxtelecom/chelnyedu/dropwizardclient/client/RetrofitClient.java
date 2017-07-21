package com.taxtelecom.chelnyedu.dropwizardclient.client;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static com.taxtelecom.chelnyedu.dropwizardclient.App.service;

/**
 * Created by user on 21.07.17.
 */
public class RetrofitClient {
    //private static ContactApi service = App.getApi();
    String ok = "OK";
    String wrong = "WRONG";

    public Contact gContact(int id) throws IOException {
        Call<Contact> c1 = service.getContact(id);
        Contact c = c1.execute().body();
        //System.out.println(c.getId() +" "+c.getFirstName()+" "+c.getLastName()+" "+ c.getPhone()+" " + c.getMail()+" " +c.getComment());
        return c;
    }

    public List<Contact> contactList() throws IOException {
        Call<List<Contact>> call = service.getAllContact();

        List<Contact> contacts = call.execute().body();
       /* for (Contact c: contacts){
            System.out.println(c.getId()+" "+ c.getFirstName());
        }*/
        return contacts;
    }

    public String delContact(int id) throws IOException {
        Call<Contact> del = service.deleteContact(id);
        if (del.execute().isSuccessful()) {
            return ok;
        } else return wrong;
    }

    public String creaContact(Contact contact) throws IOException {
        Call<Contact> create = service.createContact(contact);
        return ok;
    }

    public String upContact(Contact contact) throws IOException {
        Call<Contact> update = service.updateContact(contact.getId(), contact);
        if (update.execute().isSuccessful()) {
            return ok;
        } else return wrong;
    }
}
