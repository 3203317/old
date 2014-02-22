package net.newcapec.sca.formlayout;

import net.newcapec.sca.result.ResultMsg;



public class FormLayout {

	private  Integer  code;//编号
	private  Integer  form_code;//表单编号
	private  Integer  custom_type;//定制类型（简单行列、模板）
	private  String   custom_content;//简单行列定制参数，JSON格式，包括行数、列数、顺序、标签宽度、内容文本宽度等
    private  String   temp_content;//定制模板信息
    private  Integer  print_temp_code;//打印模板编号
    private  ResultMsg  resultMsg;//封装返回结果信息

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
	public Integer getCustom_type() {
		return custom_type;
	}
	public void setCustom_type(Integer custom_type) {
		this.custom_type = custom_type;
	}
	public String getCustom_content() {
		return custom_content;
	}
	public void setCustom_content(String custom_content) {
		this.custom_content = custom_content;
	}
	public String getTemp_content() {
		return temp_content;
	}
	public void setTemp_content(String temp_content) {
		this.temp_content = temp_content;
	}
	public Integer getPrint_temp_code() {
		return print_temp_code;
	}
	public void setPrint_temp_code(Integer print_temp_code) {
		this.print_temp_code = print_temp_code;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}





}
