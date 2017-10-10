package com.taxtelecom.chelnyedu.dropwizardclient.resources;

/**
 * Class for representation of contact
 */
public class Contact {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String mail;
    private final String comment;

    public Contact(int id, String firstName, String lastName, String phone, String mail, String comment){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail != null ?  mail : "";
        this.comment = comment != null ? comment : "";
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail(){ return mail; }

    public String getComment(){ return comment; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (mail != null ? !mail.equals(contact.mail) : contact.mail != null) return false;
        return comment != null ? comment.equals(contact.comment) : contact.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return String.valueOf(id) + '\n' + firstName + ' ' + lastName + '\n' + phone + '\n' + mail + '\n' + comment;
    }
}
