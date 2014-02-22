package net.newcapec.sca.userrole;

import net.newcapec.sca.result.ResultMsg;
public class RoleInfo_Unit{
	private String userrole_code;
	private String role_code;
	private String user_name;
	private String account_id;
	private String role_name;
	public String getUserrole_code() {
		return userrole_code;
	}

	public void setUserrole_code(String userrole_code) {
		this.userrole_code = userrole_code;
	}
	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
}
