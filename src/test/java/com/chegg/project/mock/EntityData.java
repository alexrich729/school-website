package com.chegg.project.mock;

/**
 *  Keeps track of data created in the test-data generation, so tests can match against expected values
 *  Used in ManagerImplTest, but cannot be nested due to being used in static context
 */
public class EntityData {
	public int numStudents = 0;
	public int numProfs = 0;
}