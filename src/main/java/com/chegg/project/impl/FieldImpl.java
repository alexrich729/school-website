package com.chegg.project.impl;

import com.chegg.project.Field;
import com.chegg.project.FieldType;

/**
 * Implements Field in a real way.
 * @author alexrich
 */
public class FieldImpl implements Field {
    private String name;
    private Object value;
    private FieldType type;

    public FieldImpl(String name, Object value, FieldType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSameField(Field field) {
        return field.getName().equals(name) && field.getType() == type;
    }
}
