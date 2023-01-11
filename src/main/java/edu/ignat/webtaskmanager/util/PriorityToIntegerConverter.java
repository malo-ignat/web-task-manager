package edu.ignat.webtaskmanager.util;

import edu.ignat.webtaskmanager.entity.Task;
import jakarta.persistence.AttributeConverter;

public class PriorityToIntegerConverter implements AttributeConverter<Task.Priority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Task.Priority priority) {
        return switch (priority) {
            case NORMAL -> 1;
            case HIGH -> 2;
        };
    }

    @Override
    public Task.Priority convertToEntityAttribute(Integer value) {
        return switch (value) {
            case 1 -> Task.Priority.NORMAL;
            case 2 -> Task.Priority.HIGH;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}