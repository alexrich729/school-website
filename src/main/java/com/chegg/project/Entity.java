package com.chegg.project;


/**
 * 
 * Provides the reusable interface for any of our configurable and extensible entity types, school, user, course, etc.
 * Objects implementing this interface are expected to do validation after setting all fields to make sure all required
 * fields are set.
 * 
 * @author alexrich729
 *
 */

public interface Entity extends EntityFields {
}
