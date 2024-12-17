package com.example.students.dataload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Student {
    public String firstname;
    public String lastname;
    public int age;
    Student(){}
}
