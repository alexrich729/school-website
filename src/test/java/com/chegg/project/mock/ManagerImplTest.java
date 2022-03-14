package com.chegg.project.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chegg.project.*;

public class ManagerImplTest {
	private static ManagerImpl manager;
	private static Config config;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		
		manager = new ManagerImpl(users, courses, schools);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	public static Entity createNewEntity(EntityFieldsImpl ef) {
		double rand1 = Math.random();
		double rand2 = Math.random();
		double rand3 = Math.random();

		String name = (char) ('A' + (int)( rand1 * 26.0)) + "" + (char) ('a' + (int)( rand2 * 26.0)) + "" + (char) ('a' + (int)( rand3 * 26.0));
		ef.setField("name", name);
		
		
		if (ef.getType() == EntityType.SCHOOL) {
			Entity schoolEntity = new EntityImpl(ef);
			return schoolEntity.buildSchool();
		} else if (ef.getType() == EntityType.COURSE) {
			if (rand2 >= 0.5)
				ef.setField("school", name);
			Entity courseEntity = new EntityImpl(ef);
			return courseEntity.buildCourse();
		} else if (ef.getType() == EntityType.USER) {
			ef.setField("email", name + "@email");
			if (rand1 >= 0.5)
				ef.setField("isStudent", true);
			else
				ef.setField("isTeacher", true);
			
			if (rand3 >= 0.5) {
				EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, ef.getConfig());
				schoolFields.setField("name", name);
				Entity schoolEntity = new EntityImpl(schoolFields);
				School school = schoolEntity.buildSchool();
				ef.setField("school", school);
			}
			Entity userEntity = new EntityImpl(ef);
			return userEntity.buildUser();
		}
		return null;
	}

	@Test
	public void testManagerImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		EntityFieldsImpl userFields = new EntityFieldsImpl(EntityType.USER, config);
		manager.addUser(createNewEntity(userFields).buildUser());
		assertEquals(101, manager.listUsers(null));
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testListUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCourse() {
		fail("Not yet implemented");
	}

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

	@Test
	public void testAddSchool() {
		fail("Not yet implemented");
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

	@Test
	public void testSuggest() {
		fail("Not yet implemented");
	}

}
