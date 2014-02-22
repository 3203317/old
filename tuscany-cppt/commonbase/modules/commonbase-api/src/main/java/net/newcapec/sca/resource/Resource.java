package net.newcapec.sca.resource;

import net.newcapec.sca.result.ResultMsg;

public class Resource
{
	private Long code;
	private Long user_unit_code;
	private Long resource_type_code;
	private String name;
	private Long status;
	private String info;
	private Long domain_code;
	private Long sys_code;
	private Long super_code;
	private Long is_have_child;
	private Long is_enabled;
	private String small_icon_code;
	private String large_icon_code;
	private String url;
	private String uuid;
	private Long create_user_account_id;
	private String Date;
	private Long sortid;
	private Long ver;
	private String type = "resource";



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private ResultMsg resultMsg;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getUser_unit_code() {
		return user_unit_code;
	}

	public void setUser_unit_code(Long user_unit_code) {
		this.user_unit_code = user_unit_code;
	}

	public Long getResource_type_code() {
		return resource_type_code;
	}

	public void setResource_type_code(Long resource_type_code) {
		this.resource_type_code = resource_type_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Long getDomain_code() {
		return domain_code;
	}

	public void setDomain_code(Long domain_code) {
		this.domain_code = domain_code;
	}

	public Long getSys_code() {
		return sys_code;
	}

	public void setSys_code(Long sys_code) {
		this.sys_code = sys_code;
	}

	public Long getSuper_code() {
		return super_code;
	}

	public void setSuper_code(Long super_code) {
		this.super_code = super_code;
	}

	public Long getIs_have_child() {
		return is_have_child;
	}

	public void setIs_have_child(Long is_have_child) {
		this.is_have_child = is_have_child;
	}

	public Long getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Long is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getSmall_icon_code() {
		return small_icon_code;
	}

	public void setSmall_icon_code(String small_icon_code) {
		this.small_icon_code = small_icon_code;
	}

	public String getLarge_icon_code() {
		return large_icon_code;
	}

	public void setLarge_icon_code(String large_icon_code) {
		this.large_icon_code = large_icon_code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCreate_user_account_id() {
		return create_user_account_id;
	}

	public void setCreate_user_account_id(Long create_user_account_id) {
		this.create_user_account_id = create_user_account_id;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Long getSortid() {
		return sortid;
	}

	public void setSortid(Long sortid) {
		this.sortid = sortid;
	}

	public Long getVer() {
		return ver;
	}

	public void setVer(Long ver) {
		this.ver = ver;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}



}
