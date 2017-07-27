package com.taxtelecom.chelnyedu.dropwizardclient.factory;

import com.taxtelecom.chelnyedu.dropwizardclient.client.ContactApi;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


public class RetrofitClient implements ClientInterface{

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ContactApi service = retrofit.create(ContactApi.class);

    @Override
    public List<Contact> getListContact() throws IOException{
            Call<List<Contact>> call = service.getAllContact();
        List<Contact> contacts = call.execute().body();
            return contacts;
    }

    @Override
    public void createContact(Contact contact){
        Call<Contact> create = service.createContact(contact);
        create.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }

    @Override
    public void updateContact(Contact contact){
        service.updateContact(contact.getId(), contact);
    }

    @Override
    public void deleteContact(int id){
       service.deleteContact(id);
    }
}
