package net.foreworld.utils.rcp.core.utils;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 * 
 * @param <T>
 * @param
 *            <P>
 */
public class ReparentInfo<T, P> {
	private final T _object;
	private final P _newParent;
	private final T _newPosition;
	private final Boolean _after;

	/**
	 * Creates an instance of this object and return it. This is preferable to
	 * the constructor because it performs type inferencing, removing some
	 * duplication.
	 * 
	 * @param <T>
	 *            The type of the object that will have its parent changed.
	 * @param
	 *            <P>
	 *            The type of the parent.
	 * @param $object
	 *            Non-null object to be reparented.
	 * @param $newParent
	 *            Non-null new parent of <code>object</code>.
	 * @param $newPosition
	 *            Neighbour of <code>object</code> in <code>newParent</code>.
	 *            May be <code>null</code>.
	 * @param $after
	 *            Whether <code>object</code> will be before or after <code>
	 * newPosition</code>
	 *            in <code>newParent</code>. Has to be <code>null</code> if
	 *            <code>newPosition</code> is <code>null</code> and non-null
	 *            otherwise.
	 * @return ReparentInfo instance.
	 */
	public final static <T, P> ReparentInfo<T, P> create(T $object,
			P $newParent, T $newPosition, Boolean $after) {
		return new ReparentInfo<T, P>($object, $newParent, $newPosition, $after);
	}

	/**
	 * Creates an instance of this object.
	 * 
	 * @param $object
	 *            Non-null object to be reparented.
	 * @param $newParent
	 *            Non-null new parent of <code>object</code>.
	 * @param $newPosition
	 *            Neighbour of <code>object</code> in <code>newParent</code>.
	 *            May be <code>null</code>.
	 * @param $after
	 *            Whether <code>object</code> will be before or after <code>
	 * newPosition</code>
	 *            in <code>newParent</code>. Has to be <code>null</code> if
	 *            <code>newPosition</code> is <code>null</code> and non-null
	 *            otherwise.
	 */
	public ReparentInfo(T $object, P $newParent, T $newPosition, Boolean $after) {
		Assert.isNotNull($object, "object cannot be null"); //$NON-NLS-1$
		Assert.isNotNull($newParent, "newParent cannot be null"); //$NON-NLS-1$
		if ($newPosition == null)
			Assert.isLegal($after == null,
					"If position is null, after must also be null."); //$NON-NLS-1$
		else
			Assert.isNotNull($after,
					"if position is non-null, after must also be non-null"); //$NON-NLS-1$

		this._object = $object;
		this._newParent = $newParent;
		this._newPosition = $newPosition;
		this._after = $after;
	}

	/**
	 * @return the object that will have its parent changed.
	 */
	public final T getObject() {
		return _object;
	}

	/**
	 * @return the new parent of <code>object</code>.
	 */
	public final P getNewParent() {
		return _newParent;
	}

	/**
	 * @return the neighbour of <code>object</code> in <code>newParent</code>.
	 *         Together with <code>after</code> determines the actual position
	 *         of <code>object</code> in <code>newParent</code>. May be
	 *         <code>null</code>.
	 */
	public final T getNewPosition() {
		return _newPosition;
	}

	/**
	 * @return whether <code>object</code> will be positioned before or after
	 *         <code>newPosition</code> in <code>newParent</code>. May be
	 *         <code>null</code>.
	 */
	public final Boolean isAfter() {
		return _after;
	}
}
