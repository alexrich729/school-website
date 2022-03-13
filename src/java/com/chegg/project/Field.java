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
     * Checks to see if given field has the same name and type as this field
     * @param field to compare with
     * @return  true if name and type are the same, false otherwise
     */
    boolean isSameField(Field field);

}
