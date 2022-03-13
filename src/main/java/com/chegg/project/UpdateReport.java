package com.chegg.project;

/**
 * Reports on the result of an update command; how many records were matched for update and how many were actually updated.
 * @author alexrich729
 *
 */
public interface UpdateReport {
	/**
	 * @return the number of entities that were matched by an update
	 */
	public Integer getNumberMatched();
	/**
	 * @return the number of entities actually changed
	 */
	public Integer getNumberUpdated();
}
