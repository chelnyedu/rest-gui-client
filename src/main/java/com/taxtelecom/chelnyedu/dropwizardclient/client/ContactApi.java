package com.taxtelecom.chelnyedu.dropwizardclient.client;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by user on 20.07.17.
 */
public interface ContactApi {
    @GET("contact/all")
    Call<List<Contact>> getAllContact();


    @GET("contact/{id}")
    Call<Contact> getContact(@Path("id") int id);

    @DELETE("contact/{id}")
    Call<Contact> deleteContact(@Path("id") int id);

    @POST("contact/")
    Call<Contact> createContact(@Body Contact contact);

    @PUT("contact/{id}")
    Call<Contact> updateContact(@Path("id") int id, @Body Contact contact);

}
