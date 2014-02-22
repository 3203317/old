package net.newcapec.sca.gridcondition;

import net.newcapec.sca.result.ResultMsg;

public class GridCondition {
	private String code;
	private String form_code;
	private String field_name;//字段code
	private String relation;
	private String parent_condition;
	private String group_name;
	private String type;
	private String default_value;
	private String condition_name;
	private String field_name_value;//字段中文名
	private String parent_condition_value;
	private String field_name_name;//字段英文
	private String field_input_type;//字段输入类型 如：select，input，number，date 等
	//private String state;//zpf add 更新、添加状态标识
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
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getParent_condition() {
		return parent_condition;
	}
	public void setParent_condition(String parent_condition) {
		this.parent_condition = parent_condition;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getType() {
		return type;
	}
	public String getCondition_name() {
		return condition_name;
	}
	public void setCondition_name(String condition_name) {
		this.condition_name = condition_name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
	public String getField_name_value() {
		return field_name_value;
	}
	public void setField_name_value(String field_name_value) {
		this.field_name_value = field_name_value;
	}
	public String getParent_condition_value() {
		return parent_condition_value;
	}
	public void setParent_condition_value(String parent_condition_value) {
		this.parent_condition_value = parent_condition_value;
	}
	public String getField_name_name() {
		return field_name_name;
	}
	public void setField_name_name(String field_name_name) {
		this.field_name_name = field_name_name;
	}
	public String getField_input_type() {
		return field_input_type;
	}
	public void setField_input_type(String field_input_type) {
		this.field_input_type = field_input_type;
	}

}
