package net.foreworld.utils.rcp.core.internal.persist;

import java.util.Collections;
import java.util.List;

import net.foreworld.utils.rcp.core.persist.ISearchValueType;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * <p>
 * Instances of <code>ISearchValueType</code> describe the data-type for a
 * search-field. Most types reflect well known ones as used in relational
 * databases. They are helpful to validate a search-value for a given field and
 * to perform the search in the persistance layer.
 * </p>
 * <p>
 * In case the data-type is <code>ENUM</code>, a call to
 * <code>getEnumValues()</code> has to be used in order to retrieve the valid
 * search-values.
 * </p>
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 * @allc true
 * @modify true
 * 
 * 
 */
public class SearchValueType implements ISearchValueType {
	private final int _type;
	private List<String> _enumValues;

	/* Commonly used STRING Type */
	static final SearchValueType STRING = new SearchValueType(
			ISearchValueType.STRING);

	/* Commonly used INTEGER Type */
	static final SearchValueType INTEGER = new SearchValueType(
			ISearchValueType.INTEGER);

	/* Commonly used DATETIME Type */
	static final SearchValueType DATETIME = new SearchValueType(
			ISearchValueType.DATETIME);

	/* Commonly used BOOLEAN Type */
	static final SearchValueType BOOLEAN = new SearchValueType(
			ISearchValueType.BOOLEAN);

	/* Commonly used LINK Type */
	static final SearchValueType LINK = new SearchValueType(
			ISearchValueType.LINK);

	/**
	 * Instantiates a new SearchValueType that is of any Type <em>not</em>
	 * <code>ENUM</code>.
	 * 
	 * @param $type
	 *            One of the constants as defined in
	 *            <code>ISearchValueType</code>
	 */
	public SearchValueType(int $type) {
		Assert
				.isLegal($type != ENUM,
						"Use the other constructor to supply a list of Enumeration values."); //$NON-NLS-1$
		_type = $type;
	}

	/**
	 * Instantiates a new SearchValueType that is of the Type <code>ENUM</code>.
	 * 
	 * @param $enumValues
	 *            A List of allowed values as Strings.
	 */
	public SearchValueType(List<String> $enumValues) {
		Assert
				.isNotNull(
						$enumValues,
						"The type SearchValueType of Type ENUM requires a List of Enum-Values that is not NULL"); //$NON-NLS-1$
		_enumValues = $enumValues;
		_type = ENUM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.foreworld.utils.rcp.core.persist.ISearchValueType#getId()
	 */
	public synchronized int getId() {
		return _type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.foreworld.utils.rcp.core.persist.ISearchValueType#getEnumValues()
	 */
	public synchronized List<String> getEnumValues() {
		return Collections.unmodifiableList(_enumValues);
	}

	/**
	 * Returns an object which is an instance of the given class associated with
	 * this object. Returns <code>null</code> if no such object can be found.
	 * <p>
	 * This implementation of the method declared by <code>IAdaptable</code>
	 * passes the request along to the platform's adapter manager; roughly
	 * <code>Platform.getAdapterManager().getAdapter(this, adapter)</code>.
	 * Subclasses may override this method (however, if they do so, they should
	 * invoke the method on their superclass to ensure that the Platform's
	 * adapter manager is consulted).
	 * </p>
	 * 
	 * @param $adapter
	 *            the class to adapt to
	 * @return the adapted object or <code>null</code>
	 * @see IAdaptable#getAdapter(Class)
	 * @see Platform#getAdapterManager()
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class $adapter) {
		return Platform.getAdapterManager().getAdapter(this, $adapter);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public synchronized boolean equals(Object $obj) {
		if (this == $obj)
			return true;

		if (($obj == null) || ($obj.getClass() != getClass()))
			return false;

		synchronized ($obj) {
			SearchValueType ___s = (SearchValueType) $obj;

			/* Compare Enum of Values if types are ENUM */
			if (_type == ENUM && ___s._type == ENUM)
				return _enumValues.equals(___s._enumValues);

			/* Compare Type */
			return _type == ___s._type;
		}
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public synchronized int hashCode() {
		if (_type != ENUM)
			return ((_type * getClass().hashCode() + 17)) * 37;
		return _enumValues.hashCode();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public synchronized String toString() {
		String __type;
		switch (_type) {
		case ISearchValueType.BOOLEAN:
			__type = "Boolean"; //$NON-NLS-1$
			break;
		case ISearchValueType.DATE:
			__type = "Date"; //$NON-NLS-1$
			break;
		case ISearchValueType.DATETIME:
			__type = "DateTime"; //$NON-NLS-1$
			break;
		case ISearchValueType.ENUM:
			__type = "Enum"; //$NON-NLS-1$
			break;
		case ISearchValueType.INTEGER:
			__type = "Integer"; //$NON-NLS-1$
			break;
		case ISearchValueType.NUMBER:
			__type = "Number"; //$NON-NLS-1$
			break;
		case ISearchValueType.STRING:
			__type = "String"; //$NON-NLS-1$
			break;
		case ISearchValueType.TIME:
			__type = "Time"; //$NON-NLS-1$
			break;
		case ISearchValueType.LINK:
			__type = "Link"; //$NON-NLS-1$
			break;
		default:
			__type = "Unknown"; //$NON-NLS-1$
		}

		if (_type != ENUM)
			return super.toString() + "(Type = " + __type + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		return super.toString()
				+ "(Type = " + __type + ", Values = " + _enumValues + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
