package com.taxtelecom.chelnyedu.dropwizardclient.client;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


/**
 * Retrofit Client including methods of CRUD
 * Create connection
 */
public class RetrofitClient {
    String ok = "OK";
    String wrong = "WRONG";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ContactApi service = retrofit.create(ContactApi.class);

    /**
     * Method for get contact by id
     */
    public Contact gContact(int id) throws IOException {
        Call<Contact> c1 = service.getContact(id);
        Contact c = c1.execute().body();
        return c;
    }

    /**
     * Method for get list of contacts
     */
    public List<Contact> contactList() throws IOException {
        Call<List<Contact>> call = service.getAllContact();
        List<Contact> contacts = call.execute().body();
        return contacts;
    }

    /**
     * Method for delete contact
     * @param id
     */
    public String delContact(int id) throws IOException {
        Call<Contact> del = service.deleteContact(id);
        if (del.execute().isSuccessful()) {
            return ok;
        } else return wrong;
    }

    /**
     * Method for insert new Contact
     * @param contact
     */
    public String creaContact(Contact contact) throws IOException {
        Call<Contact> create = service.createContact(contact);
        create.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
        return ok;
    }

    /**
     * Method for update contact
     * @param contact
     */
    public String upContact(Contact contact) throws IOException {
        Call<Contact> update = service.updateContact(contact.getId(), contact);
        if (update.execute().isSuccessful()) {
            return ok;
        } else return wrong;
    }
}
