package com.taxtelecom.chelnyedu.dropwizardclient.JerseyClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.taxtelecom.chelnyedu.dropwizardclient.factory.InterfaceClient;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Jersey Client including methods of CRUD
 * Create connection
 */
public class JerseyClient implements InterfaceClient {
    private Client client = Client.create();
    private WebResource contactResource;
    private ClientResponse response;
    private String url = "http://localhost:8080/";
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Method for get list of contacts
     */
    @Override
    public List<Contact> getListContact() throws IOException {
        Gson gson = new Gson();
        contactResource = client.resource(url + "contact/all");
        response = contactResource.accept("application/json").get(ClientResponse.class);
        String all = response.getEntity(String.class);
        return gson.fromJson(all, new TypeToken<List<Contact>>() {
        }.getType());
    }

    /**
     * Method for get contact by id
     */
    @Override
    public Contact getContact(int id) throws IOException {
        Gson gson = new Gson();
        contactResource = client.resource(url + "contact/id");
        response = contactResource.accept("application/json").get(ClientResponse.class);
        String all = response.getEntity(String.class);
        return gson.fromJson(all, new TypeToken<Contact>() {
        }.getType());
    }

    /**
     * Method for insert new Contact
     * @param contact
     */
    @Override
    public void createContact(Contact contact) throws IOException {
        contactResource = client.resource(url+"contact/");
        String newContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").post(ClientResponse.class, newContact);
    }

    /**
     * Method for delete contact
     * @param id
     */
    @Override
    public void deleteContact(int id) throws IOException {
        contactResource = client.resource(url +"contact/" +id);
        contactResource.delete();
    }

    /**
     * Method for update contact
     * @param contact
     */
    @Override
    public void updateContact(Contact contact) throws IOException {
        contactResource = client.resource(url +"contact/"+ contact.getId());
        // Contact contact1 = new Contact(contact.getId(), firstName, lastName, phone, mail, comment);
        String updateContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").put(ClientResponse.class, updateContact);
    }
}
