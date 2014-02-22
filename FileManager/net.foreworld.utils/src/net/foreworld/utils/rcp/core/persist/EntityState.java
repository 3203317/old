package net.foreworld.utils.rcp.core.persist;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public enum EntityState {

	/** Entity is new create */
	NEW,
	/** Entity is normal */
	NORMAL,
	/** Entity has been updated */
	UPDATED,
	/** Entity has been deleted from the tree */
	HIDDEN,
	/** Entity is ready to be deleted from the database */
	DELETED;

	private static final transient Set<EntityState> VISIBLE_STATES = EnumSet
			.of(NEW, NORMAL, UPDATED);
	private static final transient EntityState[] VALUES = values();

	/**
	 * 
	 * @return
	 */
	public static final Set<EntityState> getVisible() {
		return Collections.unmodifiableSet(VISIBLE_STATES);
	}

	/**
	 * 
	 * @param $ordinal
	 * @return EntityState with the provided ordinal.
	 */
	public static final EntityState getState(int $ordinal) {
		return VALUES[$ordinal];
	}
}
