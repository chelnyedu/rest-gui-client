package com.taxtelecom.chelnyedu.dropwizardclient.factory;

import com.taxtelecom.chelnyedu.dropwizardclient.JerseyClient.JerseyClient;
import com.taxtelecom.chelnyedu.dropwizardclient.client.RetrofitClient;

/**
 * Created by user on 27.07.17.
 */
public class Factory {
    public InterfaceClient getClienttype(String type) {
        if (type.equals("jersey")) {
            return new JerseyClient();
        }
        if (type.equals("retrofit")) {
            return new RetrofitClient();
        }
        return null;
    }
}
