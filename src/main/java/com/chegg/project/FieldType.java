package com.chegg.project;

/**
 * Stores the types that a field can have.
 * @author alexrich729
 */
public enum FieldType {
	// Each enum will be identified as an entity xor by a string name
	
	// Name  stringName  		
	BOOLEAN ("BOOLEAN"),
    STRING 	("STRING"),
	EMAIL	("EMAIL"),
    USER 	(EntityType.USER.getName()),
    COURSE 	(EntityType.COURSE.getName()),
    SCHOOL 	(EntityType.SCHOOL.getName());


    private final String name;
    
    FieldType(String name) {
        this.name = name;
    }

    /**
     * @return name of FieldType, either Entity name, String, Email, etc.
     */
    public String getName() {
        return this.name;
    }

	/**
	 * Maps string to one of the field type enums
	 * @param fieldTypeStr - the string
	 * @return the enum
	 */
	static FieldType fromString(String fieldTypeStr) {
		for (FieldType fieldType : FieldType.values()) {
			if (fieldType.getName().equals(fieldTypeStr))
				return fieldType;
		}
		throw new IllegalArgumentException
		("No field type with name " + fieldTypeStr + "; valid types are " + FieldType.values().toString());
	}

}
