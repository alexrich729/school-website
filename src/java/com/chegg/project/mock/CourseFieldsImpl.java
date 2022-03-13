package com.chegg.project.mock;

public class CourseFieldsImpl extends EntityFieldsImpl {

	public CourseFieldsImpl(CourseFieldsImpl courseFields) {
		this.setAllFields(courseFields.getAllFields());
	}

}
