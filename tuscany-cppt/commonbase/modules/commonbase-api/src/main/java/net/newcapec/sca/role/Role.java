package net.newcapec.sca.role;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>
 * Title: Role
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class Role {

	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	private Integer code;
	private String name;
	private Integer user_unit_code;
	private Integer domain_code;
	private Integer max_user_number;
	private Integer max_group_number;
	private Integer enabled_user_number;
	private Integer enabled_group_number;
	private Integer enabled_inherit;
	private Integer create_user_account_id;
	private String create_date;
	private String ver;

	private String user_unit_code_text;
	private String domain_code_text;
	private String enabled_inherit_text;
	private String create_user_account_id_text;

	private String enabled_user_number_text;
	private String enabled_group_number_text;




	public String getEnabled_user_number_text() {
		return enabled_user_number_text;
	}

	public void setEnabled_user_number_text(String enabled_user_number_text) {
		this.enabled_user_number_text = enabled_user_number_text;
	}

	public String getEnabled_group_number_text() {
		return enabled_group_number_text;
	}

	public void setEnabled_group_number_text(String enabled_group_number_text) {
		this.enabled_group_number_text = enabled_group_number_text;
	}

	public String getUser_unit_code_text() {
		return user_unit_code_text;
	}

	public void setUser_unit_code_text(String user_unit_code_text) {
		this.user_unit_code_text = user_unit_code_text;
	}

	public String getDomain_code_text() {
		return domain_code_text;
	}

	public void setDomain_code_text(String domain_code_text) {
		this.domain_code_text = domain_code_text;
	}

	public String getEnabled_inherit_text() {
		return enabled_inherit_text;
	}

	public void setEnabled_inherit_text(String enabled_inherit_text) {
		this.enabled_inherit_text = enabled_inherit_text;
	}

	public String getCreate_user_account_id_text() {
		return create_user_account_id_text;
	}

	public void setCreate_user_account_id_text(String create_user_account_id_text) {
		this.create_user_account_id_text = create_user_account_id_text;
	}

	public Integer getCode(){
		return code;
	}

	public void setCode(Integer code){
		this.code = code;
	}



	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}



	public Integer getUser_unit_code(){
		return user_unit_code;
	}

	public void setUser_unit_code(Integer user_unit_code){
		this.user_unit_code = user_unit_code;
	}



	public Integer getDomain_code(){
		return domain_code;
	}

	public void setDomain_code(Integer domain_code){
		this.domain_code = domain_code;
	}



	public Integer getMax_user_number(){
		return max_user_number;
	}

	public void setMax_user_number(Integer max_user_number){
		this.max_user_number = max_user_number;
	}



	public Integer getMax_group_number(){
		return max_group_number;
	}

	public void setMax_group_number(Integer max_group_number){
		this.max_group_number = max_group_number;
	}



	public Integer getEnabled_user_number(){
		return enabled_user_number;
	}

	public void setEnabled_user_number(Integer enabled_user_number){
		this.enabled_user_number = enabled_user_number;
	}



	public Integer getEnabled_group_number(){
		return enabled_group_number;
	}

	public void setEnabled_group_number(Integer enabled_group_number){
		this.enabled_group_number = enabled_group_number;
	}



	public Integer getEnabled_inherit(){
		return enabled_inherit;
	}

	public void setEnabled_inherit(Integer enabled_inherit){
		this.enabled_inherit = enabled_inherit;
	}



	public Integer getCreate_user_account_id(){
		return create_user_account_id;
	}

	public void setCreate_user_account_id(Integer create_user_account_id){
		this.create_user_account_id = create_user_account_id;
	}



	public String getCreate_date(){
		return create_date;
	}

	public void setCreate_date(String create_date){
		this.create_date = create_date;
	}



	public String getVer(){
		return ver;
	}

	public void setVer(String ver){
		this.ver = ver;
	}



}