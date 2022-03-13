package com.chegg.project.mock;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;

import com.chegg.project.*;
import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;

public class EntityFieldsImpl implements EntityFields {
	protected EntityType type;
	protected List<Field> fields;
	protected Config config;

	public EntityFieldsImpl(EntityType type, Config config) {
		this.type = type;
		this.fields = new ArrayList<>();
		this.config = config;
	}

	public EntityFieldsImpl(EntityType type, List<Field> fields) {
		this.type = type;
		this.fields = fields;
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
		return Config.getRequiredFields(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getOptionalFields() {
		return Config.getOptionalFields(type);
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
		// overrides same field as newField if it already exists
		Field tmpField;
		for (int i = 0; i < fields.size(); i++) {
			tmpField = fields.get(i);
			if (tmpField.isSameField(newField)) {
				fields.remove(i);
				fields.add(newField);
				return;
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
				throw new FieldTypeException();
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
				throw new FieldTypeException();
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

}
