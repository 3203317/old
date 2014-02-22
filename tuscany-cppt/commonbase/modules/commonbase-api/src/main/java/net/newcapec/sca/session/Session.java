package net.newcapec.sca.session;


/**
 *
 *接口描述：session相关信息
 *@author: 赵鹏飞
 *@date： 日期：2012-9-10 时间：上午11:03:54
 *@version 1.0
 *修改人：
 *修改时间：
 *修改备注：
 */
public class Session {
	private String id;//会话id
	private String user_code;//用户编号
	private String age;//超时间隔
	private String update_time;//最后访问时间
	private String content;//附加会话信息
	private String domain_code;//域编号
	private String unit_code;//客户单位编号
	private String invalid_date;//失效时间
	//状态标示，如果是curd操作，1代表成功，0代表失败
	//如果是会话状态，1为未失效状态。   0为失效状态，并且session id为null
	private int state;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDomain_code() {
		return domain_code;
	}
	public void setDomain_code(String domain_code) {
		this.domain_code = domain_code;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	public String getInvalid_date() {
		return invalid_date;
	}
	public void setInvalid_date(String invalid_date) {
		this.invalid_date = invalid_date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
