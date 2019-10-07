/*
 *
 *  * --------------------------------------------------------------------------
 *  *   Copyright (C) 1997 - 2019TeamAmerica
 *  * --------------------------------------------------------------------------
 *  *
 *  * NOTICE:  All information contained herein is, and remains the property of TeamAmeirca and its suppliers, if any.
 *  * The intellectual and technical concepts contained herein are proprietary to TeamAmeirca and its suppliers and are protected by trade secret or copyright law.
 *  * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from TeamAmeirca.
 *  *
 *  *
 *
 */

/**
 * 
 */
package com.teamamerica.tourbot.roomtypes.service.domain.pagination;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class SortBy.
 * 
 * @author pavan.solapure
 */
public class FilterBy {

	/** The map of sorts. */
	private Map<String, String> mapOfFilters;
	
	
	/** The global search. */
	private boolean globalSearch;

	/**
	 * Instantiates a new sort by.
	 */
	public FilterBy() {
		if (null == mapOfFilters) {
			mapOfFilters = new HashMap<String, String>();
		}
	}

	/**
	 * Gets the map of filters.
	 *
	 * @return the mapOfFilters
	 */
	public Map<String, String> getMapOfFilters() {
		return mapOfFilters;
	}

	/**
	 * Sets the map of filters.
	 *
	 * @param mapOfFilters            the mapOfFilters to set
	 */
	public void setMapOfFilters(Map<String, String> mapOfFilters) {
		this.mapOfFilters = mapOfFilters;
	}

	/**
	 * Adds the sort.
	 *
	 * @param filterColumn the filter column
	 * @param filterValue the filter value
	 */
	public void addFilter(String filterColumn, String filterValue) {
		mapOfFilters.put(filterColumn, filterValue);
	}

	/**
	 * Checks if is global search.
	 *
	 * @return the globalSearch
	 */
	public boolean isGlobalSearch() {
		return globalSearch;
	}

	/**
	 * Sets the global search.
	 *
	 * @param globalSearch the globalSearch to set
	 */
	public void setGlobalSearch(boolean globalSearch) {
		this.globalSearch = globalSearch;
	}

}
