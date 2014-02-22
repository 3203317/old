package net.newcapec.sca.org;

import net.newcapec.sca.result.ResultMsg;
public class Organization{
	private String code;
	private String user_unit_code;
	private String name;
	private String alias;
	private String short_hand;
	private String status;
	private String super_code;
	private String type_code;
	private String address;
	private String is_have_child;
	private String department_level;
	private String code_path;
	private String batch_code;
	private String no_use_date;
	private String open_date;
	private String cur_use_date;
	private String uuid;
	private String create_user_account_id;
	private String create_date;
	private String sortid;
	private String ver;

	private String type = "department";


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getShort_hand() {
		return short_hand;
	}

	public void setShort_hand(String short_hand) {
		this.short_hand = short_hand;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuper_code() {
		return super_code;
	}

	public void setSuper_code(String super_code) {
		this.super_code = super_code;
	}
	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getIs_have_child() {
		return is_have_child;
	}

	public void setIs_have_child(String is_have_child) {
		this.is_have_child = is_have_child;
	}
	public String getDepartment_level() {
		return department_level;
	}

	public void setDepartment_level(String department_level) {
		this.department_level = department_level;
	}
	public String getCode_path() {
		return code_path;
	}

	public void setCode_path(String code_path) {
		this.code_path = code_path;
	}
	public String getBatch_code() {
		return batch_code;
	}

	public void setBatch_code(String batch_code) {
		this.batch_code = batch_code;
	}
	public String getNo_use_date() {
		return no_use_date;
	}

	public void setNo_use_date(String no_use_date) {
		this.no_use_date = no_use_date;
	}
	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public String getCur_use_date() {
		return cur_use_date;
	}

	public void setCur_use_date(String cur_use_date) {
		this.cur_use_date = cur_use_date;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
