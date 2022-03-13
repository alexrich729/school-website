package com.chegg.project.mock;

import com.chegg.project.EntityType;
import com.chegg.project.Field;
import com.chegg.project.exceptions.runtime.ValidationException;

import java.util.List;

/**
 * Extends EntityFieldsImpl and makes sure that all required fields are present.
 * @author alexrich729
 *
 */
public class EntityImpl extends EntityFieldsImpl {
	public EntityImpl(EntityType type, List<Field> fields) {
		super(type, fields);
	}

	/**
	 * @param fields should have a complete set of fields for entity type
	 * @throws ValidationException if any required fields are missing
	 */
	public static void validate(EntityFieldsImpl fields) throws ValidationException {
		List<Field> allFields = fields.getAllFields();
		// checks if any of the fields have the same name in which case it throws an error
		for (int i = 0; i < allFields.size(); i++) {
			for (int j = i + 1; j < allFields.size(); j++) {
				if (allFields.get(i).getName().equals(allFields.get(j).getName())) {
					throw new ValidationException("Attempt to create an object that has multiple fields with the same name.");
				}
			}
		}

		List<Field> requiredFields = fields.getRequiredFields();
		int reqCount = 0;
		for (int i = 0; i < allFields.size(); i++) {
			Field curField = allFields.get(i);
			if (containsField(requiredFields, curField))  {
				reqCount++;
			} else if (!containsField(fields.getOptionalFields(), curField)) {
				// exception when field isn't required or optional
				throw new ValidationException();
			}
		}
		// checks to see if all required fields were found
		if (reqCount < requiredFields.size()) {
			throw new ValidationException();
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
}
