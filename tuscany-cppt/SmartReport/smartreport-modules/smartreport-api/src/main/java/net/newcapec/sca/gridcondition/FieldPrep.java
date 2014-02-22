package net.newcapec.sca.gridcondition;

import net.newcapec.sca.result.ResultMsg;
public class FieldPrep{
	private String code;
	private String ds_code;
	private String name;
	private String alias;
	private String type;
	private String input_type;
	private String regexp;
	private String memo;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getDs_code() {
		return ds_code;
	}

	public void setDs_code(String ds_code) {
		this.ds_code = ds_code;
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
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getInput_type() {
		return input_type;
	}

	public void setInput_type(String input_type) {
		this.input_type = input_type;
	}
	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
}
