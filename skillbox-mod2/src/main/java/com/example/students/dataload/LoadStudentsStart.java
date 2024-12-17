package com.example.students.dataload;

import com.example.students.StudentsManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty("app.load-students-on-start.enabled")
public class LoadStudentsStart {

    private final StudentsManager studentsManager;

    @Value("${app.load-students-on-start.students-list-filename}")
    private String filename;

    public LoadStudentsStart(StudentsManager studentsManager) {
        this.studentsManager = studentsManager;
    }

    @PostConstruct
    private void loadData() {
        try {
            String jsonString = Files.readString(Path.of(filename));
            ObjectMapper mapper = new ObjectMapper();
            Student[] students = mapper.readValue(jsonString, Student[].class);
            for (Student s : students) {
                studentsManager.add(s.firstname, s.lastname, s.age);
            }
            System.out.println("Загружено " + students.length + " cтудентов.");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки данных из файла");
        }
    }
}
