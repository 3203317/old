package net.newcapec.sca.userrole;

import net.newcapec.sca.result.ResultMsg;
public class UserRole{
	private String code;
	private String account_id;
	private String role_code;
	private String create_user_account_id;
	private String create_date;
	private String ver;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getCreate_user_account_id() {
		return create_user_account_id;
	}

	public void setCreate_user_account_id(String create_user_account_id) {
		this.create_user_account_id = create_user_account_id;
	}
	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}
	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
}
