package org.example.contactlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.contactlist.entity.Contact;
import org.example.contactlist.service.ContactServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ContactController {
    private final ContactServiceImpl contactService;

    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Contact> contactList = contactService.selectAll();
        model.addAttribute("contacts", contactList);
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateContactForm(Model model) {
        model.addAttribute("action","create");
        model.addAttribute("contact", new Contact());
        return "form_contact";
    }

    @PostMapping(value = "/contact/action", params = "create")
    public String createContact(@ModelAttribute Contact contact) {
        contactService.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditContactForm(@PathVariable long id, Model model) {
        Contact contact = contactService.selectContact(id);
        if (contact != null) {
            model.addAttribute("action","edit");
            model.addAttribute("contact", contact);
            return "form_contact";
        }
        return "redirect:/";
    }
    @PostMapping(value = "/contact/action", params = "edit")
    public String editContact(@ModelAttribute Contact contact){
        contactService.updateContact(contact);
        return "redirect:/";
    };

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable long id) {
        contactService.deleteContact(id);
        return "redirect:/";
    }

}
