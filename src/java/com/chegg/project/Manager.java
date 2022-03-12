/**
 * 
 */
package com.chegg.project;

import java.util.List;

import com.chegg.project.exceptions.TooManyMatchesException;

/**
 * This declares the logic that we'll support for managing all the entities.
 * @author alexrich729
 *
 * Implementation may throw undeclared types of runtime exceptions, e.g. if the database is not available.
 *
 */
public interface Manager {
	// CORE APIS
	
	/** Adds a user, which may be a student or professor */
	void addUser(User user);
	
	/** 
	 *  Updates a user in two steps:  1) find the user(s) to update, 2) update them
	 *  @param selector - User fields that are to be matched to find the users to update
	 *  @param newData - the new field values to assign to matching users
	 *  @param moreThanOne - allows updating more than one user record
	 *  @return UpdateReport indicating the number of users selected and the number updated
	 *  @throws TooManyMatchesException if the update would affect more than one record, but moreThanOne is FALSE
	 */
	UpdateReport updateUser(UserFields selector, UserFields newData, Boolean moreThanOne)
			throws TooManyMatchesException;
	/**
	 * Get a list of users matching the given data.
	 * @param match - the fields that should be matched to generate a list of results
	 * @return a list of results
	 */
	List<User> listUsers(UserFields match);
	/**
	 * Delete user.
	 * @param match
	 * @return UpdateReport indicating the number of matching records, and the number deleted:
	 * 	    1: if one record deleted
	 *      0: if no records matched or deleted
	 *      >1: if multiple records matched and deleted
	 *      -1: if multiple records matched, but none deleted b/c moreThanOne==FALSE
	 */
	UpdateReport deleteUser(UserFields match, Boolean moreThanOne);
	
	// ADDITIONAL APIS
	
	/**
	 * Adds the given course
	 */
	void addCourse(Course course);
	
	/**
	 * Updates a course in two steps: 1) find the course(s) to update, 2) update them
	 * @param selector - course fields that are to be matched to find the courses to update
	 * @param newData - the new field values to assign to matching courses
	 * @param moreThanOne - allows updating more than one course record
	 * @return UpdateReport indicating the number of users selected and the number updated
	 * @throws TooManyMatchesException if the update would affect more than one record, but moreThanOne is FALSE
	 */
	Boolean updateCourse(CourseFields selector, CourseFields newData, Boolean moreThanOne)
		throws TooManyMatchesException;
	
	/**
	 * Get a list of courses matching the given data.
	 * @param match - the fields that should be matched to generate a list of results
	 * @return a list of results
	 */
	List<Course> listCourses(CourseFields match);
	/**
	 * Delete course.
	 * @param match
	 * @return UpdateReport indicating the number of matching records, and the number deleted:
	 * 	    1: if one record deleted
	 *      0: if no records matched or deleted
	 *      >1: if multiple records matched and deleted
	 *      -1: if multiple records matched, but none deleted b/c moreThanOne==FALSE
	 * 
	 */
	UpdateReport deleteCourse(CourseFields match, Boolean moreThanOne);

	/**
	 * Adds the given school
	 */
	void addSchool(School school);

	/**
	 * Get a list of courses matching the given data.
	 * @param school - the name of school to matched to
	 * @return a list of results
	 */
	List<Course> listSchools(String school);
	/**
	 * Delete course.
	 * @param school
	 * @return UpdateReport indicating the number of matching records, and the number deleted:
	 * 	    1: if one record deleted
	 *      0: if no records matched or deleted
	 *      >1: if multiple records matched and deleted
	 *      -1: if multiple records matched, but none deleted b/c moreThanOne==FALSE
	 *
	 */
	UpdateReport deleteSchool(String school, Boolean moreThanOne);
	
	/**
	 * Gets a single suggested completion which will be something in our database that matches the given prefix, e.g.
	 *   prefix is "Al", then we could return "Alex", even if there are other possible matches.  We just pick the next alphabetical.
	 * @param prefix - the part of the string that has to match
	 * @param recordType - the type of record we're looking for, a student, professor, any user, or a course
	 * @return a single match, or null if there is no match
	 * @throws IllegalArgumentException if the recordType is not one of User or Course
	 */
	String suggest(String prefix, RecordType recordType);
	
}
