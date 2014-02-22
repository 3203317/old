package com.myfvn.core.persist.pref;

public enum IPreferenceType {

	/** Datatype: Boolean */
	BOOLEAN,

	/** Datatype: Integer */
	INTEGER,

	/** Datatype: int[] */
	INTEGERS,

	/** Datatype: Long */
	LONG,

	/** Datatype: long[] */
	LONGS,

	/** Datatype: String */
	STRING,

	/** Datatype: String[] */
	STRINGS;

	/**
	 * @param obj
	 *            the object to infere the {@link IPreferenceType} from.
	 * @return the infered {@link IPreferenceType} from the given object.
	 */
	public static IPreferenceType getType(Object obj) {
		if (obj instanceof String)
			return STRING;

		if (obj instanceof Long)
			return LONG;

		if (obj instanceof Integer)
			return INTEGER;

		if (obj instanceof Boolean)
			return BOOLEAN;

		if (obj instanceof long[])
			return LONGS;

		if (obj instanceof int[])
			return INTEGERS;

		if (obj instanceof String[])
			return STRINGS;

		return STRING;
	}
}
