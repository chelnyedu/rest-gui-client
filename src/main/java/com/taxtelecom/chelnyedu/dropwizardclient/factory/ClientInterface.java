package com.taxtelecom.chelnyedu.dropwizardclient.factory;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;

import java.io.IOException;
import java.util.List;


public interface ClientInterface {
    List<Contact> getListContact() throws IOException;
    void createContact(Contact contact) throws IOException;
    Contact getContact(int id) throws IOException;
    void updateContact(Contact contact) throws IOException;
    void deleteContact(int id);


}
