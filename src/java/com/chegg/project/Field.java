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

}
