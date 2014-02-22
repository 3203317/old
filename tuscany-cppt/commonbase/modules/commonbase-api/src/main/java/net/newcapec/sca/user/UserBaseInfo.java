package net.newcapec.sca.user;

import net.newcapec.sca.result.ResultMsg;

/**
 *
 * @author huangxin
 *
 */
public class UserBaseInfo {

    private String account_id;
    private String code;
    private String user_unit_code;
    private String login_alias;
    private String sex;

    private String name;
    private String opassword;
    private String password;
    private String user_type_code;
    private String userPhotoId ;
    private ResultMsg resultMsg;

    public ResultMsg getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(ResultMsg resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getUserPhotoId() {
        return userPhotoId;
    }

    public void setUserPhotoId(String userPhotoId) {
        this.userPhotoId = userPhotoId;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_unit_code() {
        return user_unit_code;
    }

    public void setUser_unit_code(String user_unit_code) {
        this.user_unit_code = user_unit_code;
    }

    public String getLogin_alias() {
        return login_alias;
    }

    public void setLogin_alias(String login_alias) {
        this.login_alias = login_alias;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type_code() {
        return user_type_code;
    }

    public void setUser_type_code(String user_type_code) {
        this.user_type_code = user_type_code;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public String getCur_use_date() {
        return cur_use_date;
    }

    public void setCur_use_date(String cur_use_date) {
        this.cur_use_date = cur_use_date;
    }

    public String getNo_use_date() {
        return no_use_date;
    }

    public void setNo_use_date(String no_use_date) {
        this.no_use_date = no_use_date;
    }

    public String getActivation_type_code() {
        return activation_type_code;
    }

    public void setActivation_type_code(String activation_type_code) {
        this.activation_type_code = activation_type_code;
    }

    public String getActivation_date() {
        return activation_date;
    }

    public void setActivation_date(String activation_date) {
        this.activation_date = activation_date;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCust_skin() {
        return cust_skin;
    }

    public void setCust_skin(String cust_skin) {
        this.cust_skin = cust_skin;
    }

    public String getCreate_user_account_id() {
        return create_user_account_id;
    }

    public void setCreate_user_account_id(String create_user_account_id) {
        this.create_user_account_id = create_user_account_id;
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

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getOpassword() {
        return opassword;
    }

    public void setOpassword(String opassword) {
        this.opassword = opassword;
    }


    private String status_code;
    private String open_date;

    private String cur_use_date;
    private String no_use_date;
    private String activation_type_code;
    private String activation_date;
    private String dept_code;

    private String uuid;
    private String cust_skin;
    private String create_user_account_id;
    private String create_date;
    private String encryption_info;

    private String encryption_timestamp;
    private String record_status = "0";
    private String ver;

}
