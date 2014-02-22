package net.newcapec.sca.diccode;

import net.newcapec.sca.result.ResultMsg;

public class DicTable {
	private Integer code; //编号
	private String name; //编码字典名称英文标示，如SEX
	private String name_alias; //编码字典别名中文描述，如性别
	private String info;//编码字典表描述
	private Integer create_user_account_id;//创建者用户账号
	private String create_date;//创建时间
	private Integer sortid;//排序字段
	private Integer ver;//版本号
	private ResultMsg resultMsg;//封装消息结果



	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_alias() {
		return name_alias;
	}
	public void setName_alias(String name_alias) {
		this.name_alias = name_alias;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getCreate_user_account_id() {
		return create_user_account_id;
	}
	public void setCreate_user_account_id(Integer create_user_account_id) {
		this.create_user_account_id = create_user_account_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

}
