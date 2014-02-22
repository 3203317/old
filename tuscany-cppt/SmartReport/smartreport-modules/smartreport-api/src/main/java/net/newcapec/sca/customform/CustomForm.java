package net.newcapec.sca.customform;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class CustomForm {

	private Integer code;
	private Integer domain_code;
	private Integer unit_code;
	private String name;
	private int type;

	private Integer ds_code;
	private String create_date;
	private Integer create_user_code;
	private String memo;

	private ResultMsg resultMsg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getDs_code() {
		return ds_code;
	}

	public void setDs_code(Integer ds_code) {
		this.ds_code = ds_code;
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
