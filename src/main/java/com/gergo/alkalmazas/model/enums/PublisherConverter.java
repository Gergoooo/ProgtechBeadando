package com.gergo.alkalmazas.model.enums;

import javax.persistence.AttributeConverter;

public class PublisherConverter implements AttributeConverter<Publisher, String> {

    @Override
    public String convertToDatabaseColumn(Publisher value) {
        return value != null ? value.name() : null;
    }

    @Override
    public Publisher convertToEntityAttribute(String name) {
        return name != null ? Publisher.valueOf(name) : null;
    }
}
