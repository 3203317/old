package net.newcapec.sca.gridview;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class GridView {

	private Integer code;
	private Integer form_code;

	private String toolbuttons;
	private String condition_groups;
	private String condition_trees;
	private String fields;

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

	public String getToolbuttons() {
		return toolbuttons;
	}

	public void setToolbuttons(String toolbuttons) {
		this.toolbuttons = toolbuttons;
	}

	public String getCondition_groups() {
		return condition_groups;
	}

	public void setCondition_groups(String condition_groups) {
		this.condition_groups = condition_groups;
	}

	public String getCondition_trees() {
		return condition_trees;
	}

	public void setCondition_trees(String condition_trees) {
		this.condition_trees = condition_trees;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

}
