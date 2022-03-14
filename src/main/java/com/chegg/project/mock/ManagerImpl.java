package com.chegg.project.mock;

import java.util.ArrayList;
import java.util.List;

import com.chegg.project.Course;
import com.chegg.project.CourseFields;
import com.chegg.project.EntityFields;
import com.chegg.project.EntityType;
import com.chegg.project.Manager;
import com.chegg.project.School;
import com.chegg.project.User;
import com.chegg.project.Entity;
import com.chegg.project.exceptions.TooManyMatchesException;

public class ManagerImpl implements Manager {
	private List<User> users;
	private List<Course> courses;
	
	public ManagerImpl() {
		users = new ArrayList<>();
		courses = new ArrayList<>();
	}

	/**
	 * Add an entity
	 */
	private void addEntity(Entity entity, List<Entity> entities) {
		entities.add(entity);
	}

	/**
	 * Update matching entities
	 */
	private int updateEntity(EntityFields selector, EntityFields newData, boolean moreThanOne, List<Entity> entities) 
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
			entity.setAllFields(newData.getAllFields());
		}
		return entitiesMatched.size();
	}

	/**
	 * List matching entities
	 */
	public List<Entity> listEntities(EntityFields match, List<Entity> entities) {
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
	public int deleteEntity(EntityFields match, Boolean moreThanOne, List<Entity> entities) {
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
		entities.add(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateEntity(EntityFields selector, EntityFields newData, boolean moreThanOne, List<Entity> entities) 
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
			entity.setAllFields(newData.getAllFields());
		}
		return entitiesMatched.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Entity> listEntities(EntityFields match, List<Entity> entities) {
		List<Entity> entitiesMatched = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity.hasFieldValues((EntityFieldsImpl)match)) {
				entitiesMatched.add(entity);
			}
		}
		return entitiesMatched;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteEntity(EntityFields match, Boolean moreThanOne, List<Entity> entities) {
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
	public void addCourse(Course course) {
		courses.add(course);
	}

	@Override
	public Boolean updateCourse(CourseFields selector, CourseFields newData, Boolean moreThanOne)
			throws TooManyMatchesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> listCourses(CourseFields match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCourse(CourseFields match, Boolean moreThanOne) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addSchool(School school) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Course> listSchools(String school) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteSchool(String school, Boolean moreThanOne) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String suggest(String prefix, EntityType entityType) {
		// TODO Auto-generated method stub
		return null;
	}

}
