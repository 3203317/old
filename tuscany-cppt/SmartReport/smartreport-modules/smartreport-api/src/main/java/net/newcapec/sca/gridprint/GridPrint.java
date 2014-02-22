package net.newcapec.sca.gridprint;

import net.newcapec.sca.result.ResultMsg;

public class GridPrint {
	private String code;
	private String form_code;
	private String temp_code;
	private String print_parm;

	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getForm_code() {
		return form_code;
	}
	public void setForm_code(String from_code) {
		this.form_code = from_code;
	}
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	public String getPrint_parm() {
		return print_parm;
	}
	public void setPrint_parm(String print_parm) {
		this.print_parm = print_parm;
	}


}
