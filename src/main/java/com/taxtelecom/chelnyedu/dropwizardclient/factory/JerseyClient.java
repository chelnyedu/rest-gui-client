package com.taxtelecom.chelnyedu.dropwizardclient.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;


public class JerseyClient implements ClientInterface {
    Client client = Client.create();
    ClientResponse response;
    WebResource contactResource;
    String url = "http://localhost:8080/contact/";
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Contact> getListContact() {

        Gson gson = new Gson();
        contactResource = client.resource(url + "all");
        response = contactResource.accept("application/json").get(ClientResponse.class);
        String all = response.getEntity(String.class);
        return gson.fromJson(all, new TypeToken<List<Contact>>() {
        }.getType());
    }

    @Override
    public void deleteContact(int id) {
        contactResource = client.resource(url + id);
        contactResource.delete();
    }

    @Override
    public void createContact(Contact contact) throws IOException {
        contactResource = client.resource(url);
        String newContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").post(ClientResponse.class, newContact);
    }

    @Override
    public void updateContact(Contact contact) throws IOException {
        contactResource = client.resource(url + contact.getId());
       // Contact contact1 = new Contact(contact.getId(), firstName, lastName, phone, mail, comment);
        String updateContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").put(ClientResponse.class, updateContact);
    }

    @Override
    public Contact getContact(int id) {
        contactResource = client.resource(url + id);
        response = contactResource.accept("application/json").get(ClientResponse.class);
        return response.getEntity(Contact.class);
    }
}
