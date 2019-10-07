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

package com.teamamerica.tourbot.roomtypes.service.domain.pagination;

/**
 * The Enum SortOrder.
 */
public enum SortOrder {

	/** The asc. */
	ASC("ASC"), 
	/** The desc. */
	DESC("DESC");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new sort order.
	 * 
	 * @param v
	 *            the v
	 */
	SortOrder(String v) {
		value = v;
	}

	/**
	 * From value.
	 * 
	 * @param v
	 *            the v
	 * @return the sort order
	 */
	public static SortOrder fromValue(String v) {
		for (SortOrder c : SortOrder.values()) {
			if (c.name().equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	/**
	 * Value.
	 * 
	 * @return the string
	 */
	public String value() {
		return value;
	}

}
