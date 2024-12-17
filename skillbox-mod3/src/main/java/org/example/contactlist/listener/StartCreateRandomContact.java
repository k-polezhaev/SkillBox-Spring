package org.example.contactlist.listener;


import io.github.serpro69.kfaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.example.contactlist.entity.Contact;
import org.example.contactlist.service.ContactService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "environment", name = "create")
public class StartCreateRandomContact {
    @Value("${environment.create}")
    private int amountContact;
    private final ContactService contactService;

    public StartCreateRandomContact(ContactService contactService) {
        this.contactService = contactService;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void randomCreateContact(){
        log.info("Calling StartCreateContact -> random create  {} contacts", amountContact);
        Faker faker =new Faker();
        List<Contact> contactList = new ArrayList<>();
        for (int i = 0; i < amountContact; i++){
            Contact contact = new Contact();
            contact.setId(System.currentTimeMillis());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contact.setFirstName(faker.getName().firstName());
            contact.setLastName(faker.getName().lastName());
            contact.setPhone(RandomPhone.getRandomPhone(8));
            contact.setEmail(faker.getInternet().email(contact.getFirstName()));
            contactList.add(contact);
        }
        contactService.addListContact(contactList);
    }

}
