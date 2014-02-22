package net.foreworld.java.sysmanage.sysmanage_user;

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
public class Sysmanage_user extends BaseModel {
	
	private String _fields = "id,uuid,user_code,user_name,password,lpassword,realname,sex,country,province,city,birthday,last_logintime,last_logouttime,isrememberpassword,isautologin,addtime,opt_sysmanage_user_id,availsign,startusing";
	
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

	private String _detailTablesName = "sysmanage_user_role,sysmanage_user_station";
	
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
	private String _user_code;
	
	/**
	 * @generated
	 **/
	public String getUser_code(){
		return this._user_code;
	}	
	
	/**
	 * @generated
	 **/
	public void setUser_code(String $user_code){
		this._user_code = $user_code;
	}
	
	/**
	 * @generated
	 **/
	private String _user_code_operate;
	
	/**
	 * @generated
	 **/
	public String getUser_code_operate(){
		return this._user_code_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setUser_code_operate(String $user_code_operate){
		this._user_code_operate = $user_code_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _user_name;
	
	/**
	 * @generated
	 **/
	public String getUser_name(){
		return this._user_name;
	}	
	
	/**
	 * @generated
	 **/
	public void setUser_name(String $user_name){
		this._user_name = $user_name;
	}
	
	/**
	 * @generated
	 **/
	private String _user_name_operate;
	
	/**
	 * @generated
	 **/
	public String getUser_name_operate(){
		return this._user_name_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setUser_name_operate(String $user_name_operate){
		this._user_name_operate = $user_name_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _password;
	
	/**
	 * @generated
	 **/
	public String getPassword(){
		return this._password;
	}	
	
	/**
	 * @generated
	 **/
	public void setPassword(String $password){
		this._password = $password;
	}
	
	/**
	 * @generated
	 **/
	private String _password_operate;
	
	/**
	 * @generated
	 **/
	public String getPassword_operate(){
		return this._password_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setPassword_operate(String $password_operate){
		this._password_operate = $password_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _lpassword;
	
	/**
	 * @generated
	 **/
	public String getLpassword(){
		return this._lpassword;
	}	
	
	/**
	 * @generated
	 **/
	public void setLpassword(String $lpassword){
		this._lpassword = $lpassword;
	}
	
	/**
	 * @generated
	 **/
	private String _lpassword_operate;
	
	/**
	 * @generated
	 **/
	public String getLpassword_operate(){
		return this._lpassword_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setLpassword_operate(String $lpassword_operate){
		this._lpassword_operate = $lpassword_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _realname;
	
	/**
	 * @generated
	 **/
	public String getRealname(){
		return this._realname;
	}	
	
	/**
	 * @generated
	 **/
	public void setRealname(String $realname){
		this._realname = $realname;
	}
	
	/**
	 * @generated
	 **/
	private String _realname_operate;
	
	/**
	 * @generated
	 **/
	public String getRealname_operate(){
		return this._realname_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setRealname_operate(String $realname_operate){
		this._realname_operate = $realname_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _sex;
	
	/**
	 * @generated
	 **/
	public Integer getSex(){
		return this._sex;
	}	
	
	/**
	 * @generated
	 **/
	public void setSex(Integer $sex){
		this._sex = $sex;
	}
	
	/**
	 * @generated
	 **/
	private String _sex_operate;
	
	/**
	 * @generated
	 **/
	public String getSex_operate(){
		return this._sex_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setSex_operate(String $sex_operate){
		this._sex_operate = $sex_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _country;
	
	/**
	 * @generated
	 **/
	public Integer getCountry(){
		return this._country;
	}	
	
	/**
	 * @generated
	 **/
	public void setCountry(Integer $country){
		this._country = $country;
	}
	
	/**
	 * @generated
	 **/
	private String _country_operate;
	
	/**
	 * @generated
	 **/
	public String getCountry_operate(){
		return this._country_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setCountry_operate(String $country_operate){
		this._country_operate = $country_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _province;
	
	/**
	 * @generated
	 **/
	public Integer getProvince(){
		return this._province;
	}	
	
	/**
	 * @generated
	 **/
	public void setProvince(Integer $province){
		this._province = $province;
	}
	
	/**
	 * @generated
	 **/
	private String _province_operate;
	
	/**
	 * @generated
	 **/
	public String getProvince_operate(){
		return this._province_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setProvince_operate(String $province_operate){
		this._province_operate = $province_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _city;
	
	/**
	 * @generated
	 **/
	public Integer getCity(){
		return this._city;
	}	
	
	/**
	 * @generated
	 **/
	public void setCity(Integer $city){
		this._city = $city;
	}
	
	/**
	 * @generated
	 **/
	private String _city_operate;
	
	/**
	 * @generated
	 **/
	public String getCity_operate(){
		return this._city_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setCity_operate(String $city_operate){
		this._city_operate = $city_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private String _birthday;
	
	/**
	 * @generated
	 **/
	public String getBirthday(){
		return this._birthday;
	}	
	
	/**
	 * @generated
	 **/
	public void setBirthday(String $birthday){
		this._birthday = $birthday;
	}
	
	/**
	 * @generated
	 **/
	private String _birthday_operate;
	
	/**
	 * @generated
	 **/
	public String getBirthday_operate(){
		return this._birthday_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setBirthday_operate(String $birthday_operate){
		this._birthday_operate = $birthday_operate;
	}
	

	private String _birthday_startdate;
	
	public String getBirthday_startdate(){
		return this._birthday_startdate;
	}
	
	public void setBirthday_startdate(String $birthday_startdate){
		this._birthday_startdate = $birthday_startdate;
	}

	private String _birthday_enddate;
	
	public String getBirthday_enddate(){
		return this._birthday_enddate;
	}
	
	public void setBirthday_enddate(String $birthday_enddate){
		this._birthday_enddate = $birthday_enddate;
	}	
		
	
	/**
	 * @generated
	 **/
	private String _last_logintime;
	
	/**
	 * @generated
	 **/
	public String getLast_logintime(){
		return this._last_logintime;
	}	
	
	/**
	 * @generated
	 **/
	public void setLast_logintime(String $last_logintime){
		this._last_logintime = $last_logintime;
	}
	
	/**
	 * @generated
	 **/
	private String _last_logintime_operate;
	
	/**
	 * @generated
	 **/
	public String getLast_logintime_operate(){
		return this._last_logintime_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setLast_logintime_operate(String $last_logintime_operate){
		this._last_logintime_operate = $last_logintime_operate;
	}
	

	private String _last_logintime_startdate;
	
	public String getLast_logintime_startdate(){
		return this._last_logintime_startdate;
	}
	
	public void setLast_logintime_startdate(String $last_logintime_startdate){
		this._last_logintime_startdate = $last_logintime_startdate;
	}

	private String _last_logintime_enddate;
	
	public String getLast_logintime_enddate(){
		return this._last_logintime_enddate;
	}
	
	public void setLast_logintime_enddate(String $last_logintime_enddate){
		this._last_logintime_enddate = $last_logintime_enddate;
	}	
		
	
	/**
	 * @generated
	 **/
	private String _last_logouttime;
	
	/**
	 * @generated
	 **/
	public String getLast_logouttime(){
		return this._last_logouttime;
	}	
	
	/**
	 * @generated
	 **/
	public void setLast_logouttime(String $last_logouttime){
		this._last_logouttime = $last_logouttime;
	}
	
	/**
	 * @generated
	 **/
	private String _last_logouttime_operate;
	
	/**
	 * @generated
	 **/
	public String getLast_logouttime_operate(){
		return this._last_logouttime_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setLast_logouttime_operate(String $last_logouttime_operate){
		this._last_logouttime_operate = $last_logouttime_operate;
	}
	

	private String _last_logouttime_startdate;
	
	public String getLast_logouttime_startdate(){
		return this._last_logouttime_startdate;
	}
	
	public void setLast_logouttime_startdate(String $last_logouttime_startdate){
		this._last_logouttime_startdate = $last_logouttime_startdate;
	}

	private String _last_logouttime_enddate;
	
	public String getLast_logouttime_enddate(){
		return this._last_logouttime_enddate;
	}
	
	public void setLast_logouttime_enddate(String $last_logouttime_enddate){
		this._last_logouttime_enddate = $last_logouttime_enddate;
	}	
		
	
	/**
	 * @generated
	 **/
	private Integer _isrememberpassword;
	
	/**
	 * @generated
	 **/
	public Integer getIsrememberpassword(){
		return this._isrememberpassword;
	}	
	
	/**
	 * @generated
	 **/
	public void setIsrememberpassword(Integer $isrememberpassword){
		this._isrememberpassword = $isrememberpassword;
	}
	
	/**
	 * @generated
	 **/
	private String _isrememberpassword_operate;
	
	/**
	 * @generated
	 **/
	public String getIsrememberpassword_operate(){
		return this._isrememberpassword_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setIsrememberpassword_operate(String $isrememberpassword_operate){
		this._isrememberpassword_operate = $isrememberpassword_operate;
	}
	
	
	/**
	 * @generated
	 **/
	private Integer _isautologin;
	
	/**
	 * @generated
	 **/
	public Integer getIsautologin(){
		return this._isautologin;
	}	
	
	/**
	 * @generated
	 **/
	public void setIsautologin(Integer $isautologin){
		this._isautologin = $isautologin;
	}
	
	/**
	 * @generated
	 **/
	private String _isautologin_operate;
	
	/**
	 * @generated
	 **/
	public String getIsautologin_operate(){
		return this._isautologin_operate;
	}	
	
	/**
	 * @generated
	 **/
	public void setIsautologin_operate(String $isautologin_operate){
		this._isautologin_operate = $isautologin_operate;
	}
	
	
	
	
}