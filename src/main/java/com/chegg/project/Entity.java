package com.chegg.project;

import com.chegg.project.exceptions.runtime.ValidationException;

/**
 * 
 * Provides the reusable interface for any of our configurable and extensible entity types, school, user, course, etc.
 * Objects implementing this interface are expected to do validation after setting all fields to make sure all required
 * fields are set.
 * 
 * @author alexrich729
 *
 */

public interface Entity extends EntityFields {
	/**
	 * @return Course version of this.
	 * @throws ValidationException if this isn't of type course.
	 */
	public Course buildCourse() throws ValidationException;
	
	/**
	 * @return User version of this.
	 * @throws ValidationException if this isn't of type user.
	 */
	public User buildUser() throws ValidationException ;
	
	/**
	 * @return School version of this.
	 * @throws ValidationException if this isn't of type school.
	 */
	public School buildSchool() throws ValidationException;
}
