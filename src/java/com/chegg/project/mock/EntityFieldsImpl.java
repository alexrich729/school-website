package com.chegg.project.mock;

import java.util.List;

import com.chegg.project.EntityFields;
import com.chegg.project.EntityType;
import com.chegg.project.Field;
import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;

public class EntityFieldsImpl implements EntityFields {

	@Override
	public EntityType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> getAllFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field getField(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setField(Field newField) throws FieldNotSupportedException, FieldTypeException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAllFields(Iterable<Field> allFields) throws FieldNotSupportedException, FieldTypeException {
		// TODO Auto-generated method stub

	}

}
