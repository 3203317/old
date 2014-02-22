package net.newcapec.sca.user;

import net.newcapec.sca.result.ResultMsg;
public class UserPhoto{
    private String code;
    private String account_id;
    private String user_unit_code;
    private String cust_code;
    private String photo_no;
    private byte[] photo;
    private String photo_file_path;
    private String create_user_account_id;
    private String create_date;
    private String ver;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public String getUser_unit_code() {
        return user_unit_code;
    }

    public void setUser_unit_code(String user_unit_code) {
        this.user_unit_code = user_unit_code;
    }
    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }
    public String getPhoto_no() {
        return photo_no;
    }

    public void setPhoto_no(String photo_no) {
        this.photo_no = photo_no;
    }
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public String getPhoto_file_path() {
        return photo_file_path;
    }

    public void setPhoto_file_path(String photo_file_path) {
        this.photo_file_path = photo_file_path;
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
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
    private ResultMsg resultMsg;

    public ResultMsg getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(ResultMsg resultMsg) {
        this.resultMsg = resultMsg;
    }
}
