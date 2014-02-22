package net.newcapec.sca.gridcust;

/**
 * <p>Title: 业务实体类 </p>
 * <p>Description:查询浏览列表用户自定义配置业务实体类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import net.newcapec.sca.result.ResultMsg;


public class GridCustom {

	private  Integer   code ; //编号
	private  Integer   user_code; //用户编号
	private  Integer   form_code; //表单编号
    private  String    custom_content; //JSON格式配置信息
    private ResultMsg  resultMsg; //封装返回结果信息


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getUser_code() {
		return user_code;
	}
	public void setUser_code(Integer user_code) {
		this.user_code = user_code;
	}
	public Integer getForm_code() {
		return form_code;
	}
	public void setForm_code(Integer form_code) {
		this.form_code = form_code;
	}

	public String getCustom_content() {
		return custom_content;
	}
	public void setCustom_content(String custom_content) {
		this.custom_content = custom_content;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}





}
