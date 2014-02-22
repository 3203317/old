package net.newcapec.sca.reportdsparam;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class ReportDsParam {

	private Integer code;
	private String ds_type;
	private Integer report_ds_code;

	private String logical;
	private String field_name;
	private String relation;
	private String field_value;
	private String field_type;
	private ResultMsg resultMsg; // 封装返回结果信息

	private String label;
	private String input_type;
	private Integer widget_ds_code;
	private String widget_ds_label;
	private String widget_key;
	private String widget_value;
	private String widget_parent_field;
	private String widget_top_default;
	private String widget_type;
	private Integer widget_datasource_code;

	private String field_name_alias;

	public String getField_name_alias() {
		return field_name_alias;
	}

	public void setField_name_alias(String field_name_alias) {
		this.field_name_alias = field_name_alias;
	}

	public String getWidget_ds_label() {
		return widget_ds_label;
	}

	public void setWidget_ds_label(String widget_ds_label) {
		this.widget_ds_label = widget_ds_label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getInput_type() {
		return input_type;
	}

	public void setInput_type(String input_type) {
		this.input_type = input_type;
	}

	public Integer getWidget_ds_code() {
		return widget_ds_code;
	}

	public void setWidget_ds_code(Integer widget_ds_code) {
		this.widget_ds_code = widget_ds_code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDs_type() {
		return ds_type;
	}

	public void setDs_type(String ds_type) {
		this.ds_type = ds_type;
	}

	public Integer getReport_ds_code() {
		return report_ds_code;
	}

	public void setReport_ds_code(Integer report_ds_code) {
		this.report_ds_code = report_ds_code;
	}

	public String getLogical() {
		return logical;
	}

	public void setLogical(String logical) {
		this.logical = logical;
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

	public String getField_value() {
		return field_value;
	}

	public void setField_value(String field_value) {
		this.field_value = field_value;
	}

	public String getField_type() {
		return field_type;
	}

	public void setField_type(String field_type) {
		this.field_type = field_type;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getWidget_key() {
		return widget_key;
	}

	public void setWidget_key(String widget_key) {
		this.widget_key = widget_key;
	}

	public String getWidget_value() {
		return widget_value;
	}

	public void setWidget_value(String widget_value) {
		this.widget_value = widget_value;
	}

	public String getWidget_parent_field() {
		return widget_parent_field;
	}

	public void setWidget_parent_field(String widget_parent_field) {
		this.widget_parent_field = widget_parent_field;
	}

	public String getWidget_top_default() {
		return widget_top_default;
	}

	public void setWidget_top_default(String widget_top_default) {
		this.widget_top_default = widget_top_default;
	}

	public String getWidget_type() {
		return widget_type;
	}

	public void setWidget_type(String widget_type) {
		this.widget_type = widget_type;
	}

	public Integer getWidget_datasource_code() {
		return widget_datasource_code;
	}

	public void setWidget_datasource_code(Integer widget_datasource_code) {
		this.widget_datasource_code = widget_datasource_code;
	}

}
