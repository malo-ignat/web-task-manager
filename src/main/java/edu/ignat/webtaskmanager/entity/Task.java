package edu.ignat.webtaskmanager.entity;

import edu.ignat.webtaskmanager.util.LocalDateTimeToTimestampConverter;
import edu.ignat.webtaskmanager.util.PriorityToIntegerConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(name = "is_completed")
    private boolean isCompleted = false;

    @Column(name = "created_at")
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Convert(converter = PriorityToIntegerConverter.class)
    private Priority priority = Priority.NORMAL;

    public enum Priority {
        NORMAL, HIGH
    }

}
