package com.example.students;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(Integer id, String firstName, String lastName, int age) {
    }

    @Override
    public String toString() {
        return "Студент " +id + ": " + firstName + " " + lastName + " - "+ age + " лет.";
    }
}
