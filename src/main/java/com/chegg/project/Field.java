/**
 * 
 */
package com.chegg.project;

/**
 * This type represents a configurable field in one of the objects we're modeling.
 * @author alexrich729
 *
 */
public interface Field {

    /**
     * @return Gets the name
     */
    String getName();

    /**
     * @return Gets the value
     */
    Object getValue();

    /**
     * @return Gets the type
     */
    FieldType getType();

    /**
     * @param field to compare with
     * @return  true if name and type are the same, false otherwise
     */
    boolean isSameField(Field field);
    
    /**
     * @param field to compare with
     * @return true if name, type, and value are the same, false otherwise
     */
    boolean isSameFieldAndVal(Field field);

	/**
	 * @return true iff this field is required to be set in the entity where it is used
	 */
	Boolean isRequired();

	/**
	 * @return true iff this field is multi-valued
	 */
	Boolean isMulti();

}
