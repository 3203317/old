package net.foreworld.utils.rcp.core.persist;

import java.util.List;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IParent extends IChild {

	/** One of the fields in this type described as constant */
	public static final int NAME = 0;

	/**
	 * @return {@code true} if the folder has no children. Otherwise, it returns
	 *         {@code false}.
	 */
	public boolean isEmpty();

	/**
	 * Returns {@code true} if this IFolder contains {@code child} and {@false}
	 * otherwise.
	 * 
	 * @param $child
	 *            element whose presence should be tested.
	 * 
	 * @return {@code true} if this IFolder contains {@code child} and {@false}
	 *         otherwise.
	 */
	public boolean containsChild(IChild $child);

	/**
	 * Get a list of the children contained in this folder. Typically, these
	 * children will be of type IMark or IFolder.
	 * 
	 * @return a list of children contained in this folder.
	 *         <p>
	 *         Note: The returned List should not be modified. The default
	 *         implementation returns an unmodifiable List using
	 *         <code>Collections.unmodifiableList()</code>. Trying to modify
	 *         the List will result in
	 *         <code>UnsupportedOperationException</code>.
	 *         </p>
	 * @see #getMarks()
	 * @see #getFolders()
	 */
	List<IChild> getChildren();

	/**
	 * Of there is an instance of <code>IFolderChild</code> that is equal to
	 * <code>child</code> in the list of children, removes it and returns
	 * <code>true</code>. Otherwise, returns <code>false</code>.
	 * 
	 * @param $child
	 *            An instance of <code>IFolderChild</code> to be removed.
	 * @return <code>true</code> if a child is removed from children,
	 *         <code>false</code> otherwise.
	 */
	boolean removeChild(IChild $child);

	/**
	 * 
	 * @param $child
	 * @param $position
	 * @param $after
	 */
	void addChild(IChild $child, IChild $position, boolean $after);

	/**
	 * Moves a List of <code>IFolderChild</code> contained in this Folder to a
	 * new position.
	 * 
	 * @param $children
	 *            The List of <code>IFolderChild</code> being moved to a new
	 *            position.
	 * @param $position
	 *            The new Position identified by a <code>IFolderChild</code>
	 *            contained in this folder.
	 * @param $after
	 *            If <code>true</code>, move the folders to a one index after
	 *            the given position. May be <code>NULL</code> if the position
	 *            is not provided.
	 */
	void reorderChildren(List<? extends IChild> $children, IChild $position, boolean $after);

	/**
	 * Sorts all {@link IFolderChild} contained in this folder by their names.
	 * Different kinds of {@link IFolderChild} get grouped together and not
	 * mixed.
	 */
	void sort();

}
