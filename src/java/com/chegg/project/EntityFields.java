package com.chegg.project;

import java.util.List;

import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;

/**
 * 
 * Provides the reusable interface for any of our configurable and extensible entity types, school, user, course, etc.,
 *   where only some of the fields may have been provided, e.g. for matching.
 * 
 * @author alexrich729
 *
 */

interface EntityFields {
	/**
	 * @return all the fields in the entity, in the configured order
	 */
	List<Field> getAllFields();
	/**
	 * @param fieldName is the field we want to get
	 * @return the field specified, or null if no such field exists in the entity
	 */
	Field getField(String fieldName);
	/**
	 * Sets the new field into the entity
	 * @param newField
	 * @throws FieldNotSupportedException if the Entity is not configured with the given field
	 * @throws FieldMismatchException if the Entity is configured with the field name, but the field is configured with a different type
	 */
	void setField(Field newField) throws FieldNotSupportedException, FieldTypeException;
	/**
	 * Sets all the given fields into the entity
	 * @param allFields
	 * @throws FieldNotSupportedException if the Entity is not configured with a given field
	 * @throws FieldTypeException if the Entity is configured with the field name, but a given field is configured with a different type
	 */
	void setAllFields(Iterable<Field> allFields) throws FieldNotSupportedException, FieldTypeException;
}
