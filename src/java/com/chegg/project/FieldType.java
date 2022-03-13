package com.chegg.project;

/**
 * Stores the types that a field can have.
 * @author alexrich
 */
public enum FieldType {
    STRING (null, "String"),
    USER (EntityType.USER, null),
    COURSE (EntityType.COURSE, null),
    SCHOOL (EntityType.SCHOOL, null);


    private final String name;
    private final EntityType entityType;
    FieldType(EntityType entity, String name) {
        this.entityType = entity;
        if (this.entityType != null) {
            this.name = this.entityType.getName();
        } else {
            this.name = name;
        }
    }

    /**
     * @return name of FieldType
     */
    String getName() {
        return this.name;
    }
}
