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

import java.util.Iterator;
import java.util.Map.Entry;


/**
 * The Class PaginationCriteria.
 */
public class PaginationCriteria {

	/** The page number. */
	private Integer pageNumber;
	
	/** The page size. */
	private Integer pageSize;
	
	/** The total records. */
	private Integer totalRecords;
	
	/** The sort by. */
	private SortBy sortBy;
	
	/** The filter by. */
	private FilterBy filterBy;
	

	/**
	 * Gets the page number.
	 *
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return (null == pageNumber) ? 0 : pageNumber;
	}

	/**
	 * Sets the page number.
	 *
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * Gets the page size.
	 *
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return (null == pageSize) ? 10 : pageSize;
	}

	/**
	 * Sets the page size.
	 *
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Gets the total records.
	 *
	 * @return the totalRecords
	 */
	public Integer getTotalRecords() {
		return totalRecords;
	}

	/**
	 * Sets the total records.
	 *
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * Gets the sort by.
	 *
	 * @return the sortBy
	 */
	public SortBy getSortBy() {
		return sortBy;
	}

	/**
	 * Sets the sort by.
	 *
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * Gets the filter by.
	 *
	 * @return the filterBy
	 */
	public FilterBy getFilterBy() {
		return filterBy;
	}

	/**
	 * Sets the filter by.
	 *
	 * @param filterBy the filterBy to set
	 */
	public void setFilterBy(FilterBy filterBy) {
		this.filterBy = filterBy;
	}
	
	/**
	 * Checks if is filter by empty.
	 *
	 * @return true, if is filter by empty
	 */
	public boolean isFilterByEmpty() {
		if(null == filterBy || null == filterBy.getMapOfFilters() || filterBy.getMapOfFilters().size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is sort by empty.
	 *
	 * @return true, if is sort by empty
	 */
	public boolean isSortByEmpty() {
		if(null == sortBy || null == sortBy.getSortBys() || sortBy.getSortBys().size() == 0) {
			return true;
		}
		return false;
	}

//	private String handleCategoryFilter( Integer value) {
//		String result = BRKT_OPN + SPACE + BRKT_OPN;
//
//		int tmpValue= value.intValue();
//
//		switch (tmpValue) {
//			case 0:
//				result+= " isHiltonProduct=1 " ;
//				break;
//			case 1:
//				result+= " DynamicProduct=1 AND ( isHiltonProduct=0 OR isHiltonProduct IS NULL )";
//				break;
//			case 2:
//				result+= " ( DynamicProduct IS NULL OR DynamicProduct<>1 )  AND (isHiltonProduct IS NULL OR isHiltonProduct<>1)";
//				break;
//		}
//
//
//		result+= BRKT_CLS;
//
//		return result;
//	}

	private String handleNameFilter(String name){
		String result= BRKT_OPN + SPACE + BRKT_OPN;
		result+= "last"+LIKE_PREFIX+name+LIKE_SUFFIX+BRKT_CLS+OR+BRKT_OPN+SPACE+"first"+LIKE_PREFIX+name+LIKE_SUFFIX+BRKT_CLS;

		return result;
	}
	
	/**
	 * Gets the filter by clause.
	 *
	 * @return the filter by clause
	 */
	public String getFilterByClause() {
		
		StringBuilder fbsb = null;
		
		if (!isFilterByEmpty()) {
			Iterator<Entry<String, String>> fbit = filterBy.getMapOfFilters().entrySet().iterator();
			
			while (fbit.hasNext()) {
				
				Entry<String, String> pair =  fbit.next();
				
				if(null == fbsb) {
					fbsb = new StringBuilder();

					if(pair.getKey().equalsIgnoreCase("name")) {

						fbsb.append(handleNameFilter(pair.getValue()));
						/*fbsb.append(BRKT_CLS);*/
						continue;
					}

					fbsb.append(BRKT_OPN);
					
					fbsb.append(SPACE)
							.append(BRKT_OPN)
								.append(pair.getKey())
									.append(LIKE_PREFIX)
										.append(pair.getValue())
											.append(LIKE_SUFFIX)
												.append(BRKT_CLS);
					
				} else {
					
					fbsb.append(filterBy.isGlobalSearch() ? OR : AND);

					if(pair.getKey().equalsIgnoreCase("name")) {

						fbsb.append(handleNameFilter(pair.getValue()));
						fbsb.append(BRKT_CLS);
						continue;
					}

					fbsb.append(BRKT_OPN)
								  .append(pair.getKey())
									.append(LIKE_PREFIX)
										.append(pair.getValue())
											.append(LIKE_SUFFIX)
												.append(BRKT_CLS);

				}
			}
			fbsb.append(BRKT_CLS);
		}
		
		return (null == fbsb) ? BLANK : fbsb.toString();
	}
	
	/**
	 * Gets the order by clause.
	 *
	 * @return the order by clause
	 */
	public String getOrderByClause() {
		
		StringBuilder sbsb = null;
		
		if(!isSortByEmpty()) {
			Iterator<Entry<String, SortOrder>> sbit = sortBy.getSortBys().entrySet().iterator();
			
			while (sbit.hasNext()) {
				Entry<String, SortOrder> pair =  sbit.next();
				if(null == sbsb) {
					sbsb = new StringBuilder();
					if(pair.getKey().equalsIgnoreCase("name")){
						sbsb.append(ORDER_BY).append("last").append(SPACE).append(pair.getValue());
					}else{
						sbsb.append(ORDER_BY).append(pair.getKey()).append(SPACE).append(pair.getValue());
					}

				} else {
					if(pair.getKey().equalsIgnoreCase("name")){
						sbsb.append(COMMA).append("last").append(SPACE).append(pair.getValue());
					}else {
						sbsb.append(COMMA).append(pair.getKey()).append(SPACE).append(pair.getValue());
					}

				}
			}
		}
		
		return (null == sbsb) ? BLANK : sbsb.toString();
	}

	/** The Constant BLANK. */
	private static final String BLANK = "";
	
	/** The Constant SPACE. */
	private static final String SPACE = " ";
	
	/** The Constant LIKE_PREFIX. */
	private static final String LIKE_PREFIX = " LIKE '%";
	
	/** The Constant LIKE_SUFFIX. */
	private static final String LIKE_SUFFIX = "%' ";
	
	/** The Constant AND. */
	private static final String AND = " AND ";
	
	/** The Constant OR. */
	private static final String OR = " OR ";
	
	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = " ORDER BY ";
	
	private static final String BRKT_OPN = " ( ";
	
	private static final String BRKT_CLS = " ) ";
	
	/** The Constant COMMA. */
	private static final String COMMA = " , ";
	
	/** The Constant PAGE_NO. */
	public static final String PAGE_NO = "start";
	
	/** The Constant PAGE_SIZE. */
	public static final String PAGE_SIZE = "length";
	
	/** The Constant DRAW. */
	public static final String DRAW = "draw";
	
}
