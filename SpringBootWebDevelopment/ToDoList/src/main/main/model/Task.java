package main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime creationTime = LocalDateTime.now();
    private boolean isDone;
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationTime = LocalDateTime.now();
        this.isDone = false;
    }

    public Task() {
        this.title = title;
        this.description = description;
//        this.creationTime = LocalDateTime.now();
        this.isDone = false;
    }
}
