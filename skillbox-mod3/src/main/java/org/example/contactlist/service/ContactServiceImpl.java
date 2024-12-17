package org.example.contactlist.service;

import org.example.contactlist.entity.Contact;
import org.example.contactlist.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public List<Contact> selectAll(){
        return new ArrayList<>(contactRepository.selectAll());
    }

    @Override
    public Contact selectContact(long id) {
        return contactRepository.selectById(id).orElse(null);
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(System.currentTimeMillis());
        contactRepository.insert(contact);
    }
    @Override
    public void deleteContact(long id) {
        contactRepository.removeById(id);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepository.update(contact);
    }

    @Override
    public void addListContact(List<Contact> contactList) {
        contactRepository.batchInsert(contactList);
    }


}
