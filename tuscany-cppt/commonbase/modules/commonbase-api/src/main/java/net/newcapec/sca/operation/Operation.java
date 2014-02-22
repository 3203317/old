package net.newcapec.sca.operation;

import net.newcapec.sca.result.ResultMsg;
public class Operation{
	private String code;
	private String user_unit_code;
	private String uuid;
	private String name;
	private String resource_type_code;
	private String info;
	private String create_user_account_id;
	private String create_date;
	private String sortid;
	private String ver;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getUser_unit_code() {
		return user_unit_code;
	}

	public void setUser_unit_code(String user_unit_code) {
		this.user_unit_code = user_unit_code;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getResource_type_code() {
		return resource_type_code;
	}

	public void setResource_type_code(String resource_type_code) {
		this.resource_type_code = resource_type_code;
	}
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
	public String getSortid() {
		return sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
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
