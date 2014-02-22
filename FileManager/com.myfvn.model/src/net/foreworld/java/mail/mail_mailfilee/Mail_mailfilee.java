package net.foreworld.java.mail.mail_mailfilee;

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
public class Mail_mailfilee extends BaseModel {
	
	private String _fields = "id,file_name,summary,comment,tab_mail_maill_id,tab_mail_maill_uuid,uuid,version,addtime,opt_sysmanage_user_id,availsign,startusing";
	
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
	private String _file_name;
	
	/**
	 * @generated
	 **/
	public String getFile_name(){
		return this._file_name;
	}	
	
	/**
	 * @generated
	 **/
	public void setFile_name(String $file_name){
		this._file_name = $file_name;
	}
	
	/**
	 * @generated
	 **/
	private String _file_name_operate;
	
	/**
	 * @generated
	 **/
	public String getFile_name_operate(){
		return this._file_name_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setFile_name_operate(String $file_name_operate){
		this._file_name_operate = $file_name_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _summary;
	
	/**
	 * @generated
	 **/
	public String getSummary(){
		return this._summary;
	}	
	
	/**
	 * @generated
	 **/
	public void setSummary(String $summary){
		this._summary = $summary;
	}
	
	/**
	 * @generated
	 **/
	private String _summary_operate;
	
	/**
	 * @generated
	 **/
	public String getSummary_operate(){
		return this._summary_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setSummary_operate(String $summary_operate){
		this._summary_operate = $summary_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _comment;
	
	/**
	 * @generated
	 **/
	public String getComment(){
		return this._comment;
	}	
	
	/**
	 * @generated
	 **/
	public void setComment(String $comment){
		this._comment = $comment;
	}
	
	/**
	 * @generated
	 **/
	private String _comment_operate;
	
	/**
	 * @generated
	 **/
	public String getComment_operate(){
		return this._comment_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setComment_operate(String $comment_operate){
		this._comment_operate = $comment_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _tab_mail_maill_id;
	
	/**
	 * @generated
	 **/
	public Integer getTab_mail_maill_id(){
		return this._tab_mail_maill_id;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_maill_id(Integer $tab_mail_maill_id){
		this._tab_mail_maill_id = $tab_mail_maill_id;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_mail_maill_id_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_maill_id_operate(){
		return this._tab_mail_maill_id_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_maill_id_operate(String $tab_mail_maill_id_operate){
		this._tab_mail_maill_id_operate = $tab_mail_maill_id_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _tab_mail_maill_uuid;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_maill_uuid(){
		return this._tab_mail_maill_uuid;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_maill_uuid(String $tab_mail_maill_uuid){
		this._tab_mail_maill_uuid = $tab_mail_maill_uuid;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_mail_maill_uuid_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_maill_uuid_operate(){
		return this._tab_mail_maill_uuid_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_maill_uuid_operate(String $tab_mail_maill_uuid_operate){
		this._tab_mail_maill_uuid_operate = $tab_mail_maill_uuid_operate;
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
	private String _version;
	
	/**
	 * @generated
	 **/
	public String getVersion(){
		return this._version;
	}	
	
	/**
	 * @generated
	 **/
	public void setVersion(String $version){
		this._version = $version;
	}
	
	/**
	 * @generated
	 **/
	private String _version_operate;
	
	/**
	 * @generated
	 **/
	public String getVersion_operate(){
		return this._version_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setVersion_operate(String $version_operate){
		this._version_operate = $version_operate;
	}
	
	
	
	
}