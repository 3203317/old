package net.newcapec.sca.dbconn;

import net.newcapec.sca.result.ResultMsg;

public class DBConnection
{
	private Long  code;
	private Long  domain_code;
	private Long  unit_code;
	private String name;
	private String 	configName;
	private String  dataBaseName;
	private String 	connectIP ;
	private String 	connectPort ;
	private String 	connectSID ;
	private String 	accoutName ;
	private String 	accountPassword ;
	private String 	marker;
	private String  memo;

	private String create_date;

	private String encryption_info;
	private String encryption_timestamp;
	private Long ver;
	private String create_user_code;


	private ResultMsg resultMsg;



	public String getCreate_user_code() {
		return create_user_code;
	}
	public void setCreate_user_code(String create_user_code) {
		this.create_user_code = create_user_code;
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
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getConnectIP() {
		return connectIP;
	}
	public void setConnectIP(String connectIP) {
		this.connectIP = connectIP;
	}
	public String getConnectPort() {
		return connectPort;
	}
	public void setConnectPort(String connectPort) {
		this.connectPort = connectPort;
	}
	public String getConnectSID() {
		return connectSID;
	}
	public void setConnectSID(String connectSID) {
		this.connectSID = connectSID;
	}
	public String getAccoutName() {
		return accoutName;
	}
	public void setAccoutName(String accoutName) {
		this.accoutName = accoutName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Long getDomain_code() {
		return domain_code;
	}
	public void setDomain_code(Long domain_code) {
		this.domain_code = domain_code;
	}
	public Long getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(Long unit_code) {
		this.unit_code = unit_code;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getEncryption_info() {
		return encryption_info;
	}
	public void setEncryption_info(String encryption_info) {
		this.encryption_info = encryption_info;
	}
	public String getEncryption_timestamp() {
		return encryption_timestamp;
	}
	public void setEncryption_timestamp(String encryption_timestamp) {
		this.encryption_timestamp = encryption_timestamp;
	}
	public Long getVer() {
		return ver;
	}
	public void setVer(Long ver) {
		this.ver = ver;
	}

}
