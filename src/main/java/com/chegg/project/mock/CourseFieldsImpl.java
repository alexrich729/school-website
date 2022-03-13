package com.chegg.project.mock;

import com.chegg.project.EntityType;

public class CourseFieldsImpl extends EntityFieldsImpl {

	public CourseFieldsImpl(CourseFieldsImpl courseFields) {
		super(EntityType.COURSE);
		this.setAllFields(courseFields.getAllFields());
	}

}
