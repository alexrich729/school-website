package com.chegg.project;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ConfigTest {
	private static Config config = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new Config();
	}
	
	@Test
	public void testGetFields() {
		List<Field> fields = config.getFields(EntityType.COURSE);
		assertEquals("Course entity should have name and school fields", 2, fields.size());
	}

	@Test
	public void testGetRequiredFields() {
		List<Field> fields = config.getRequiredFields(EntityType.COURSE);
		assertEquals("Course entity has one required field, name", 1, fields.size());
	}

	@Test
	public void testGetOptionalFields() {
		List<Field> fields = config.getOptionalFields(EntityType.COURSE);
		assertEquals("Course entity has one optional field, school", 1, fields.size());
	}

}
