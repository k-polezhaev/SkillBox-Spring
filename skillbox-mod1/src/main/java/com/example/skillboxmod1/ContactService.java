package com.example.skillboxmod1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public boolean removeContactByEmail(String email) {
        return contacts.removeIf(contact -> contact.email().equals(email));
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts); // возвращаем копию списка
    }
}

