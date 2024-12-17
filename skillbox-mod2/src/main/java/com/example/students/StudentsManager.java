package com.example.students;

import com.example.students.events.StudentAddEvent;
import com.example.students.events.StudentDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StudentsManager {
    private final ApplicationEventPublisher eventPublisher;
    private final Map<Integer, Student> students = new HashMap<>();

    public StudentsManager(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void print(){
        for (Student student: students.values()){
            System.out.println(student);
        }
    }

    public int add(String firstName, String lastName, int age){
        Integer id = getNextId();
        Student student= new Student(id, firstName, lastName, age);
        eventPublisher.publishEvent(new StudentAddEvent(student));
        students.put(id, student);
        return id;
    }

    public void delete(Integer id){
        if(students.containsKey(id)){
            eventPublisher.publishEvent(new StudentDeleteEvent(id));
            students.remove(id);
        }
    }

    public void flush(){
        students.clear();
    }
    private Integer getNextId() {
        if (students.isEmpty()) return 0;
        Integer nextId = students.keySet().stream().max(Integer::compareTo).get();
        return nextId + 1;
    }
}
