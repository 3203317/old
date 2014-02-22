package net.foreworld.utils.rcp.core.persist;

import java.util.List;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 * @allc true
 * @modify true
 * 
 */
public interface ISearchValueType extends IPersistable {

	/** The most open type. Anything allowed here */
	public static final int STRING = 0;

	/** A Date containing of Year, Month and Day */
	public static final int DATE = 1;

	/** A Time containing of Hour, Minute and Second */
	public static final int TIME = 2;

	/** A Timestamp giving info on Date and Time */
	public static final int DATETIME = 3;

	/** The Integer Type */
	public static final int INTEGER = 4;

	/** Matching any Number, either Float or Integer */
	public static final int NUMBER = 5;

	/** The Boolean Type can only have TRUE or FALSE */
	public static final int BOOLEAN = 6;

	/** Enumeration of allowed Strings specified by <code>getEnumValues()</code> */
	public static final int ENUM = 7;

	/** A Link (usually not tokenized) */
	public static final int LINK = 8;

	/**
	 * Get the Type, which is one of the Type as defined in this interface.
	 * 
	 * @return Returns one of the Type as defined in this interface.
	 */
	int getId();

	/**
	 * Get a List of allowed Strings for this ENUM type.
	 * <p>
	 * This Method should <em>only</em> return values in case
	 * <code>getSearchValueType()</code> returns
	 * <code>ISearchValueType.ENUM</code>
	 * </p>
	 * 
	 * <p>
	 * Note that an unmodifiable list is returned by this method. Trying to
	 * modify it will cause a UnsupportedOperationException to be thrown.
	 * </p>
	 * 
	 * @return Returns a List of allowed Strings for this ENUM type.
	 */
	List<String> getEnumValues();
}
