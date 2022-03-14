package com.chegg.project.mock;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chegg.project.Config;
import com.chegg.project.CourseFields;
import com.chegg.project.EntityFields;
import com.chegg.project.EntityType;
import com.chegg.project.Field;

public class CourseFieldsImplTest {
	private static CourseFields courseFields;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Config config = new Config();
		EntityFields entityFields = new EntityFieldsImpl(EntityType.COURSE, config);
		courseFields = entityFields.buildCourseFields();
		courseFields.setField("name", "CS 101");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetType() {
		assertEquals(EntityType.COURSE, courseFields.getType());
	}

	@Test
	public void testGetSetFields() {
		List<Field> fields = courseFields.getSetFields();
		assertEquals(fields.size(), 1);
		assertEquals(fields.get(0).getName(), "name");
	}

	@Test
	public void testGetField() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetField() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAllFields() {
		fail("Not yet implemented");
	}

}
