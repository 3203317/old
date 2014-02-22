package net.foreworld.utils.rcp.core.persist;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public interface IEntity extends IPersistable {

	/**
	 * 
	 */
	public static final int ALL_FIELDS = -1;

	/**
	 * 
	 * @param $key
	 * @param $value
	 */
	void setProperty(String $key, Serializable $value);

	/**
	 * 
	 * @param $key
	 * @return
	 */
	Object getProperty(String $key);

	/**
	 * 
	 * @param $key
	 * @return
	 */
	Object removeProperty(String $key);

	/**
	 * 
	 * @return
	 */
	Map<String, Serializable> getProperties();

	/**
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 
	 * @param $id
	 */
	void setId(int $id);

	/**
	 * 
	 * @return
	 */
	String getUuid();

	/**
	 * 
	 * @param $uuid
	 */
	void setUuid(String $uuid);

	/**
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 
	 * @param $name
	 */
	void setName(String $name);

	/**
	 * 
	 * @param $state
	 */
	void setState(EntityState $state);

	/**
	 * 
	 * @return
	 */
	EntityState getState();

}
