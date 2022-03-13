package com.chegg.project.mock;

import java.util.ArrayList;
import java.util.List;

import com.chegg.project.*;
import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;
import com.chegg.project.exceptions.runtime.ValidationException;

public class EntityFieldsImpl implements EntityFields {
	protected EntityType type;
	protected List<Field> fields;
	protected Config config;
	
	/** For some children as they are only constructed through factory method */
	protected EntityFieldsImpl() {}

	public EntityFieldsImpl(EntityType type, Config config) {
		this.type = type;
		this.fields = new ArrayList<>();
		this.config = config;
		validate(this.fields);
	}

	public EntityFieldsImpl(EntityType type, List<Field> fields, Config config) {
		this.type = type;
		this.fields = fields;
		this.config = config;
		validate(this.fields);
	}
	
	/** Copy constructor */
	public EntityFieldsImpl(EntityFieldsImpl efi) {
		this.type = efi.getType();
		this.fields = efi.getAllFields();
		this.config = efi.getConfig();
		validate(this.fields);
	}
	
	/**
	 * @param allFields should only have fields that are valid to the entity type
	 * @throws ValidationException if any of the fields aren't required or optional to the entity type
	 */
	public void validate(List<Field> allFields) throws ValidationException {
		for (int i = 0; i < allFields.size(); i++) {
			Field curField = allFields.get(i);
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
	public List<Field> getAllFields() {
		return fields;
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
		for (int i = 0; i < fields.size(); i++) {
			Field curField = fields.get(i);
			if (curField.getName().equals(fieldName))
				return curField;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setField(Field newField) throws FieldNotSupportedException, FieldTypeException {
		// overrides same field as newField if it already exists or throws exception if they have the same name but different types
		Field tmpField;
		for (int i = 0; i < fields.size(); i++) {
			tmpField = fields.get(i);
			if (tmpField.isSameField(newField)) {
				fields.remove(i);
				fields.add(newField);
				return;
			}
			if (tmpField.getName().equals(newField.getName())) {
				throw new FieldTypeException("Given field has same name as an existing field but different type.");
			}
			
		}

		// checks to see if field is required field or has same name as required field with the wrong type
		List<Field> requiredFields = getRequiredFields();
		for (int i = 0; i < requiredFields.size(); i++) {
			tmpField = requiredFields.get(i);
			if (tmpField.isSameField(newField)) {
				fields.add(newField);
				return;
			}
			if (tmpField.getName().equals(newField.getName())) {
				throw new FieldTypeException("Given field has same name as a required field but different type.");
			}
		}

		// checks to see if field is optional field or has same name as optional field with the wrong type
		List<Field> optionalFields = getOptionalFields();
		for (int i = 0; i < optionalFields.size(); i++) {
			tmpField = optionalFields.get(i);
			if (tmpField.isSameField(newField)) {
				fields.add(newField);
				return;
			}
			if (tmpField.getName().equals(newField.getName())) {
				throw new FieldTypeException("Given field has same name as an optional field but different type.");
			}
		}

		// field hasn't been found to be available in this type of entity
		throw new FieldNotSupportedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAllFields(List<Field> allFields) throws FieldNotSupportedException, FieldTypeException {
		for (int i = 0; i < allFields.size(); i++) {
			setField(allFields.get(i));
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
	 * @return CourseField version of this.
	 * @throws ValidationException if this isn't of type course.
	 */
	public CourseFields buildCourseFields() throws ValidationException {
		if (this.type != EntityType.COURSE) {
			throw new ValidationException("Attempt to build CourseFields with EntityFields of wrong type.");
		}
		return (CourseFieldsImpl)this;
	}
	
	/**
	 * @return UserField version of this.
	 * @throws ValidationException if this isn't of type user.
	 */
	public UserFields buildUserFields() throws ValidationException {
		if (this.type != EntityType.USER) {
			throw new ValidationException("Attempt to build UserFields with EntityFields of wrong type.");
		}
		return (UserFieldsImpl)this;
	}
	
	/**
	 * @return SchoolField version of this.
	 * @throws ValidationException if this isn't of type school.
	 */
	public SchoolFields buildSchoolFields() throws ValidationException {
		if (this.type != EntityType.SCHOOL) {
			throw new ValidationException("Attempt to build SchoolFields with EntityFields of wrong type.");
		}
		return (SchoolFieldsImpl)this;
	}

}
