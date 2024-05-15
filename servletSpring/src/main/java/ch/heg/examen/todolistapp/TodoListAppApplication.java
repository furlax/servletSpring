package ch.heg.examen.todolistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TodoListAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListAppApplication.class, args);
    }

}
