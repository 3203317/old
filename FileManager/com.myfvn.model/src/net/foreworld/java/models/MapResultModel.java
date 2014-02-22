package net.foreworld.java.models;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Map;


/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public class MapResultModel extends MapModel implements Map{

	public Object get(Object $key){
		return this._mapModel.get($key);
	}
	
	public Object put(Object $key, Object $value){
		
		return super.put($key, $value);
	}
}