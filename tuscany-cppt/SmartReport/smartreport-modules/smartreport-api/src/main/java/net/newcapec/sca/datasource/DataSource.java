package net.newcapec.sca.datasource;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class DataSource {
	private Integer code;
	private Integer domain_code;
	private Integer unit_code;

	private String name;
	private String type;
	private String type_text;

	private Integer bind_type;
	private String service;
	private Integer dbconn_code;

	private String method;
	private String param;
	private String memo;

	private String domain_code_text;
	private String unit_code_text;
	private String bind_type_text;
	private String dbconn_code_text;

	/**/
	private String db_account;
	private String db_password;
	private String ip;
	private String port;
	private String server_id;

	private Integer use_scope_type;
	private String use_scope_type_text;

	public String getUse_scope_type_text() {
		return use_scope_type_text;
	}

	public void setUse_scope_type_text(String use_scope_type_text) {
		this.use_scope_type_text = use_scope_type_text;
	}

	public Integer getUse_scope_type() {
		return use_scope_type;
	}

	public void setUse_scope_type(Integer use_scope_type) {
		this.use_scope_type = use_scope_type;
	}

	public String getDb_account() {
		return db_account;
	}

	public void setDb_account(String db_account) {
		this.db_account = db_account;
	}

	public String getDb_password() {
		return db_password;
	}

	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getServer_id() {
		return server_id;
	}

	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}

	public String getDomain_code_text() {
		return domain_code_text;
	}

	public void setDomain_code_text(String domain_code_text) {
		this.domain_code_text = domain_code_text;
	}

	public String getUnit_code_text() {
		return unit_code_text;
	}

	public void setUnit_code_text(String unit_code_text) {
		this.unit_code_text = unit_code_text;
	}

	public String getBind_type_text() {
		return bind_type_text;
	}

	public void setBind_type_text(String bind_type_text) {
		this.bind_type_text = bind_type_text;
	}

	public String getDbconn_code_text() {
		return dbconn_code_text;
	}

	public void setDbconn_code_text(String dbconn_code_text) {
		this.dbconn_code_text = dbconn_code_text;
	}

	public String getType_text() {
		return type_text;
	}

	public void setType_text(String type_text) {
		this.type_text = type_text;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBind_type() {
		return bind_type;
	}

	public void setBind_type(Integer bind_type) {
		this.bind_type = bind_type;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getDbconn_code() {
		return dbconn_code;
	}

	public void setDbconn_code(Integer dbconn_code) {
		this.dbconn_code = dbconn_code;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
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
