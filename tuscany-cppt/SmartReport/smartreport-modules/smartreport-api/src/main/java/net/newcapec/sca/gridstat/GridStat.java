package net.newcapec.sca.gridstat;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class GridStat {

	private Integer code;
	private Integer form_code;
	private Integer type;
	private String field_type_list;

	private ResultMsg resultMsg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getForm_code() {
		return form_code;
	}

	public void setForm_code(Integer form_code) {
		this.form_code = form_code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getField_type_list() {
		return field_type_list;
	}

	public void setField_type_list(String field_type_list) {
		this.field_type_list = field_type_list;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

}
