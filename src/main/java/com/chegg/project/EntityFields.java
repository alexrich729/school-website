package com.chegg.project;

import java.util.List;

import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;
import com.chegg.project.exceptions.runtime.ValidationException;

/**
 * 
 * Provides the reusable interface for any of our configurable and extensible entity types, school, user, course, etc.,
 *   where only some of the fields may have been provided, e.g. for matching.
 * 
 * @author alexrich729
 *
 */

public interface EntityFields {
	/**
	 * @return the type of entity represented
	 */
	EntityType getType();
	/**
	 * @return all the fields that have been set into the EntityFields
	 */
	List<Field> getSetFields();
	
	/**
	 * @return all the fields that have been configured for the entity type
	 */
	List<Field> getConfiguredFields();

	/**
	 * @return all required fields in the entity of its type
	 */
	List<Field> getRequiredFields();

	/**
	 * @return all fields that aren't required but are allowed in the entity of its type
	 */
	List<Field> getOptionalFields();
	/**
	 * @param fieldName is the field we want to get
	 * @return the field specified, or null if no such field exists in the entity
	 */
	Field getField(String fieldName);
	/**
	 * @return config being used
	 */
	Config getConfig();
	/**
	 * Sets the new field into the entity. If field with same name and type exists that field is overridden.
	 * @param newField
	 * @throws FieldNotSupportedException if the Entity is not configured with the given field
	 * @throws FieldTypeException if the Entity is configured with the field name, but the value is not compatible
	 */
	void setField(String name, Object value) throws FieldNotSupportedException, FieldTypeException;
	/**
	 * Sets all the given fields into the entity
	 * @param allFields
	 * @throws FieldNotSupportedException if the Entity is not configured with a given field
	 * @throws FieldTypeException if the Entity is configured with the field name, but a given field is configured with a different type
	 */
	void setAllFields(List<Field> allFields) throws FieldNotSupportedException, FieldTypeException;
	/**
	 * @return EntityFields casted specifically to CourseFields
	 * @throws ValidationException if type of EntityFields is not COURSE
	 */
	CourseFields buildCourseFields() throws ValidationException;
	/**
	 * @return EntityFields casted specifically to UserFields
	 * @throws ValidationException if type of EntityFields is not USER
	 */
	UserFields buildUserFields() throws ValidationException;
	/**
	 * @return EntityFields casted specifically to SchoolFields
	 * @throws ValidationException if type of EntityFields is not SCHOOL
	 */
	SchoolFields buildSchoolFields() throws ValidationException;
}
