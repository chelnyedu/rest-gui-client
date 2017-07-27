package com.taxtelecom.chelnyedu.dropwizardclient.factory;

public class Factory {

    public ClientInterface getClientType(String type){
        if(type.equals("jersey")){
            return new JerseyClient();
        }
        if(type.equals("retrofit")){
            return new RetrofitClient();
        }
       return null;
    }
}
