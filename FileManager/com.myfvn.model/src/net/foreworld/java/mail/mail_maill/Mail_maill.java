package net.foreworld.java.mail.mail_maill;

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
public class Mail_maill extends BaseModel {
	
	private String _fields = "id,from1,to1,cc,bcc,subject,summary,content,tab_mail_mailaccountt_id,tab_mail_mailaccountt_uuid,uuid,version,tab_mail_maill_id,tab_mail_maill_uuid,addtime,opt_sysmanage_user_id,availsign,startusing";
	
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
	private String _from1;
	
	/**
	 * @generated
	 **/
	public String getFrom1(){
		return this._from1;
	}	
	
	/**
	 * @generated
	 **/
	public void setFrom1(String $from1){
		this._from1 = $from1;
	}
	
	/**
	 * @generated
	 **/
	private String _from1_operate;
	
	/**
	 * @generated
	 **/
	public String getFrom1_operate(){
		return this._from1_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setFrom1_operate(String $from1_operate){
		this._from1_operate = $from1_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _to1;
	
	/**
	 * @generated
	 **/
	public String getTo1(){
		return this._to1;
	}	
	
	/**
	 * @generated
	 **/
	public void setTo1(String $to1){
		this._to1 = $to1;
	}
	
	/**
	 * @generated
	 **/
	private String _to1_operate;
	
	/**
	 * @generated
	 **/
	public String getTo1_operate(){
		return this._to1_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTo1_operate(String $to1_operate){
		this._to1_operate = $to1_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _cc;
	
	/**
	 * @generated
	 **/
	public String getCc(){
		return this._cc;
	}	
	
	/**
	 * @generated
	 **/
	public void setCc(String $cc){
		this._cc = $cc;
	}
	
	/**
	 * @generated
	 **/
	private String _cc_operate;
	
	/**
	 * @generated
	 **/
	public String getCc_operate(){
		return this._cc_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setCc_operate(String $cc_operate){
		this._cc_operate = $cc_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _bcc;
	
	/**
	 * @generated
	 **/
	public String getBcc(){
		return this._bcc;
	}	
	
	/**
	 * @generated
	 **/
	public void setBcc(String $bcc){
		this._bcc = $bcc;
	}
	
	/**
	 * @generated
	 **/
	private String _bcc_operate;
	
	/**
	 * @generated
	 **/
	public String getBcc_operate(){
		return this._bcc_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setBcc_operate(String $bcc_operate){
		this._bcc_operate = $bcc_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _subject;
	
	/**
	 * @generated
	 **/
	public String getSubject(){
		return this._subject;
	}	
	
	/**
	 * @generated
	 **/
	public void setSubject(String $subject){
		this._subject = $subject;
	}
	
	/**
	 * @generated
	 **/
	private String _subject_operate;
	
	/**
	 * @generated
	 **/
	public String getSubject_operate(){
		return this._subject_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setSubject_operate(String $subject_operate){
		this._subject_operate = $subject_operate;
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
	private String _content;
	
	/**
	 * @generated
	 **/
	public String getContent(){
		return this._content;
	}	
	
	/**
	 * @generated
	 **/
	public void setContent(String $content){
		this._content = $content;
	}
	
	/**
	 * @generated
	 **/
	private String _content_operate;
	
	/**
	 * @generated
	 **/
	public String getContent_operate(){
		return this._content_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setContent_operate(String $content_operate){
		this._content_operate = $content_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _tab_mail_mailaccountt_id;
	
	/**
	 * @generated
	 **/
	public Integer getTab_mail_mailaccountt_id(){
		return this._tab_mail_mailaccountt_id;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_mailaccountt_id(Integer $tab_mail_mailaccountt_id){
		this._tab_mail_mailaccountt_id = $tab_mail_mailaccountt_id;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_mail_mailaccountt_id_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_mailaccountt_id_operate(){
		return this._tab_mail_mailaccountt_id_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_mailaccountt_id_operate(String $tab_mail_mailaccountt_id_operate){
		this._tab_mail_mailaccountt_id_operate = $tab_mail_mailaccountt_id_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _tab_mail_mailaccountt_uuid;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_mailaccountt_uuid(){
		return this._tab_mail_mailaccountt_uuid;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_mailaccountt_uuid(String $tab_mail_mailaccountt_uuid){
		this._tab_mail_mailaccountt_uuid = $tab_mail_mailaccountt_uuid;
	}
	
	/**
	 * @generated
	 **/
	private String _tab_mail_mailaccountt_uuid_operate;
	
	/**
	 * @generated
	 **/
	public String getTab_mail_mailaccountt_uuid_operate(){
		return this._tab_mail_mailaccountt_uuid_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setTab_mail_mailaccountt_uuid_operate(String $tab_mail_mailaccountt_uuid_operate){
		this._tab_mail_mailaccountt_uuid_operate = $tab_mail_mailaccountt_uuid_operate;
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
	
	
	
	
}