package com.chegg.project.mock;

import com.chegg.project.Config;
import com.chegg.project.Course;
import com.chegg.project.Entity;
import com.chegg.project.EntityType;
import com.chegg.project.Field;
import com.chegg.project.School;
import com.chegg.project.User;
import com.chegg.project.exceptions.runtime.ValidationException;

import java.util.List;

/**
 * Extends EntityFieldsImpl and makes sure that all required fields are present.
 * @author alexrich729
 *
 */
public class EntityImpl extends EntityFieldsImpl implements Entity {
	/** For children as they are only constructed through factory method */
	protected EntityImpl() {}
	
	public EntityImpl(EntityType type, List<Field> fields, Config config) {
		super(type, fields, config);
		validate(this.fieldsSet);
	}
	
	public EntityImpl(EntityFieldsImpl efi) {
		super(efi);
		validate(this.fieldsSet);
	}

	/**
	 * @param allFields should have a complete set of fields for entity type
	 * @throws ValidationException if any required fields are missing
	 */
	public void validate(List<Field> allFields) throws ValidationException {
		List<Field> requiredFields = getRequiredFields();
		for (int i = 0; i < requiredFields.size(); i++) {
			Field curField = requiredFields.get(i);
			if (!containsField(allFields, curField)) {
				throw new ValidationException("Missing required field with name " + curField.getName() + " and type " + curField.getType() + " in a " + this.type + ".");
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Course buildCourse() throws ValidationException {
		if (this.type != EntityType.COURSE) {
			throw new ValidationException("Attempt to build Course with Entity of wrong type.");
		}
		return (CourseImpl)this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User buildUser() throws ValidationException {
		if (this.type != EntityType.USER) {
			throw new ValidationException("Attempt to build User with Entity of wrong type.");
		}
		return (UserImpl)this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public School buildSchool() throws ValidationException {
		if (this.type != EntityType.SCHOOL) {
			throw new ValidationException("Attempt to build School with Entity of wrong type.");
		}
		return (SchoolImpl)this;
	}
}
