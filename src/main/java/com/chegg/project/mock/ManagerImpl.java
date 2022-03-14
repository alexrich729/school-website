package com.chegg.project.mock;

import java.util.ArrayList;
import java.util.List;

import com.chegg.project.Course;
import com.chegg.project.CourseFields;
import com.chegg.project.EntityFields;
import com.chegg.project.EntityType;
import com.chegg.project.Manager;
import com.chegg.project.School;
import com.chegg.project.SchoolFields;
import com.chegg.project.User;
import com.chegg.project.UserFields;
import com.chegg.project.Entity;
import com.chegg.project.exceptions.TooManyMatchesException;

public class ManagerImpl implements Manager {
	private List<User> users;
	private List<Course> courses;
	private List<School> schools;
	
	public ManagerImpl() {
		users = new ArrayList<>();
		courses = new ArrayList<>();
		schools = new ArrayList<>();
	}

	/**
	 * Update matching entities
	 */
	private int updateEntity(EntityFields selector, EntityFields newData, boolean moreThanOne, List<? extends Entity> entities) 
			throws TooManyMatchesException {
		// Get all matching entities, throw exception if too many matched
		List<Entity> entitiesMatched = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.hasFieldValues((EntityFieldsImpl)selector)) {
				entitiesMatched.add(entity);
				if (!moreThanOne && entitiesMatched.size() > 1)
					throw new TooManyMatchesException();
			}
		}
		
		// update all matched entities
		for (Entity entity : entitiesMatched) {
			entity.setAllFields(newData.getSetFields());
		}
		return entitiesMatched.size();
	}

	/**
	 * List matching entities
	 */
	private List<Entity> listEntities(EntityFields match, List<? extends Entity> entities) {
		List<Entity> entitiesMatched = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.hasFieldValues((EntityFieldsImpl)match)) {
				entitiesMatched.add(entity);
			}
		}
		return entitiesMatched;
	}

	/**
	 * Delete matching entities
	 */
	private int deleteEntity(EntityFields match, boolean moreThanOne, List<? extends Entity> entities) {
		List<Entity> entitiesMatched = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.hasFieldValues((EntityFieldsImpl)match)) {
				entitiesMatched.add(entity);
				if (!moreThanOne && entitiesMatched.size() > 1)
					return -1;
			}
		}
		
		// removes all matched entities
		int toRemove = 0;
		int index = 0;
		while (index < entities.size()) {
			Entity tmpEntity = entities.get(index);
			if (tmpEntity == entitiesMatched.get(toRemove)) {
				entities.remove(index);
				toRemove++;
			} else {
				index++;
			}
		}
		return entitiesMatched.size();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUser(User user) {
		users.add(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateUser(UserFields selector, UserFields newData, boolean moreThanOne) 
			throws TooManyMatchesException {
		return updateEntity(selector, newData, moreThanOne, users);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> listUsers(UserFields match) {
		List<Entity> entities = listEntities(match, users);
		List<User> results = new ArrayList<>();
		for (Entity entity : entities) {
			results.add(entity.buildUser());
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteUser(UserFields match, boolean moreThanOne) {
		return deleteEntity(match, moreThanOne, users);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCourse(Course course) {
		courses.add(course);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateCourse(CourseFields selector, CourseFields newData, boolean moreThanOne)
			throws TooManyMatchesException {
		return updateEntity(selector, newData, moreThanOne, courses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Course> listCourses(CourseFields match) {
		List<Entity> entities = listEntities(match, courses);
		List<Course> results = new ArrayList<>();
		for (Entity entity : entities) {
			results.add(entity.buildCourse());
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteCourse(CourseFields match, boolean moreThanOne) {
		return deleteEntity(match, moreThanOne, courses);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSchool(School school) {
		schools.add(school);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateCourse(SchoolFields selector, SchoolFields newData, boolean moreThanOne)
			throws TooManyMatchesException {
		return updateEntity(selector, newData, moreThanOne, schools);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<School> listSchools(SchoolFields match) {
		List<Entity> entities = listEntities(match, schools);
		List<School> results = new ArrayList<>();
		for (Entity entity : entities) {
			results.add(entity.buildSchool());
		}
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteSchool(SchoolFields match, boolean moreThanOne) {
		return deleteEntity(match, moreThanOne, schools);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String suggest(String prefix, EntityType entityType) {
		throw new UnsupportedOperationException();
	}


}
