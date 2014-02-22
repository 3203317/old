package net.newcapec.sca.diccode;

import net.newcapec.sca.result.ResultMsg;

public class DicItem {
	private Integer code;//编码
	private String code_dictionary_name;//编码字典编号，Z_CODE_DICTIONARY表中的主键
	private String data_key;//数据KEY，存储数据key，如1
	private String data_value;//数据Value，存储数据描述，如男
	private String key_code;//数据编码key，存储国标或行业标准编号，如01等
	private String info;//描述信息
	private Integer create_user_account_id;//创建者用户账号
	private String create_date;//创建日期
	private Integer sortid;//排序字段
	private Integer ver;//版本号
	private ResultMsg resultMsg;


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCode_dictionary_name() {
		return code_dictionary_name;
	}
	public void setCode_dictionary_name(String code_dictionary_name) {
		this.code_dictionary_name = code_dictionary_name;
	}
	public String getData_key() {
		return data_key;
	}
	public void setData_key(String data_key) {
		this.data_key = data_key;
	}
	public String getData_value() {
		return data_value;
	}
	public void setData_value(String data_value) {
		this.data_value = data_value;
	}
	public String getKey_code() {
		return key_code;
	}
	public void setKey_code(String key_code) {
		this.key_code = key_code;
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
