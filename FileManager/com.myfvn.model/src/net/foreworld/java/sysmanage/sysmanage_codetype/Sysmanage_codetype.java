package net.foreworld.java.sysmanage.sysmanage_codetype;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.foreworld.java.models.BaseModel;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public class Sysmanage_codetype extends BaseModel {
	
	private String _fields = "id,uuid,codetype_name,codetype_desc,addtime,opt_sysmanage_user_id,availsign,startusing";
	
	public String getFields(){
		return this._fields;
	}
	
	public void setFields(String $fields){
		this._fields = $fields;
	}

	private String _detailTable;
	
	public String getDetailTable(){
		return this._detailTable;
	}
	
	public void setDetailTable(String $detailTable){
		this._detailTable = $detailTable;
	}

	private String _detailTablesName = "sysmanage_code";
	
	public String getDetailTablesName(){
		return this._detailTablesName;
	}
	
	public void setDetailTablesName(String $detailTablesName){
		this._detailTablesName = $detailTablesName;
	}

	private String _refTable;
	
	public String getRefTable(){
		return this._refTable;
	}
	
	public void setRefTable(String $refTable){
		this._refTable = $refTable;
	}

	private String _batchData;
	
	public String getBatchData(){
		return this._batchData;
	}
	
	public void setBatchData(String $batchData){
		this._batchData = $batchData;
	}

	/**
	 * @generated
	 **/
	private String _uuid;
	
	/**
	 * @generated
	 **/
	public String getUuid(){
		return this._uuid;
	}	
	
	/**
	 * @generated
	 **/
	public void setUuid(String $uuid){
		this._uuid = $uuid;
	}
	
	/**
	 * @generated
	 **/
	private String _uuid_operate;
	
	/**
	 * @generated
	 **/
	public String getUuid_operate(){
		return this._uuid_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setUuid_operate(String $uuid_operate){
		this._uuid_operate = $uuid_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _codetype_name;
	
	/**
	 * @generated
	 **/
	public String getCodetype_name(){
		return this._codetype_name;
	}	
	
	/**
	 * @generated
	 **/
	public void setCodetype_name(String $codetype_name){
		this._codetype_name = $codetype_name;
	}
	
	/**
	 * @generated
	 **/
	private String _codetype_name_operate;
	
	/**
	 * @generated
	 **/
	public String getCodetype_name_operate(){
		return this._codetype_name_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setCodetype_name_operate(String $codetype_name_operate){
		this._codetype_name_operate = $codetype_name_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _codetype_desc;
	
	/**
	 * @generated
	 **/
	public String getCodetype_desc(){
		return this._codetype_desc;
	}	
	
	/**
	 * @generated
	 **/
	public void setCodetype_desc(String $codetype_desc){
		this._codetype_desc = $codetype_desc;
	}
	
	/**
	 * @generated
	 **/
	private String _codetype_desc_operate;
	
	/**
	 * @generated
	 **/
	public String getCodetype_desc_operate(){
		return this._codetype_desc_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setCodetype_desc_operate(String $codetype_desc_operate){
		this._codetype_desc_operate = $codetype_desc_operate;
	}
	
	
	
	
}