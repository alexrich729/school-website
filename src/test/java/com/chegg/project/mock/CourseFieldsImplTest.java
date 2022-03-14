package com.chegg.project.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chegg.project.Config;
import com.chegg.project.CourseFields;
import com.chegg.project.Entity;
import com.chegg.project.EntityFields;
import com.chegg.project.EntityType;
import com.chegg.project.Field;
import com.chegg.project.FieldType;
import com.chegg.project.School;
import com.chegg.project.exceptions.runtime.FieldNotSupportedException;
import com.chegg.project.exceptions.runtime.FieldTypeException;

public class CourseFieldsImplTest {
	private static Config config = null;
	private static CourseFields courseFields = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new Config();
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
		Field field = courseFields.getField("name");
		assertTrue("should have gotten the field \"name\" - it's set", field != null);
	}

	@Test
	public void testSetFieldNull() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		courseFields.setField("school", null);
		Field field = courseFields.getField("school");
		assertEquals(null, field.getValue());
	}

	/**
	 * Try setting school to a School entity, but school is a multi-value field, so it should throw an exception
	 */
	@Test(expected = FieldTypeException.class)
	public void testSetMultiWithSingle() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
		
		schoolFields.setField("name", "Purdue!");
		Entity schoolEntity = new EntityImpl(schoolFields);
		School school = schoolEntity.buildSchool();
		
		courseFields.setField("school", school);
	}
	
	/**
	 * Try setting school to an empty list; should be ok
	 */
	@Test
	public void testSetMultiWithEmptyList() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		
		courseFields.setField("school", new ArrayList<School>());
	}

	/**
	 * Try setting school to a list with the wrong type of value in it
	 */
	@Test(expected = FieldTypeException.class)
	public void testSetMultiWithListOfStrings() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		List<String> schoolList = new ArrayList<String>();
		
		schoolList.add("Purdue!");
		
		courseFields.setField("school", schoolList);
	}
	
	/**
	 * Try setting school to a list with a single School in it
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSetMultiWithGoodListLen1() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		List<School> schoolList = new ArrayList<>();
		
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
		
		schoolFields.setField("name", "Purdue!");
		Entity schoolEntity = new EntityImpl(schoolFields);
		School school = schoolEntity.buildSchool();
		
		schoolList.add(school);
		
		courseFields.setField("school", schoolList);
		
		Field field = courseFields.getField("school");
		schoolList = (List<School>)field.getValue();
		assertEquals(1, schoolList.size());
		
		school = schoolList.get(0);
		assertEquals("Purdue!", (String)school.getField("name").getValue());

	}
	
	/**
	 * Try setting school to a list with a two Schools in it
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSetMultiWithGoodListLen2() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		List<School> schoolList = new ArrayList<>();
		
		EntityFieldsImpl schoolFields = new EntityFieldsImpl(EntityType.SCHOOL, config);
		
		schoolFields.setField("name", "Purdue!");
		Entity schoolEntity = new EntityImpl(schoolFields);
		School school = schoolEntity.buildSchool();
		
		schoolList.add(school);
		
		EntityFieldsImpl schoolFields2 = new EntityFieldsImpl(EntityType.SCHOOL, config);
		schoolFields2.setField("name", "Emory");
		
		Entity schoolEntity2 = new EntityImpl(schoolFields2);
		School school2 = schoolEntity2.buildSchool();

		schoolList.add(school2);
		
		courseFields.setField("school", schoolList);
		
		Field field = courseFields.getField("school");
		schoolList = (List<School>)field.getValue();
		assertEquals(2, schoolList.size());
		
		school = schoolList.get(1);
		assertEquals("Emory", (String)school.getField("name").getValue());
	}
	

	@Test
	public void testSetAllFieldsGoodFields() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		
		List<Field> goodFields = new ArrayList<>();
		
		Field nameField = new Field("name", "CS 101", FieldType.STRING, true /* required */, false /* multi */);
		
		goodFields.add(nameField);
		
		courseFields.setAllFields(goodFields);
	}

	@Test(expected = FieldNotSupportedException.class)
	public void testSetAllFieldsWrongFields() {
		EntityFields courseFields = new EntityFieldsImpl(EntityType.COURSE, config);
		
		List<Field> wrongFields = new ArrayList<>();
		
		Field fooField = new Field("foo", "CS 101", FieldType.STRING, true /* required */, false /* multi */);
		
		wrongFields.add(fooField);
		
		courseFields.setAllFields(wrongFields);
	}

}
