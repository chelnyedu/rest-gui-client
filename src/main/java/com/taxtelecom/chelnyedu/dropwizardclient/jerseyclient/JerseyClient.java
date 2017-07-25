package com.taxtelecom.chelnyedu.dropwizardclient.jerseyclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

public class JerseyClient {
    private Client client = Client.create();
    private WebResource contactResource;
    private ClientResponse response;
    private String url = "http://localhost:8080/contact/";
    private ObjectMapper mapper = new ObjectMapper();

    public List getAllContact() {
        Gson gson = new Gson();
        contactResource = client.resource(url + "all");
        response = contactResource.accept("application/json").get(ClientResponse.class);
        String all = response.getEntity(String.class);
        return gson.fromJson(all, new TypeToken<List<Contact>>() {
        }.getType());
    }

    public String getContact(int id) {
        contactResource = client.resource(url + id);
        response = contactResource.accept("application/json").get(ClientResponse.class);
        return response.getEntity(String.class);
    }

    public Response deleteContact(int id) {
        contactResource = client.resource(url + id);
        contactResource.delete();
        return Response.noContent().entity("Contact was deleted!").build();
    }

    public Response createContact(String firstName, String lastName, String phone,
                                  String mail, String comment) throws IOException {
        contactResource = client.resource(url);
        Contact contact = new Contact(0, firstName, lastName, phone, mail, comment);
        String newContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").post(ClientResponse.class, newContact);
        if (response.getStatus() == 201) {
            return Response.status(302).entity("The contact was created successfully!").build();
        }else return Response.status(422).entity(response.getEntity(String.class)).build();
    }

    public Response updateContact(int id, String firstName, String lastName, String phone,
                                  String mail, String comment) throws IOException {
        contactResource = client.resource(url + id);
        Contact contact = new Contact(id, firstName, lastName, phone, mail, comment);
        String updateContact = mapper.writeValueAsString(contact);
        response = contactResource.type("application/json").put(ClientResponse.class, updateContact);
        if (response.getStatus() == 200) {
            return Response.status(302).entity("The contact was updated successfully!").build();
        }else return Response.status(422).entity(response.getEntity(String.class)).build();
    }
}
