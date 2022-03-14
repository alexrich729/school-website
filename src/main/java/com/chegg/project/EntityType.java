package com.chegg.project;

/**
 * This is an enumeration of the different types of records we are managing.
 * @author alexrich729
 *
 */
public enum EntityType {
	USER		("USER"),
	SCHOOL		("SCHOOL"),
	COURSE		("COURSE");
	
	/** The name of the EntityType as a String */
	private final String name;
	EntityType(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the EntityType
	 */
	String getName() {
		return this.name;
	}
}
