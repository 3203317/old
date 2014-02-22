package net.newcapec.sca.report;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class Report {

	private Integer code;
	private String name;
	private String memo;
	private String templet;
	private ResultMsg resultMsg; // 封装返回结果信息

	private Integer domain_code;
	private Integer unit_code;
	private String create_date;
	private Integer create_user_code;

	private String create_user_name;
	private Integer type;
	private String type_name;

	private Integer ds_code;
	private String ds_name;
	private String ds_type;

	private Integer report_ds_code;
	private Integer resource_code;


	public Integer getResource_code() {
		return resource_code;
	}

	public void setResource_code(Integer resource_code) {
		this.resource_code = resource_code;
	}

	public Integer getReport_ds_code() {
		return report_ds_code;
	}

	public void setReport_ds_code(Integer report_ds_code) {
		this.report_ds_code = report_ds_code;
	}

	public String getDs_type() {
		return ds_type;
	}

	public void setDs_type(String ds_type) {
		this.ds_type = ds_type;
	}

	public Integer getDs_code() {
		return ds_code;
	}

	public void setDs_code(Integer ds_code) {
		this.ds_code = ds_code;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getDs_name() {
		return ds_name;
	}

	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}

	public Integer getDomain_code() {
		return domain_code;
	}

	public void setDomain_code(Integer domain_code) {
		this.domain_code = domain_code;
	}

	public Integer getUnit_code() {
		return unit_code;
	}

	public void setUnit_code(Integer unit_code) {
		this.unit_code = unit_code;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public Integer getCreate_user_code() {
		return create_user_code;
	}

	public void setCreate_user_code(Integer create_user_code) {
		this.create_user_code = create_user_code;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

}
