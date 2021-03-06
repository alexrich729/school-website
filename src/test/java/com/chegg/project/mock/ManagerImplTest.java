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
import com.chegg.project.exceptions.TooManyMatchesException;
import com.chegg.project.exceptions.runtime.ValidationException;

public class ManagerImplTest {
	private static ManagerImpl manager;
	private static Config config;
	private static EntityData data; // keeps track of how many of each type of user we have
	
	/**
	 * @param data if not null, is populated with the number of students vs. professors created; will exceed 100 total, since
	 *   a few users will be marked as both students and profs
	 * @return a Manager object with 100 mock entities of each type
	 * @throws IOException if Config fails to load
	 */
	private static ManagerImpl createManagerWithMockData(EntityData data) throws IOException {
		config = new Config();
		List<User> users = new ArrayList<>();
		List<Course> courses = new ArrayList<>();
		List<School> schools = new ArrayList<>();
		// creates dummy data to start off with
		for (int i = 0; i < 100; i++) {
			EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
			EntityFieldsImpl courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
			EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, config);
			schools.add(createNewEntity(schoolFields, data).buildSchool());
			courses.add(createNewEntity(courseFields, data).buildCourse());
			users.add(createNewEntity(userFields, data).buildUser());

		}
		
		return new ManagerImpl(users, courses, schools);
	}
	
	private static ManagerImpl createManagerWithMockData() throws IOException {
		return createManagerWithMockData(null);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		data = new EntityData();
		manager = createManagerWithMockData(data);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * Creates a new User, Course, or School with a random name, and other random values
	 * @param ef is of type that you want to be created
	 * @param data is EntityData record that is updated with new counts if not null
	 * @return the created entity
	 */
	public static Entity createNewEntity(EntityFieldsImpl ef, EntityData data) {
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
		
		if (data == null)   // keeps logic below simpler to just always record
			data = new EntityData();
		
		
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
			if (rand1 >= 0.5) {
				ef.setField("isStudent", true);
				data.numStudents++;
				if (rand1 >= 0.95) {
					ef.setField("isProfessor", true);
					data.numProfs++;
				}
			} else {
				ef.setField("isProfessor", true);
				data.numProfs++;
				if (rand1 <= 0.05) {
					ef.setField("isStudent", true);
					data.numStudents++;
				}
					
			}
			
			if (rand3 >= 0.5) {
				ef.setField("school", schools);
			}
			Entity userEntity = new EntityImpl(ef);
			return userEntity.buildUser();
		}
		return null;
	}
	
	/**
	 * Creates a new User, Course, or School with a random name, and other random values
	 * @param ef is of type that you want to be created
	 * @return the created entity
	 */
	public static Entity createNewEntity(EntityFieldsImpl ef) {
		return createNewEntity(ef, null);
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

	/**
	 * Tests updateUser by updating a user and looking for the user updated.
	 * @throws IOException if Config fails to load
	 * @throws ValidationException if new EntityFields isn't the type we think it is
	 * @throws TooManyMatchesException if updateUser finds more than one match
	 */
	@Test
	public void testUpdateUser() throws IOException, ValidationException, TooManyMatchesException {
		Manager manager = createManagerWithMockData();
		
		User user = manager.listUsers(null).get(0);
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, user.getSetFields(), config);
		EntityFieldsImpl searchFields = new EntityFieldsImpl(EntityType.USER, config);
		searchFields.setField("name", "Needle I. H. Stack");
		
		manager.updateUser(userFields.buildUserFields(), searchFields.buildUserFields(), false);
		List<User> users = manager.listUsers(searchFields.buildUserFields());
		assert(users.get(0).hasFieldValues(searchFields));
	}

	/**
	 * List all users - expect to see them all
	 */
	@Test
	public void testListAllUsers() {
		List<User> users = manager.listUsers(null);
		assertEquals(100, users.size());
	}

	/**
	 * List all students - expect to see the right number
	 */
	@Test
	public void testListStudents() {
		EntityFields matchProto = new EntityFieldsImpl(EntityType.USER, config);
		
		matchProto.setField("isStudent", true);
		
		UserFields match = matchProto.buildUserFields();
		List<User> students = manager.listUsers(match);
		
		assertEquals(data.numStudents, students.size());
	}
	
	
	/**
	 * List all profs - expect to see the right number
	 */
	public void testListProfessors() {
		EntityFields matchProto = new EntityFieldsImpl(EntityType.USER, config);
		
		matchProto.setField("isProfessor", true);
		
		UserFields match = matchProto.buildUserFields();
		List<User> profs = manager.listUsers(match);
		
		assertEquals(data.numProfs, profs.size());
	}

	/**
	 * Tests deleteUser by deleting one user and checking if # of users decreases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testDeleteUser() throws IOException {
		Manager manager = createManagerWithMockData();

		User user = manager.listUsers(null).get(0);
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, user.getSetFields(), config);
		manager.deleteUser(userFields.buildUserFields(), false);
		assertEquals(99, manager.listUsers(null).size());
	}
	
	/**
	 * List all users who are both students and profs - expect to see a number in the right range
	 */
	@Test
	public void testListStudentTeachers() {
		EntityFields matchProto = new EntityFieldsImpl(EntityType.USER, config);
		
		matchProto.setField("isProfessor", true);
		matchProto.setField("isStudent", true);
		
		UserFields match = matchProto.buildUserFields();
		List<User> studentTeachers = manager.listUsers(match);
		
		assertTrue(studentTeachers.size() > 0);
		assertTrue(studentTeachers.size() < data.numStudents);
		assertTrue(studentTeachers.size() < data.numProfs);
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

	/**
	 * Tests updateCourse by updating a course and looking for the user updated.
	 * @throws IOException if Config fails to load
	 * @throws ValidationException if new EntityFields isn't the type we think it is
	 * @throws TooManyMatchesException if updateCourse finds more than one match
	 */
	@Test
	public void testUpdateCourse() throws IOException, ValidationException, TooManyMatchesException {
		Manager manager = createManagerWithMockData();

		User user = manager.listUsers(null).get(0);
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, user.getSetFields(), config);
		EntityFieldsImpl searchFields = new EntityFieldsImpl(EntityType.USER, config);
		searchFields.setField("name", "Needle I. H. Stack");
		
		manager.updateUser(userFields.buildUserFields(), searchFields.buildUserFields(), false);
		List<User> users = manager.listUsers(searchFields.buildUserFields());
		assert(users.get(0).hasFieldValues(searchFields));
	}

	/**
	 * Just list all the courses and make sure we have the right number; match logic is tested with users
	 */
	@Test
	public void testListCourses() {
		List<Course> courses = manager.listCourses(null);
		assertEquals(100, courses.size());
	}

	/**
	 * Tests deleteCourse by deleting one course and checking if # of courses decreases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testDeleteCourse() throws IOException {
		Manager manager = createManagerWithMockData();

		Course course = manager.listCourses(null).get(0);
		EntityFieldsImpl courseFields = new EntityFieldsImpl(EntityType.COURSE, course.getSetFields(), config);
		manager.deleteCourse(courseFields.buildCourseFields(), false);
		assertEquals(99, manager.listCourses(null).size());
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

	/**
	 * Tests updateSchool by updating a user and looking for the school updated.
	 * @throws IOException if Config fails to load
	 * @throws ValidationException if new EntityFields isn't the type we think it is
	 * @throws TooManyMatchesException if updateSchool finds more than one match
	 */
	@Test
	public void testUpdateSchool() throws IOException, ValidationException, TooManyMatchesException {
		Manager manager = createManagerWithMockData();

		User user = manager.listUsers(null).get(0);
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, user.getSetFields(), config);
		EntityFieldsImpl searchFields = new EntityFieldsImpl(EntityType.USER, config);
		searchFields.setField("name", "Needle I. H. Stack");
		
		manager.updateUser(userFields.buildUserFields(), searchFields.buildUserFields(), false);
		List<User> users = manager.listUsers(searchFields.buildUserFields());
		assert(users.get(0).hasFieldValues(searchFields));
	}

	/**
	 * Just list all the schools and make sure we have the right number; match logic is tested with users
	 */
	@Test
	public void testListSchools() {
		List<School> schools = manager.listSchools(null);
		assertEquals(100, schools.size());
	}

	/**
	 * Tests deleteSchool by deleting one school and checking if # of schools decreases by one
	 * @throws IOException if config fails to load
	 */
	@Test
	public void testDeleteSchool() throws IOException {
		Manager manager = createManagerWithMockData();

		School school = manager.listSchools(null).get(0);
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, school.getSetFields(), config);
		manager.deleteSchool(schoolFields.buildSchoolFields(), false);
		assertEquals(99, manager.listSchools(null).size());
	}

	// underlying code is not implemented yet
	@Ignore
	@Test
	public void testSuggest() {
		fail("Not yet implemented");
	}

}
