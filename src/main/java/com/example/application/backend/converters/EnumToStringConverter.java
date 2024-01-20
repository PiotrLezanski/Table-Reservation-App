package com.example.application.backend.converters;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class EnumToStringConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumType;

    public EnumToStringConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Result<T> convertToModel(String value, ValueContext context) {
        try {
            return Result.ok(Enum.valueOf(enumType, value));
        } catch (IllegalArgumentException e) {
            return Result.error("Invalid value");
        }
    }

    @Override
    public String convertToPresentation(T value, ValueContext context) {
        return value != null ? value.name() : null;
    }
}
