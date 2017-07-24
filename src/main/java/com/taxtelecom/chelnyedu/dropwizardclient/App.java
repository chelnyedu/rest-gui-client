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
    }
}
