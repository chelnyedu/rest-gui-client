package com.taxtelecom.chelnyedu.dropwizardclient.factory;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.07.17.
 */
public interface InterfaceClient {
    List<Contact> contact = new ArrayList<>();

    List<Contact> getListContact() throws IOException;

    Contact getContact(int id) throws IOException;

    void createContact(Contact contact) throws IOException;

    void deleteContact(int id) throws IOException;

    void updateContact(Contact contact) throws IOException;

}
