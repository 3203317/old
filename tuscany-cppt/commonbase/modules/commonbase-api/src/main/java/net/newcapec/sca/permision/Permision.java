package net.newcapec.sca.permision;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>
 * Title: Permision
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
public class Permision {

	private ResultMsg resultMsg;

	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	private Integer code;
	private String name;
	private Integer user_unit_code;
	private String info;
	private Integer sys_code;
	private Integer operate_code;
	private Integer resource_code;
	private Integer is_enabled;
	private Integer is_visible;
	private Integer create_user_account_id;
	private String create_date;
	private String ver;

	private String user_unit_code_text;
	private String sys_code_text;
	private String operate_code_text;
	private String resource_code_text;
	private String is_enabled_text;
	private String is_visible_text;
	private String create_user_account_id_text;

	public String getUser_unit_code_text() {
		return user_unit_code_text;
	}

	public void setUser_unit_code_text(String user_unit_code_text) {
		this.user_unit_code_text = user_unit_code_text;
	}

	public String getSys_code_text() {
		return sys_code_text;
	}

	public void setSys_code_text(String sys_code_text) {
		this.sys_code_text = sys_code_text;
	}

	public String getOperate_code_text() {
		return operate_code_text;
	}

	public void setOperate_code_text(String operate_code_text) {
		this.operate_code_text = operate_code_text;
	}

	public String getResource_code_text() {
		return resource_code_text;
	}

	public void setResource_code_text(String resource_code_text) {
		this.resource_code_text = resource_code_text;
	}

	public String getIs_enabled_text() {
		return is_enabled_text;
	}

	public void setIs_enabled_text(String is_enabled_text) {
		this.is_enabled_text = is_enabled_text;
	}

	public String getIs_visible_text() {
		return is_visible_text;
	}

	public void setIs_visible_text(String is_visible_text) {
		this.is_visible_text = is_visible_text;
	}

	public String getCreate_user_account_id_text() {
		return create_user_account_id_text;
	}

	public void setCreate_user_account_id_text(
			String create_user_account_id_text) {
		this.create_user_account_id_text = create_user_account_id_text;
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

	public Integer getUser_unit_code() {
		return user_unit_code;
	}

	public void setUser_unit_code(Integer user_unit_code) {
		this.user_unit_code = user_unit_code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getSys_code() {
		return sys_code;
	}

	public void setSys_code(Integer sys_code) {
		this.sys_code = sys_code;
	}

	public Integer getOperate_code() {
		return operate_code;
	}

	public void setOperate_code(Integer operate_code) {
		this.operate_code = operate_code;
	}

	public Integer getResource_code() {
		return resource_code;
	}

	public void setResource_code(Integer resource_code) {
		this.resource_code = resource_code;
	}

	public Integer getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Integer is_enabled) {
		this.is_enabled = is_enabled;
	}

	public Integer getIs_visible() {
		return is_visible;
	}

	public void setIs_visible(Integer is_visible) {
		this.is_visible = is_visible;
	}

	public Integer getCreate_user_account_id() {
		return create_user_account_id;
	}

	public void setCreate_user_account_id(Integer create_user_account_id) {
		this.create_user_account_id = create_user_account_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

}