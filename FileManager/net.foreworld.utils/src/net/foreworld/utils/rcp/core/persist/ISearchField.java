package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 * @allc true
 * 
 */
public interface ISearchField extends IPersistable {

	/**
	 * The ID of the search field is uniquely identifying it. It is important
	 * that the ID matches the value of the related constant in the affected
	 * type.
	 * <p>
	 * Example: In case the search-field is from the type <code>INews</code>
	 * and targeting a News' Title-Field, a call to <code>getFieldID()</code>
	 * should return <code>INews.TITLE</code>
	 * </p>
	 * 
	 * @return Returns the ID of the search field, uniquely identifying it.
	 */
	int getId();

	/**
	 * The fully qualified Name of the Entity this <code>ISearchField</code>
	 * is referring to.
	 * 
	 * @return The fully qualified Name of the Entity this
	 *         <code>ISearchField</code> is referring to.
	 */
	String getEntityName();

	/**
	 * The name of a field is used to represent it in human-readable form inside
	 * the UI.
	 * 
	 * @return Returns a human-readable representation of this field.
	 */
	String getName();

	/**
	 * The search-value-type is dependant on this field. For example some fields
	 * require a Date or Time as search-value ("Publish Date"). This information
	 * can also be used to perform the search in the persistance layer.
	 * 
	 * @return Returns the search-value-type related to this field.
	 */
	ISearchValueType getSearchValueType();
}
