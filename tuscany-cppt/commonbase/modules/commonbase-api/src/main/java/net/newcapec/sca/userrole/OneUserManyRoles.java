package net.newcapec.sca.userrole;

import net.newcapec.sca.result.ResultMsg;

public class OneUserManyRoles {
	private String account_id;

	private String user_name;

	private String userRoles;

	private ResultMsg resultMsg;

	private String dyn1;//sex
	private String dyn2;//工号，学号
	private String dyn3;
	private String dyn4;


	public String getDyn1() {
		return dyn1;
	}

	public void setDyn1(String dyn1) {
		this.dyn1 = dyn1;
	}

	public String getDyn2() {
		return dyn2;
	}

	public void setDyn2(String dyn2) {
		this.dyn2 = dyn2;
	}

	public String getDyn3() {
		return dyn3;
	}

	public void setDyn3(String dyn3) {
		this.dyn3 = dyn3;
	}

	public String getDyn4() {
		return dyn4;
	}

	public void setDyn4(String dyn4) {
		this.dyn4 = dyn4;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}



}
