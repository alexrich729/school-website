/**
 * 
 */
package com.chegg.project;

import java.util.List;

/**
 * This type represents a configurable field in one of the objects we're modeling.
 * @author alexrich729
 *
 */
public class Field {
    private String name;
    private Object value;
    private FieldType type;
    private Boolean required;
    private Boolean multi;
    
    /**
     * Checks that the value is appropriate to the field (matching type)
     * @param newValue is the value we are considering 
     * @throws IllegalArgumentException if the value type is not compatible with the field type
     */
    private void checkValue(Object newValue) throws IllegalArgumentException {
    	// null is always ok unless the field is required and used in a completed entity, but we'll validate
    	//  only when it matters for maximum flexibility
    	if (newValue == null) return;
    	
    	Object oneVal = value; // we'll set this to the first item in a list, if a list is provided

    	if (multi) {
    		if (! (newValue instanceof List)) {
    			throw new IllegalArgumentException
    			("Multi-value field requires value to be a list, but is " + newValue.getClass().toString());
    		}
    		if (((List<?>)value).isEmpty()) {
    			oneVal = null;
    		} else {
    			oneVal = ((List<?>)value).get(0);
    		}
    	}

    	// we cannot easily check an empty list, but we can check later if the list is ever populated and used
    	if (oneVal != null) {
    		// check value type
    		switch (type) {
    		case BOOLEAN:
    			if (! (oneVal instanceof Boolean))
    				throw new IllegalArgumentException
    				("Type is BOOLEAN but value is " + oneVal.getClass().toString());
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
    public Field(String name, Object value, FieldType type, Boolean required, Boolean multi)
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
        
        System.out.println("value of multi: " + multi + " for field " + name);
        
        checkValue(value);
    }

    /**
     * Creates a deep copy
     * @param sourceField is the field to copy
     */
    public Field(Field sourceField) {
		this.name = sourceField.name;
		this.type = sourceField.type;
		this.value = sourceField.value;
		this.required = sourceField.required;
		this.multi = sourceField.multi;
	}

	/**
     * @return Gets the name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * @return Gets the value currently in the field, or null if none
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return Gets the type
     */
    public FieldType getType() {
        return type;
    }

    /**
     * @param field to compare with
     * @return  true if name and type are the same, false otherwise
     */
    public boolean isSameField(Field field) {
        return field.getName().equals(name) && field.getType() == type;
    }
    
    /**
     * @param field to compare with
     * @return true if name, type, and value are the same, false otherwise
     */
	public boolean isSameFieldAndVal(Field field) {
		return isSameField(field) && field.getValue().equals(value);
	}

	/**
	 * @return true iff this field is required to be set in the entity where it is used
	 */
	public Boolean isRequired() {
		return required;
	}

	/**
	 * @return true iff this field is multi-valued
	 */
	public Boolean isMulti() {
		return multi;
	}

	/**
	 * Sets a new value into the field - ensures it is of an appropriate type
	 *
	 * @param value
	 * throws FieldTypeException if the value does not match field type
	 */
	public void setValue(Object value) {
		assert type != null;
		
		checkValue(value);
		
		this.value = value;
	}

}
