package com.chegg.project.mock;

import com.chegg.project.exceptions.runtime.ValidationException;

public class CourseImpl extends CourseFieldsImpl {
	
	private CourseFieldsImpl courseFields;
	
	/**
	 * @param courseFields should contain all fields needed to represent a course
	 * @throws ValidationException if any required fields are missing
	 */
	public CourseImpl(CourseFieldsImpl courseFields) throws ValidationException {
		super(courseFields);
		EntityImpl.validate(courseFields);
	}
}
