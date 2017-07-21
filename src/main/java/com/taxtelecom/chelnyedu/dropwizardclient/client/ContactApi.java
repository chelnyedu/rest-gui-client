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

    @GET("contact/getContact")
    Call<Contact> getContact(@Query("id") int id);

    @DELETE("contact/deleteContact")
    Call<Contact> deleteContact(@Query("id") int id);

    @POST("contact/")
    Call<Contact> createContact(@Body Contact contact);

    @PUT("contact/updateContact")
    Call<Contact> updateContact(@Query("id") int id, @Body Contact contact);

}
