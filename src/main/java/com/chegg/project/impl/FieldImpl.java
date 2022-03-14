package com.chegg.project.impl;

import java.util.List;

import com.chegg.project.Course;
import com.chegg.project.Field;
import com.chegg.project.FieldType;
import com.chegg.project.School;
import com.chegg.project.User;

/**
 * Implements Field in a real way.
 * @author alexrich
 */
public class FieldImpl implements Field {
    private String name;
    private Object value;
    private FieldType type;
    private Boolean required;
    private Boolean multi;

    /**
     * Field cannot be created in an inconsistent way, so we check that the value (if provided) matches the declared
     *  type.
     * @param name : the name of the field; each entity can have only one field with the given name
     * @param value : the value of the field; type will be appropriate for the field type
     * @param type : type of the field from {@FieldTypes}
     * @param required : true iff the field is required for an entity to be fully defined
     * @param multi : true iff it is a multi-value field, i.e. a list of the type
     * @throws IllegalArgument exception if the supplied value is not a match for the given field type
     */
    public FieldImpl(String name, Object value, FieldType type, Boolean required, Boolean multi)
    throws IllegalArgumentException
    {
        this.name = name;
        this.value = value;
        this.type = type;
        this.required = required;
        this.multi = multi;
        
        // Default required and multi to false
        if (required == null)
        	required = false;
        if (multi == null)
        	multi = false;
        
        if (value != null) {
        	// check for consistency
        	
        	Object oneVal = value; // we'll set this to the first item in a list, if the list is provided
        	
	        if (multi) {
	        	if (! (value instanceof List)) {
	        		throw new IllegalArgumentException
	        		("Multi-value field requires value to be a list, but is " + value.getClass().toString());
	        	}
	        	if (((List<?>)value).isEmpty()) {
	        		oneVal = null;
	        	} else {
	        		oneVal = ((List<?>)value).get(0);
	        	}
	        }
	        
	        if (oneVal != null) {
	        	// check value type
	        	switch (type) {
	        	case STRING:
	        		if (! (oneVal instanceof String))
	        			throw new IllegalArgumentException
	        			("Type is STRING but value is " + oneVal.getClass().toString());
	        	break;
	        	case USER:
	        		if (! (oneVal instanceof User))
	        			throw new IllegalArgumentException
	        			("Type is USER but value is " + oneVal.getClass().toString());
	        	break;
	        	case SCHOOL:
	        		if (! (oneVal instanceof School))
	        			throw new IllegalArgumentException
	        			("Type is SCHOOL but value is " + oneVal.getClass().toString());
	        	break;
	        	case COURSE:
	        		if (! (oneVal instanceof Course))
	        			throw new IllegalArgumentException
	        			("Type is COURSE but value is " + oneVal.getClass().toString());
	        	break;
	        	default:
	        		throw new UnsupportedOperationException("Currently not handling field type " + type.getName());
	        	}
	        }
        }
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
    public Boolean isRequired() {
		return required;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Boolean isMulti() {
		return multi;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean isSameField(Field field) {
        return field.getName().equals(name) && field.getType() == type;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isSameFieldAndVal(Field field) {
		return isSameField(field) && field.getValue().equals(value);
	}
}
