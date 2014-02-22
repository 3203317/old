package net.foreworld.utils.rcp.core.persist;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IChild extends IEntity, IReparentable<IParent> {

	/**
	 * 
	 * @return
	 */
	IParent getParent();

	/**
	 * 
	 */
	void setParent(IParent $newparent);

	/**
	 * 
	 * @param $parent_uuid
	 */
	void setParent_uuid(String $parent_uuid);

	/**
	 * 
	 * @return
	 */
	String getParent_uuid();
}
