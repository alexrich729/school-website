package com.chegg.project.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chegg.project.*;

public class ManagerImplTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Config config = new Config();
		List<User> users = new ArrayList<>();
		List<Course> courses = new ArrayList<>();
		List<School> schools = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			List<Field> fields = new ArrayList<>();
			fields.add(new Field("name", Character.toString((char) ('A' + i)), FieldType.STRING, true, null));
			fields.add(new Field("email", Character.toString((char) ('A' + i)) + "@email.com", FieldType.STRING, true, null));
			if (i % 2 == 0) {
				fields.add(new Field("isStudent", true, FieldType.BOOLEAN, null, null));
			} else {
				fields.add(new Field("isProfessor", true, FieldType.BOOLEAN, null, null));
			}
			users.add(new UserImpl(EntityType.USER, fields, config))
		}
		
		Manager manager = new ManagerImpl();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	public static Entity createNewEntity(EntityFields ef) {
		String name = (char) ('A' + (int)( Math.random() * 26.0)) + "" + (char) ('a' + (int)( Math.random() * 26.0)) + "" + (char) ('a' + (int)( Math.random() * 26.0));
		ef.setField("name", ef);
		return null;
	}

	@Test
	public void testManagerImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
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
