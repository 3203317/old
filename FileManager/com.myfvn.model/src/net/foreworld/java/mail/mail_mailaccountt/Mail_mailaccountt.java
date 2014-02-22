package net.foreworld.java.mail.mail_mailaccountt;

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
public class Mail_mailaccountt extends BaseModel {
	
	private String _fields = "id,mail_user,mail_pass,mail_smtp_host,mail_smtp_auth,uuid,version,tab_sysmanage_user_id,tab_sysmanage_user_uuid,addtime,opt_sysmanage_user_id,availsign,startusing";
	
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
	private String _mail_user;
	
	/**
	 * @generated
	 **/
	public String getMail_user(){
		return this._mail_user;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_user(String $mail_user){
		this._mail_user = $mail_user;
	}
	
	/**
	 * @generated
	 **/
	private String _mail_user_operate;
	
	/**
	 * @generated
	 **/
	public String getMail_user_operate(){
		return this._mail_user_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_user_operate(String $mail_user_operate){
		this._mail_user_operate = $mail_user_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _mail_pass;
	
	/**
	 * @generated
	 **/
	public String getMail_pass(){
		return this._mail_pass;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_pass(String $mail_pass){
		this._mail_pass = $mail_pass;
	}
	
	/**
	 * @generated
	 **/
	private String _mail_pass_operate;
	
	/**
	 * @generated
	 **/
	public String getMail_pass_operate(){
		return this._mail_pass_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_pass_operate(String $mail_pass_operate){
		this._mail_pass_operate = $mail_pass_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _mail_smtp_host;
	
	/**
	 * @generated
	 **/
	public String getMail_smtp_host(){
		return this._mail_smtp_host;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_smtp_host(String $mail_smtp_host){
		this._mail_smtp_host = $mail_smtp_host;
	}
	
	/**
	 * @generated
	 **/
	private String _mail_smtp_host_operate;
	
	/**
	 * @generated
	 **/
	public String getMail_smtp_host_operate(){
		return this._mail_smtp_host_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_smtp_host_operate(String $mail_smtp_host_operate){
		this._mail_smtp_host_operate = $mail_smtp_host_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _mail_smtp_auth;
	
	/**
	 * @generated
	 **/
	public Integer getMail_smtp_auth(){
		return this._mail_smtp_auth;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_smtp_auth(Integer $mail_smtp_auth){
		this._mail_smtp_auth = $mail_smtp_auth;
	}
	
	/**
	 * @generated
	 **/
	private String _mail_smtp_auth_operate;
	
	/**
	 * @generated
	 **/
	public String getMail_smtp_auth_operate(){
		return this._mail_smtp_auth_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setMail_smtp_auth_operate(String $mail_smtp_auth_operate){
		this._mail_smtp_auth_operate = $mail_smtp_auth_operate;
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
	
	
	/**
	 * @generated
	 **/
	private Integer _tab_sysmanage_user_id;
	
	/**
	 * @generated
	 **/
	public Integer getTab_sysmanage_user_id(){
		return this._tab_sysmanage_user_id;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_sysmanage_user_id(Integer $tab_sysmanage_user_id){
		this._tab_sysmanage_user_id = $tab_sysmanage_user_id;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_sysmanage_user_id_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_sysmanage_user_id_operate(){
		return this._tab_sysmanage_user_id_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_sysmanage_user_id_operate(String $tab_sysmanage_user_id_operate){
		this._tab_sysmanage_user_id_operate = $tab_sysmanage_user_id_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _tab_sysmanage_user_uuid;
	
	/**
	 * @generated
	 **/
	public String getTab_sysmanage_user_uuid(){
		return this._tab_sysmanage_user_uuid;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_sysmanage_user_uuid(String $tab_sysmanage_user_uuid){
		this._tab_sysmanage_user_uuid = $tab_sysmanage_user_uuid;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_sysmanage_user_uuid_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_sysmanage_user_uuid_operate(){
		return this._tab_sysmanage_user_uuid_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_sysmanage_user_uuid_operate(String $tab_sysmanage_user_uuid_operate){
		this._tab_sysmanage_user_uuid_operate = $tab_sysmanage_user_uuid_operate;
	}
	
	
	
	
}