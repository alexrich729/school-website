package com.chegg.project.mock;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.chegg.project.*;

public class ManagerImplTest {
	private static ManagerImpl manager;
	private static Config config;
	
	/**
	 * @return a Manager object with 100 mock entities of each type
	 * @throws IOException if Config fails to load
	 */
	private static ManagerImpl createManagerWithMockData() throws IOException {
		config = new Config();
		List<User> users = new ArrayList<>();
		List<Course> courses = new ArrayList<>();
		List<School> schools = new ArrayList<>();
		// creates dummy data to start off with
		for (int i = 0; i < 100; i++) {
			EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
			EntityFieldsImpl courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
			EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, config);
			schools.add(createNewEntity(schoolFields).buildSchool());
			courses.add(createNewEntity(courseFields).buildCourse());
			users.add(createNewEntity(userFields).buildUser());

		}
		
		return new ManagerImpl(users, courses, schools);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		manager = createManagerWithMockData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * Creates a new User, Course, or School with a random name, and other random values
	 * @param ef is of type that you want to be created
	 * @return the created entity
	 */
	public static Entity createNewEntity(EntityFieldsImpl ef) {
		double rand1 = Math.random();
		double rand2 = Math.random();
		double rand3 = Math.random();

		String name = (char) ('A' + (int)( rand1 * 26.0)) + "" + (char) ('a' + (int)( rand2 * 26.0)) + "" + (char) ('a' + (int)( rand3 * 26.0));
		ef.setField("name", name);
		
		// for if one of the fields is a list of schools
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, ef.getConfig());
		schoolFields.setField("name", name);
		Entity schoolEntity = new EntityImpl(schoolFields);
		School school = schoolEntity.buildSchool();
		List<School> schools = new ArrayList<>();
		schools.add(school);
		
		
		if (ef.getType() == EntityType.SCHOOL) {
			return school;
		} else if (ef.getType() == EntityType.COURSE) {
			if (rand2 >= 0.5) {
				ef.setField("school", schools);
			}
			Entity courseEntity = new EntityImpl(ef);
			return courseEntity.buildCourse();
		} else if (ef.getType() == EntityType.USER) {
			ef.setField("email", name + "@email.com");
			if (rand1 >= 0.5)
				ef.setField("isStudent", true);
			else
				ef.setField("isProfessor", true);
			
			if (rand3 >= 0.5) {
				ef.setField("school", schools);
			}
			Entity userEntity = new EntityImpl(ef);
			return userEntity.buildUser();
		}
		return null;
	}

	/**
	 * Add a user and verify that the number of users increases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testAddUser() throws IOException {
		Manager manager = createManagerWithMockData();
		
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, config);
		manager.addUser(createNewEntity(userFields).buildUser());
		assertEquals(101, manager.listUsers(null).size());
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAllUsers() {
		List<User> users = manager.listUsers(null);
		assertEquals(100, users);
	}

	@Test
	public void testListStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testListProfessors() {
		fail("Not yet implemented");
	}

	/**
	 * Add a user and verify that the number of users increases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testAddCourse() throws IOException {
		Manager manager = createManagerWithMockData();

		EntityFieldsImpl courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		manager.addCourse(createNewEntity(courseFields).buildCourse());
		assertEquals(101, manager.listCourses(null).size());	}

	@Test
	public void testUpdateCourseCourseFieldsCourseFieldsBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testListCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCourse() {
		fail("Not yet implemented");
	}

	/**
	 * Add a user and verify that the number of users increases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testAddSchool() throws IOException {
		Manager manager = createManagerWithMockData();
		
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
		manager.addSchool(createNewEntity(schoolFields).buildSchool());
		assertEquals(101, manager.listSchools(null).size());
	}

	@Test
	public void testUpdateCourseSchoolFieldsSchoolFieldsBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testListSchools() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteSchool() {
		fail("Not yet implemented");
	}

	// underlying code is not implemented yet
	@Ignore
	@Test
	public void testSuggest() {
		fail("Not yet implemented");
	}

}
