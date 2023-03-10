package edu.ignat.webtaskmanager.util;

import jakarta.persistence.AttributeConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeToTimestampConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute != null ? Timestamp.valueOf(attribute) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData != null ? dbData.toLocalDateTime() : null;
    }
}