package com.example.skillboxmod1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@Profile("init")
public class ContactInit {

    private final ContactService contactService;

    @Value("${app.contacts.file}")
    private String contactsFilePath;

    public ContactInit(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostConstruct
    public void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(contactsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    contactService.addContact(new Contact(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки контактов: " + e.getMessage());
        }
    }
}

