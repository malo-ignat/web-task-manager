package edu.ignat.webtaskmanager.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;
    private String title;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private Priority priority;

    public enum Priority {
        NORMAL, HIGH
    }

}
