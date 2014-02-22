package net.newcapec.sca.reportds;

import net.newcapec.sca.result.ResultMsg;

/**
 * 报表数据源
 *
 * @author huangxin
 *
 */
public class ReportDs {

	private Integer code;
	private Integer report_code;
	private Integer ds_code;
	private String memo;

	private String ds_name;
	private String ds_type;
	private String rds_id;
	private ResultMsg resultMsg; // 封装返回结果信息

	private Integer type = 2;// 2为辅数据源

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDs_name() {
		return ds_name;
	}

	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}

	public String getDs_type() {
		return ds_type;
	}

	public void setDs_type(String ds_type) {
		this.ds_type = ds_type;
	}

	public String getRds_id() {
		return rds_id;
	}

	public void setRds_id(String rds_id) {
		this.rds_id = rds_id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getReport_code() {
		return report_code;
	}

	public void setReport_code(Integer report_code) {
		this.report_code = report_code;
	}

	public Integer getDs_code() {
		return ds_code;
	}

	public void setDs_code(Integer ds_code) {
		this.ds_code = ds_code;
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
