package net.newcapec.sca.reportpublish;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>Title: 业务实体类 </p>
 * <p>Description:报表发布实体类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-15
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public class ReportPublish {

    private   Integer	code;  //编号
	private   String    name;  //报表或表单名称
	private   String    memo;  //备注
	private   Integer   type;  //类型
	private   String    type_name;//类型名称  报表或表单
	private   Integer   resource_code;//0未发布  ,大于等于1为资源code
	private   String    publish;//是否发布中文名称
	private   String    menu_name; //菜单名称
	private   Integer   user_account_id; //发布用户id
	private   Integer   grant_permission; //是否给所在用户授权
	private   String    url;          //报表或表单访问地址
	private ResultMsg resultMsg ; //封装消息结果

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
	public Integer getResource_code() {
		return resource_code;
	}
	public void setResource_code(Integer resource_code) {
		this.resource_code = resource_code;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public Integer getUser_account_id() {
		return user_account_id;
	}
	public void setUser_account_id(Integer user_account_id) {
		this.user_account_id = user_account_id;
	}
	public Integer getGrant_permission() {
		return grant_permission;
	}
	public void setGrant_permission(Integer grant_permission) {
		this.grant_permission = grant_permission;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
