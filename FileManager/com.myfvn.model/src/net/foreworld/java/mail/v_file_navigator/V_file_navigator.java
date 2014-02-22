package net.foreworld.java.mail.v_file_navigator;

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
public class V_file_navigator extends BaseModel {
	
	private String _fields = "id,parent_id,parent_uuid,node_name,node_type,uuid,node_desc,addtime,opt_sysmanage_user_id,availsign,startusing";
	
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

	private String _detailTablesName = "";
	
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
	private Integer _parent_id;
	
	/**
	 * @generated
	 **/
	public Integer getParent_id(){
		return this._parent_id;
	}	
	
	/**
	 * @generated
	 **/
	public void setParent_id(Integer $parent_id){
		this._parent_id = $parent_id;
	}
	
	/**
	 * @generated
	 **/
	private String _parent_id_operate;
	
	/**
	 * @generated
	 **/
	public String getParent_id_operate(){
		return this._parent_id_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setParent_id_operate(String $parent_id_operate){
		this._parent_id_operate = $parent_id_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _parent_uuid;
	
	/**
	 * @generated
	 **/
	public String getParent_uuid(){
		return this._parent_uuid;
	}	
	
	/**
	 * @generated
	 **/
	public void setParent_uuid(String $parent_uuid){
		this._parent_uuid = $parent_uuid;
	}
	
	/**
	 * @generated
	 **/
	private String _parent_uuid_operate;
	
	/**
	 * @generated
	 **/
	public String getParent_uuid_operate(){
		return this._parent_uuid_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setParent_uuid_operate(String $parent_uuid_operate){
		this._parent_uuid_operate = $parent_uuid_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _node_name;
	
	/**
	 * @generated
	 **/
	public String getNode_name(){
		return this._node_name;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_name(String $node_name){
		this._node_name = $node_name;
	}
	
	/**
	 * @generated
	 **/
	private String _node_name_operate;
	
	/**
	 * @generated
	 **/
	public String getNode_name_operate(){
		return this._node_name_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_name_operate(String $node_name_operate){
		this._node_name_operate = $node_name_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _node_type;
	
	/**
	 * @generated
	 **/
	public String getNode_type(){
		return this._node_type;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_type(String $node_type){
		this._node_type = $node_type;
	}
	
	/**
	 * @generated
	 **/
	private String _node_type_operate;
	
	/**
	 * @generated
	 **/
	public String getNode_type_operate(){
		return this._node_type_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_type_operate(String $node_type_operate){
		this._node_type_operate = $node_type_operate;
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
	private String _node_desc;
	
	/**
	 * @generated
	 **/
	public String getNode_desc(){
		return this._node_desc;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_desc(String $node_desc){
		this._node_desc = $node_desc;
	}
	
	/**
	 * @generated
	 **/
	private String _node_desc_operate;
	
	/**
	 * @generated
	 **/
	public String getNode_desc_operate(){
		return this._node_desc_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setNode_desc_operate(String $node_desc_operate){
		this._node_desc_operate = $node_desc_operate;
	}
	
	
	
	
}