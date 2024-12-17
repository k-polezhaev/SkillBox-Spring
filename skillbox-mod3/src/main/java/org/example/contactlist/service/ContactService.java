package org.example.contactlist.service;

import org.example.contactlist.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> selectAll();
    Contact selectContact(long id);
    void addContact(Contact contact);

    void deleteContact(long id);

   void updateContact(Contact contact);
   void addListContact(List<Contact> contactList);
}
