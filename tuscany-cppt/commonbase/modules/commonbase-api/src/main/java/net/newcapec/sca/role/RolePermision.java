package net.newcapec.sca.role;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>
 * Title: Rolepermision
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
public class RolePermision {

	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	private Integer code;
	private Integer role_code;
	private Integer permission_code;
	private Integer create_user_account_id;
	private String create_date;
	private String ver;

	private String role_name;
	private String permission_name;



	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getPermission_name() {
		return permission_name;
	}

	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}

	public Integer getCode(){
		return code;
	}

	public void setCode(Integer code){
		this.code = code;
	}



	public Integer getRole_code(){
		return role_code;
	}

	public void setRole_code(Integer role_code){
		this.role_code = role_code;
	}



	public Integer getPermission_code(){
		return permission_code;
	}

	public void setPermission_code(Integer permission_code){
		this.permission_code = permission_code;
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