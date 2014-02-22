package net.newcapec.sca.reportdsfield;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class ReportDsField {

	private Integer report_ds_code;

	private String field_name;
	private String field_type;
	private String title;
	private Integer sequence;
	private Boolean ishow = false;
	private ResultMsg resultMsg; // 封装返回结果信息

	private Integer ds_code;

	public Integer getDs_code() {
		return ds_code;
	}

	public void setDs_code(Integer ds_code) {
		this.ds_code = ds_code;
	}

	public Integer getReport_ds_code() {
		return report_ds_code;
	}

	public void setReport_ds_code(Integer report_ds_code) {
		this.report_ds_code = report_ds_code;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_type() {
		return field_type;
	}

	public void setField_type(String field_type) {
		this.field_type = field_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Boolean getIshow() {
		return ishow;
	}

	public void setIshow(Boolean ishow) {
		this.ishow = ishow;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

}
