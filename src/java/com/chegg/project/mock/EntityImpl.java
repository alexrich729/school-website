package com.chegg.project.mock;

import com.chegg.project.exceptions.runtime.ValidationException;

/**
 * Extends EntityFieldsImpl and makes sure that all required fields are present.
 * @author alexrich729
 *
 */
public class EntityImpl extends EntityFieldsImpl {
	/**
	 * @param courseFields should have a complete set of fields for entity type
	 * @throws ValidationException if any required fields are missing
	 */
	public static void validate(CourseFieldsImpl courseFields) throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
