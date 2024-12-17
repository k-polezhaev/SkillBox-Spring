package com.example.students;

import com.example.students.events.StudentAddEvent;
import com.example.students.events.StudentDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class StudentsShell {

    private final StudentsManager studentsManager;

    public StudentsShell(StudentsManager studentsManager) {
        this.studentsManager = studentsManager;
    }

    @ShellMethod
    public void print() {
        studentsManager.print();
    }

    @ShellMethod
    public void add(@ShellOption(value = "fn") String firstName, @ShellOption(value = "ln") String lastName, @ShellOption(value = "a") int age) {
        studentsManager.add(firstName, lastName, age);
    }

    @ShellMethod
    public void delete(Integer id){
        studentsManager.delete(id);
    }

    @ShellMethod
    public void flush(){
        studentsManager.flush();
    }

    @EventListener
    public void handleStudentAddEvent(StudentAddEvent event){
        System.out.println("Cтудент добавлен: " + event.getStudent());
    }

    @EventListener
    public void handleStudentDeleteEvent(StudentDeleteEvent event){
        System.out.println("Cтудент удален: " + event.getId());
    }


}
