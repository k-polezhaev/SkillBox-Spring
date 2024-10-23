package com.example.skillboxmod1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ContactController implements CommandLineRunner {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить контакт");
            System.out.println("2. Удалить контакт по email");
            System.out.println("3. Показать все контакты");
            System.out.println("4. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Введите ФИО, номер телефона и email через ';'");
                    String input = scanner.nextLine();
                    String[] data = input.split(";");
                    if (data.length == 3) {
                        contactService.addContact(new Contact(data[0], data[1], data[2]));
                        System.out.println("Контакт успешно добавлен.");
                    } else {
                        System.out.println("Неправильный формат ввода.");
                    }
                    break;
                case "2":
                    System.out.println("Введите email контакта для удаления:");
                    String email = scanner.nextLine();
                    if (contactService.removeContactByEmail(email)) {
                        System.out.println("Контакт успешно удален.");
                    } else {
                        System.out.println("Контакт с указанным email не найден.");
                    }
                    break;
                case "3":
                    System.out.println("Список контактов:");
                    contactService.getAllContacts().forEach(System.out::println);
                    break;
                case "4":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}