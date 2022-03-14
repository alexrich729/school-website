package com.chegg.project.mock;

import java.util.ArrayList;
import java.util.List;

import com.chegg.project.*;
import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;
import com.chegg.project.exceptions.runtime.ValidationException;

public class EntityFieldsImpl implements EntityFields {
	protected EntityType type;
	/**
	 * These are the fields that have been set so far into EntityFields
	 */
	protected List<Field> fieldsSet;
	protected Config config;
	
	/** For some children as they are only constructed through factory method */
	protected EntityFieldsImpl() {}

	public EntityFieldsImpl(EntityType type, Config config) {
		this.type = type;
		this.fieldsSet = new ArrayList<>();
		this.config = config;
		validate(this.fieldsSet);
	}

	public EntityFieldsImpl(EntityType type, List<Field> fields, Config config) {
		this.type = type;
		this.fieldsSet = fields;
		this.config = config;
		validate(this.fieldsSet);
	}
	
	/** Copy constructor */
	public EntityFieldsImpl(EntityFieldsImpl efi) {
		this.type = efi.getType();
		this.fieldsSet = efi.getSetFields();
		this.config = efi.getConfig();
		validate(this.fieldsSet);
	}
	
	/**
	 * @param providedFields should only have fields that are valid to the entity type
	 * @throws ValidationException if any of the fields aren't required or optional to the entity type
	 */
	public void validate(List<Field> providedFields) throws ValidationException {
		for (int i = 0; i < providedFields.size(); i++) {
			Field curField = providedFields.get(i);
			if (!containsField(getRequiredFields(), curField) && !containsField(getOptionalFields(), curField)) {
				throw new ValidationException();
			}
		}
	}
	
	/**
	 * Helper function for validate.
	 * @param fields to look for field toFind in
	 * @param toFind is a field we are looking for in list
	 * @return true if field is in list, false otherwise
	 */
	public static boolean containsField(List<Field> fields, Field toFind) {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).isSameField(toFind)) { return true; }
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityType getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getConfiguredFields() {
		return config.getFields(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getSetFields() {
		return fieldsSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getRequiredFields() {
		return config.getRequiredFields(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getOptionalFields() {
		return config.getOptionalFields(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field getField(String fieldName) {
		for (int i = 0; i < fieldsSet.size(); i++) {
			Field curField = fieldsSet.get(i);
			if (curField.getName().equals(fieldName))
				return curField;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setField(String name, Object value) throws FieldNotSupportedException, FieldTypeException {
		// First, check if the field is valid for this entity
		Field configuredField = null;
		for (Field field : getConfiguredFields()) {
			if (field.getName().equals(name))
				configuredField = field;
		}
		if (configuredField == null)
			throw new FieldNotSupportedException
			("No field \"" + name + "\" in entity of type " + getType().toString() + "; configured fields are:" +
			getConfiguredFields().toString());
		
		// Next, get the existing field, if there is one
		Field existingField = null;
		for (Field field : getSetFields()) {
			if (field.getName().equals(name))
				existingField = field;
		}
		
		// Create new field if needed
		Field fieldToUpdate = existingField;
		if (fieldToUpdate == null)
			fieldToUpdate = new Field(configuredField);
		
		// Set the value into the field and add to fieldsSet as needed
		fieldToUpdate.setValue(value);
		if (existingField == null)
			fieldsSet.add(fieldToUpdate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAllFields(List<Field> allFields) throws FieldNotSupportedException, FieldTypeException {
		for (Field field : allFields) {
			setField(field.getName(), field.getValue());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Config getConfig() {
		return config;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CourseFields buildCourseFields() throws ValidationException {
		if (this.type != EntityType.COURSE) {
			throw new ValidationException("Attempt to build CourseFields with EntityFields of wrong type.");
		}
		return new CourseFieldsImpl(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserFields buildUserFields() throws ValidationException {
		if (this.type != EntityType.USER) {
			throw new ValidationException("Attempt to build UserFields with EntityFields of wrong type.");
		}
		return new UserFieldsImpl(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchoolFields buildSchoolFields() throws ValidationException {
		if (this.type != EntityType.SCHOOL) {
			throw new ValidationException("Attempt to build SchoolFields with EntityFields of wrong type.");
		}
		return new SchoolFieldsImpl(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasFieldValues(EntityFieldsImpl efi) {
		if (efi == null)
			return true;
		List<Field> otherFields = efi.getSetFields();
		for (int i = 0; i < otherFields.size(); i++) {
			Field tmpField = otherFields.get(i);
			boolean fieldFound = false;
			for (int j = 0; j < fieldsSet.size(); j++) {
				if (tmpField.isSameFieldAndVal(fieldsSet.get(j)))
					fieldFound = true;
			}
			if (!fieldFound)
				return false;
		}
		return true;
	}

}
