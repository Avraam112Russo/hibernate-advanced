package org.example.mappingEntityAssociations.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.mappingEntityAssociations.entity.Birthday;


import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthdayConverter implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday birthday) {
        return Optional.of(birthday)
                .map(b->birthday.getBirthDate())
                .map(localDate -> Date.valueOf(localDate))
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(d -> d.toLocalDate())
                .map(localDate -> new Birthday(localDate))
                .orElse(null);
    }
}
