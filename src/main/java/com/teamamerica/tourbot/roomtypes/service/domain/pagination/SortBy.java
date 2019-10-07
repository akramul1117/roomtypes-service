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
public class SortBy {
	
	/** The map of sorts. */
	private Map<String, SortOrder> mapOfSorts;
	
	/**
	 * Instantiates a new sort by.
	 */
	public SortBy() {
		if(null == mapOfSorts) {
			mapOfSorts = new HashMap<String, SortOrder>();
		}
	}

	/**
	 * Gets the sort bys.
	 *
	 * @return the sortBys
	 */
	public Map<String, SortOrder>  getSortBys() {
		return mapOfSorts;
	}
	
	/**
	 * Adds the sort.
	 *
	 * @param sortBy the sort by
	 */
	public void addSort(String sortBy) {
		mapOfSorts.put(sortBy, SortOrder.ASC);
	}
	
	/**
	 * Adds the sort.
	 *
	 * @param sortBy the sort by
	 * @param sortOrder the sort order
	 */
	public void addSort(String sortBy, SortOrder sortOrder) {
		mapOfSorts.put(sortBy, sortOrder);
	}

}
