package net.newcapec.sca.user.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.das.UserBaseInfoDAS;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class UserBaseInfoDASImpl implements UserBaseInfoDAS{

    private static final Logger userBaseDASLog = Logger.getLogger(UserBaseInfoDASImpl.class);

    private DefDBConnService defDBConnService;

    @Reference(name = "defDBConnService", required = true)
    public void setDefDBConnService(DefDBConnService defDBConnService) {
        this.defDBConnService = defDBConnService;
    }

    //默认的功能模块das配置文件
    private static String FUNCTIONCONFIGFILE = "/user.xml";
    public DAS getDAS()
    {
        String path = "";
        path = this.getClass().getResource(FUNCTIONCONFIGFILE).getPath();
        DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
        return das;
    }

    public UserBaseInfo getUserBaseInfoById(Integer id)
    {
        DAS das = this.getDAS();
        Command command = das.getCommand("getUserBaseInfoById");
        command.setParameter(1, id);
        DataObject root = command.executeQuery();
        List<DataObject> list = root.getList("p_user");
        DataObject p_userDO = null;
        UserBaseInfo userbaseinfo = null;
        if(null != list && list.size() > 0)
        {
            p_userDO = list.get(0);
            userbaseinfo = this.translateDOTOUserBaseInfo(p_userDO);
        }

        return userbaseinfo;
    }
    /**
     * 按照学工号查询
     * @param code
     * @return
     */
    public UserBaseInfo getUserBaseInfoByCode(String code)
    {
        DAS das = this.getDAS();
        Command command = das.getCommand("getUserBaseInfoByCode");
        command.setParameter(1, code);
        DataObject root = command.executeQuery();
        List<DataObject> list = root.getList("p_user");
        DataObject p_userDO = null;
        UserBaseInfo userbaseinfo = null;
        if(null != list && list.size() > 0)
        {
            p_userDO = list.get(0);
            userbaseinfo = this.translateDOTOUserBaseInfo(p_userDO);
        }
        return userbaseinfo;
    }
private UserBaseInfo translateDOTOUserBaseInfo(DataObject p_userDO)
{
    UserBaseInfo userbaseinfo = new UserBaseInfo();
    userbaseinfo.setAccount_id(null == p_userDO.get("account_id")?"":p_userDO.get("account_id").toString());
    userbaseinfo.setCode(null == p_userDO.get("code")?"":p_userDO.get("code").toString());
    userbaseinfo.setUser_unit_code(null == p_userDO.get("user_unit_code")?"":p_userDO.get("user_unit_code").toString());
    userbaseinfo.setLogin_alias(null == p_userDO.get("login_alias")?"":p_userDO.get("login_alias").toString());
    userbaseinfo.setSex(null == p_userDO.get("sex")?"":p_userDO.get("sex").toString());
    userbaseinfo.setName(null == p_userDO.get("name")?"":p_userDO.get("name").toString());
    userbaseinfo.setUser_type_code(null == p_userDO.get("user_type_code")?"":p_userDO.get("user_type_code").toString());
    userbaseinfo.setStatus_code(null == p_userDO.get("status_code")?"":p_userDO.get("status_code").toString());
    userbaseinfo.setOpen_date(null == p_userDO.get("open_date")?"":p_userDO.get("open_date").toString());
    userbaseinfo.setCur_use_date(null == p_userDO.get("cur_use_date")?"":p_userDO.get("cur_use_date").toString());
    userbaseinfo.setNo_use_date(null == p_userDO.get("no_use_date")?"":p_userDO.get("no_use_date").toString());
    userbaseinfo.setActivation_type_code(null == p_userDO.get("activation_type_code")?"":p_userDO.get("activation_type_code").toString());
    userbaseinfo.setActivation_date(null == p_userDO.get("activation_date")?"":p_userDO.get("activation_date").toString());
    userbaseinfo.setDept_code(null == p_userDO.get("dept_code")?"":p_userDO.get("dept_code").toString());
    userbaseinfo.setUuid(null == p_userDO.get("uuid")?"":p_userDO.get("uuid").toString());
    userbaseinfo.setCust_skin(null == p_userDO.get("cust_skin")?"":p_userDO.get("cust_skin").toString());
    userbaseinfo.setCreate_user_account_id(null == p_userDO.get("create_user_account_id")?"":p_userDO.get("create_user_account_id").toString());
    userbaseinfo.setCreate_date(null == p_userDO.get("create_date")?"":p_userDO.get("create_date").toString());
    userbaseinfo.setEncryption_info(null == p_userDO.get("encryption_info")?"":p_userDO.get("encryption_info").toString());
    userbaseinfo.setEncryption_timestamp(null == p_userDO.get("encryption_timestamp")?"":p_userDO.get("encryption_timestamp").toString());
    userbaseinfo.setRecord_status(null == p_userDO.get("record_status")?"":p_userDO.get("record_status").toString());
    userbaseinfo.setVer(null == p_userDO.get("ver")?"":p_userDO.get("ver").toString());
    userbaseinfo.setPassword(null == p_userDO.get("password")?"":p_userDO.get("password").toString());
    return userbaseinfo;
}
public List<UserBaseInfo> findUserBaseInfoDetailList(Integer domainId, Integer orgId,
        List<FilterParam> filter, Integer beginId, Integer limitId) {
    DAS das = this.getDAS();
    String sql =

        "select * from\n" +
        "(select\n" +
        "       t1.account_id,\n" +
        "       t1.code,\n" +
        "       (select name from p_unit where code = t1.user_unit_code) user_unit_code,\n" +
        "       t1.login_alias,\n" +
        "       t1.sex,\n" +
        "       t1.name,\n" +
        "       t1.user_type_code,\n" +
        "       t1.status_code,\n" +
        "       t1.open_date,\n" +
        "       t1.cur_use_date,\n" +
        "       t1.no_use_date,\n" +
        "       t1.activation_type_code,\n" +
        "       t1.activation_date,\n" +
        "       (select name from p_department where code = t1.dept_code) dept_code,\n" +
        "       t1.uuid,\n" +
        "       t1.cust_skin,\n" +
        "       (select name from p_user where account_id = t1.create_user_account_id) create_user_account_id,\n" +
        "       t1.create_date,\n" +
        "       t1.encryption_info,\n" +
        "       t1.encryption_timestamp,\n" +
        "       t1.record_status,\n" +
        "       t2.code ver,\n" +
        "       t1.password,\n" +
        "       rownum testnum\n" +
        "  		from p_user t1 , p_user_photo t2\n" +
        "       where t1.account_id = t2.account_id(+) and t1.user_unit_code = ? ) where 1=1\n";
    if (filter != null) {
        for (FilterParam paramItem : filter) {
            sql += " " + paramItem.getRelation() + " "
                    + paramItem.getField() + " " + paramItem.getLogical()
                    + " " + paramItem.getValue();
        }
    }
    userBaseDASLog.debug(sql);
    if(null != beginId)
    {
    sql = sql + " and testnum > ? ";
    }
    if(null != limitId)
    {
    sql = sql + " and testnum <= ? ";
    }
    sql = sql + " order by code asc ";
    Command command = das.createCommand(sql);
    List list = this.getFilledResultDescriptionList_p_user();
    command.setResultDescriptors(list);
    command.setParameter(1,orgId);
    if(null != beginId)
    {
    command.setParameter(2,beginId);
    }
    if(null != limitId)
    {
    command.setParameter(3,beginId+limitId);
    }
    DataObject root = command.executeQuery();
    List<UserBaseInfo> userbaseinfoList = new ArrayList<UserBaseInfo>();
    List<DataObject> doList = (List<DataObject>)root.getList("p_user");
    for(DataObject dObj : doList)
    {
    UserBaseInfo userbaseinfo = this.translateDOTOUserBaseInfo(dObj);
    userbaseinfoList.add(userbaseinfo);
    }

    return userbaseinfoList;
}

public List<SelectItem> selectToAllWantFilterSelect(String column1,String column2,String tableName,String whereStatement)
{
    DAS das = this.getDAS();
    String sql =
        "select " + column1 + "," + column2+ " from " + tableName;
    if(null != whereStatement)
    {
        sql += " where 1=1 " + whereStatement;
    }
    userBaseDASLog.debug(sql);
    List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();

    ResultDescriptor first = new ResultDescriptorImpl();
    first.setColumnName(column1);
    first.setTableName(tableName);
    first.setColumnIndex(0);
    first.setColumnType("commonj.sdo.String");
    list.add(first);
    ResultDescriptor second = new ResultDescriptorImpl();
    second.setColumnName(column2);
    second.setTableName(tableName);
    second.setColumnIndex(1);
    second.setColumnType("commonj.sdo.String");
    list.add(second);

    Command command = das.createCommand(sql);

    command.setResultDescriptors(list);
    DataObject root = command.executeQuery();
    List<DataObject> doList = (List<DataObject>)root.getList(tableName);

    List<SelectItem> rtnList = new ArrayList<SelectItem>();

    for(DataObject dobj : doList)
    {
        SelectItem item = new SelectItem();
        item.setId(dobj.getString(0));
        item.setName(dobj.getString(1));
        rtnList.add(item);
    }
    return rtnList;
}
public List<UserBaseInfo> findUserBaseInfoList(Integer domainId, Integer orgId,
        List<FilterParam> filter, Integer beginId, Integer limitId) {
    DAS das = this.getDAS();
    String sql =
        "select * " +
        "  from (select account_id,code,user_unit_code,login_alias,sex,name,user_type_code,status_code,\n" +
        "	to_char(open_date,'yyyy-mm-dd hh24:mi:ss') open_date,\n" +
        "	to_char(cur_use_date,'yyyy-mm-dd hh24:mi:ss') cur_use_date,\n" +
        "	to_char(no_use_date,'yyyy-mm-dd hh24:mi:ss') no_use_date,activation_type_code,\n" +
        "	to_char(activation_date,'yyyy-mm-dd hh24:mi:ss') activation_date,\n" +
        "	dept_code,uuid,cust_skin,create_user_account_id,to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date," +
        "	encryption_info,encryption_timestamp,record_status,ver,password, \n" +
        "       rownum testnum " +
        "       from p_user " +
        "       where  1=1  \n"+
        "       and user_unit_code = ? \n";
    if (filter != null) {
        for (FilterParam paramItem : filter) {
            sql += " " + paramItem.getRelation() + " "
                    + paramItem.getField() + " " + paramItem.getLogical()
                    + " " + paramItem.getValue();
        }
    }
    sql = sql +") p_user where 1=1\n";

    if(null != beginId)
    {
    sql = sql + " and testnum > ? ";
    }
    if(null != limitId)
    {
    sql = sql + " and testnum <= ? ";
    }
    sql = sql + " order by code asc ";
    userBaseDASLog.debug(sql);
    Command command = das.createCommand(sql);
    List list = this.getFilledResultDescriptionList_p_user();
    command.setResultDescriptors(list);
    command.setParameter(1,orgId);
    if(null != beginId)
    {
    command.setParameter(2,beginId);
    }
    if(null != limitId)
    {
    command.setParameter(3,beginId+limitId);
    }
    DataObject root = command.executeQuery();
    List<UserBaseInfo> userbaseinfoList = new ArrayList<UserBaseInfo>();
    List<DataObject> doList = (List<DataObject>)root.getList("p_user");
    for(DataObject dObj : doList)
    {
    UserBaseInfo userbaseinfo = this.translateDOTOUserBaseInfo(dObj);
    userbaseinfoList.add(userbaseinfo);
    }

    return userbaseinfoList;
}
public Boolean insertUserBaseInfo(UserBaseInfo userbaseinfo) {
    DAS das = this.getDAS();
    Boolean rtn = true;
    String sql =
    "  insert into p_user " +
    "  (account_id,code,user_unit_code,login_alias,sex,name,user_type_code,status_code," +
    "open_date,cur_use_date,no_use_date,activation_type_code,activation_date,dept_code," +
    "uuid,cust_skin,create_user_account_id,create_date,encryption_info,encryption_timestamp," +
    "record_status,ver,password) " +
    "  values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),sysdate," +
    "sysdate,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,sysdate,?,?,?,?,?)";
    userBaseDASLog.debug(sql);
    Command command = das.createCommand(sql);

    command.setParameter(1, null ==userbaseinfo.getAccount_id()?"":userbaseinfo.getAccount_id());
    command.setParameter(2, null ==userbaseinfo.getCode()?"":userbaseinfo.getCode());
    command.setParameter(3, null ==userbaseinfo.getUser_unit_code()?"":userbaseinfo.getUser_unit_code());
    command.setParameter(4, null ==userbaseinfo.getLogin_alias()?"":userbaseinfo.getLogin_alias());
    command.setParameter(5, null ==userbaseinfo.getSex()?"":userbaseinfo.getSex());
    command.setParameter(6, null ==userbaseinfo.getName()?"":userbaseinfo.getName());
    command.setParameter(7, null ==userbaseinfo.getUser_type_code()?"":userbaseinfo.getUser_type_code());
    command.setParameter(8, null ==userbaseinfo.getStatus_code()?"":userbaseinfo.getStatus_code());
    command.setParameter(9, null ==userbaseinfo.getOpen_date()?"":userbaseinfo.getOpen_date());
    command.setParameter(10, null ==userbaseinfo.getActivation_type_code()?"":userbaseinfo.getActivation_type_code());
    command.setParameter(11, null ==userbaseinfo.getActivation_date()?"":userbaseinfo.getActivation_date());
    command.setParameter(12, null ==userbaseinfo.getDept_code()?"":userbaseinfo.getDept_code());
    command.setParameter(13, null ==userbaseinfo.getUuid()?"":userbaseinfo.getUuid());
    command.setParameter(14, null ==userbaseinfo.getCust_skin()?"":userbaseinfo.getCust_skin());
    command.setParameter(15, null ==userbaseinfo.getCreate_user_account_id()?"":userbaseinfo.getCreate_user_account_id());
    command.setParameter(16, null ==userbaseinfo.getEncryption_info()?"":userbaseinfo.getEncryption_info());
    command.setParameter(17, null ==userbaseinfo.getEncryption_timestamp()?"":userbaseinfo.getEncryption_timestamp());
    command.setParameter(18, null ==userbaseinfo.getRecord_status()?"":userbaseinfo.getRecord_status());
    command.setParameter(19, null ==userbaseinfo.getVer()?"":userbaseinfo.getVer());
    command.setParameter(20, null ==userbaseinfo.getPassword()?"":userbaseinfo.getPassword());
    try
    {
    command.execute();
    }
    catch (Exception e)
    {
    e.printStackTrace();
    rtn = false;
    }
    return rtn;
}
public Boolean updateUserBaseInfo(UserBaseInfo userbaseinfo) {
    DAS das = this.getDAS();
    Boolean rtn = true;
    Command command = das.getCommand("getUserBaseInfoById");
    command.setParameter(1, userbaseinfo.getAccount_id());
    DataObject root = command.executeQuery();
    DataObject userbaseinfoDO = root.getDataObject("p_user[1]");
    if(null != userbaseinfo.getAccount_id())
    {
        userbaseinfoDO.set("account_id",userbaseinfo.getAccount_id().toString());
    }
    if(null != userbaseinfo.getCode())
    {
        userbaseinfoDO.set("code",userbaseinfo.getCode().toString());
    }
    if(null != userbaseinfo.getUser_unit_code())
    {
        userbaseinfoDO.set("user_unit_code",userbaseinfo.getUser_unit_code().toString());
    }
    if(null != userbaseinfo.getLogin_alias())
    {
        userbaseinfoDO.set("login_alias",userbaseinfo.getLogin_alias().toString());
    }
    if(null != userbaseinfo.getSex())
    {
        userbaseinfoDO.set("sex",userbaseinfo.getSex().toString());
    }
    if(null != userbaseinfo.getName())
    {
        userbaseinfoDO.set("name",userbaseinfo.getName().toString());
    }
    if(null != userbaseinfo.getUser_type_code())
    {
        userbaseinfoDO.set("user_type_code",userbaseinfo.getUser_type_code().toString());
    }
    if(null != userbaseinfo.getStatus_code())
    {
        userbaseinfoDO.set("status_code",userbaseinfo.getStatus_code().toString());
    }
    if(null != userbaseinfo.getOpen_date())
    {
        userbaseinfoDO.set("open_date",userbaseinfo.getOpen_date().toString());
    }
    if(null != userbaseinfo.getCur_use_date())
    {
        userbaseinfoDO.set("cur_use_date",userbaseinfo.getCur_use_date().toString());
    }
    if(null != userbaseinfo.getNo_use_date())
    {
        userbaseinfoDO.set("no_use_date",userbaseinfo.getNo_use_date().toString());
    }
    if(null != userbaseinfo.getActivation_type_code())
    {
        userbaseinfoDO.set("activation_type_code",userbaseinfo.getActivation_type_code().toString());
    }
    if(null != userbaseinfo.getActivation_date())
    {
        userbaseinfoDO.set("activation_date",userbaseinfo.getActivation_date().toString());
    }
    if(null != userbaseinfo.getDept_code())
    {
        userbaseinfoDO.set("dept_code",userbaseinfo.getDept_code().toString());
    }
    if(null != userbaseinfo.getUuid())
    {
        userbaseinfoDO.set("uuid",userbaseinfo.getUuid().toString());
    }
    if(null != userbaseinfo.getCust_skin())
    {
        userbaseinfoDO.set("cust_skin",userbaseinfo.getCust_skin().toString());
    }
    if(null != userbaseinfo.getCreate_user_account_id())
    {
        userbaseinfoDO.set("create_user_account_id",userbaseinfo.getCreate_user_account_id().toString());
    }
    if(null != userbaseinfo.getCreate_date())
    {
        userbaseinfoDO.set("create_date",userbaseinfo.getCreate_date().toString());
    }
    if(null != userbaseinfo.getEncryption_info())
    {
        userbaseinfoDO.set("encryption_info",userbaseinfo.getEncryption_info().toString());
    }
    if(null != userbaseinfo.getEncryption_timestamp())
    {
        userbaseinfoDO.set("encryption_timestamp",userbaseinfo.getEncryption_timestamp().toString());
    }
    if(null != userbaseinfo.getRecord_status())
    {
        userbaseinfoDO.set("record_status",userbaseinfo.getRecord_status().toString());
    }
    if(null != userbaseinfo.getVer())
    {
        userbaseinfoDO.set("ver",userbaseinfo.getVer().toString());
    }
    if(null != userbaseinfo.getPassword())
    {
        userbaseinfoDO.set("password",userbaseinfo.getPassword().toString());
    }

    try
    {
        das.applyChanges(root);
    }
    catch (Exception e)
    {
        rtn = false;
        userBaseDASLog.error(e.getStackTrace());
    }
    finally
    {

    }
    return rtn;
}
public Boolean delUserBaseInfoById(Integer id) {
    DAS das = this.getDAS();
    Boolean rtn = true;
    String sql =
    "  delete p_user where account_id = ?";
    userBaseDASLog.debug(sql);
    Command command = das.createCommand(sql);
    command.setParameter(1, id);
    try
    {
        command.execute();
    }
    catch (Exception e)
    {
        userBaseDASLog.error(e.getStackTrace());
        rtn = false;
    }
    //删除成功才会删除用户相关的角色
    if(rtn)
    {
        String deleteUserRole = "  delete p_user_role where account_id = ?";
        userBaseDASLog.debug(sql);
        Command deleteUserRoleCommand = das.createCommand(deleteUserRole);
        deleteUserRoleCommand.setParameter(1, id);
        try
        {
            deleteUserRoleCommand.execute();
        }
        catch (Exception e)
        {
            userBaseDASLog.error(e.getStackTrace());
            rtn = false;
        }
    }
    return rtn;
}
public List getFilledResultDescriptionList_p_user()
{
    List list = new ArrayList();
    ResultDescriptor account_id = new ResultDescriptorImpl();
    account_id.setColumnName("account_id");
    account_id.setTableName("p_user");
    account_id.setColumnIndex(0);
    account_id.setColumnType("commonj.sdo.String");
    list.add(account_id);
    ResultDescriptor code = new ResultDescriptorImpl();
    code.setColumnName("code");
    code.setTableName("p_user");
    code.setColumnIndex(1);
    code.setColumnType("commonj.sdo.String");
    list.add(code);
    ResultDescriptor user_unit_code = new ResultDescriptorImpl();
    user_unit_code.setColumnName("user_unit_code");
    user_unit_code.setTableName("p_user");
    user_unit_code.setColumnIndex(2);
    user_unit_code.setColumnType("commonj.sdo.String");
    list.add(user_unit_code);
    ResultDescriptor login_alias = new ResultDescriptorImpl();
    login_alias.setColumnName("login_alias");
    login_alias.setTableName("p_user");
    login_alias.setColumnIndex(3);
    login_alias.setColumnType("commonj.sdo.String");
    list.add(login_alias);
    ResultDescriptor sex = new ResultDescriptorImpl();
    sex.setColumnName("sex");
    sex.setTableName("p_user");
    sex.setColumnIndex(4);
    sex.setColumnType("commonj.sdo.String");
    list.add(sex);
    ResultDescriptor name = new ResultDescriptorImpl();
    name.setColumnName("name");
    name.setTableName("p_user");
    name.setColumnIndex(5);
    name.setColumnType("commonj.sdo.String");
    list.add(name);
    ResultDescriptor user_type_code = new ResultDescriptorImpl();
    user_type_code.setColumnName("user_type_code");
    user_type_code.setTableName("p_user");
    user_type_code.setColumnIndex(6);
    user_type_code.setColumnType("commonj.sdo.String");
    list.add(user_type_code);
    ResultDescriptor status_code = new ResultDescriptorImpl();
    status_code.setColumnName("status_code");
    status_code.setTableName("p_user");
    status_code.setColumnIndex(7);
    status_code.setColumnType("commonj.sdo.String");
    list.add(status_code);
    ResultDescriptor open_date = new ResultDescriptorImpl();
    open_date.setColumnName("open_date");
    open_date.setTableName("p_user");
    open_date.setColumnIndex(8);
    open_date.setColumnType("commonj.sdo.String");
    list.add(open_date);
    ResultDescriptor cur_use_date = new ResultDescriptorImpl();
    cur_use_date.setColumnName("cur_use_date");
    cur_use_date.setTableName("p_user");
    cur_use_date.setColumnIndex(9);
    cur_use_date.setColumnType("commonj.sdo.String");
    list.add(cur_use_date);
    ResultDescriptor no_use_date = new ResultDescriptorImpl();
    no_use_date.setColumnName("no_use_date");
    no_use_date.setTableName("p_user");
    no_use_date.setColumnIndex(10);
    no_use_date.setColumnType("commonj.sdo.String");
    list.add(no_use_date);
    ResultDescriptor activation_type_code = new ResultDescriptorImpl();
    activation_type_code.setColumnName("activation_type_code");
    activation_type_code.setTableName("p_user");
    activation_type_code.setColumnIndex(11);
    activation_type_code.setColumnType("commonj.sdo.String");
    list.add(activation_type_code);
    ResultDescriptor activation_date = new ResultDescriptorImpl();
    activation_date.setColumnName("activation_date");
    activation_date.setTableName("p_user");
    activation_date.setColumnIndex(12);
    activation_date.setColumnType("commonj.sdo.String");
    list.add(activation_date);
    ResultDescriptor dept_code = new ResultDescriptorImpl();
    dept_code.setColumnName("dept_code");
    dept_code.setTableName("p_user");
    dept_code.setColumnIndex(13);
    dept_code.setColumnType("commonj.sdo.String");
    list.add(dept_code);
    ResultDescriptor uuid = new ResultDescriptorImpl();
    uuid.setColumnName("uuid");
    uuid.setTableName("p_user");
    uuid.setColumnIndex(14);
    uuid.setColumnType("commonj.sdo.String");
    list.add(uuid);
    ResultDescriptor cust_skin = new ResultDescriptorImpl();
    cust_skin.setColumnName("cust_skin");
    cust_skin.setTableName("p_user");
    cust_skin.setColumnIndex(15);
    cust_skin.setColumnType("commonj.sdo.String");
    list.add(cust_skin);
    ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
    create_user_account_id.setColumnName("create_user_account_id");
    create_user_account_id.setTableName("p_user");
    create_user_account_id.setColumnIndex(16);
    create_user_account_id.setColumnType("commonj.sdo.String");
    list.add(create_user_account_id);
    ResultDescriptor create_date = new ResultDescriptorImpl();
    create_date.setColumnName("create_date");
    create_date.setTableName("p_user");
    create_date.setColumnIndex(17);
    create_date.setColumnType("commonj.sdo.String");
    list.add(create_date);
    ResultDescriptor encryption_info = new ResultDescriptorImpl();
    encryption_info.setColumnName("encryption_info");
    encryption_info.setTableName("p_user");
    encryption_info.setColumnIndex(18);
    encryption_info.setColumnType("commonj.sdo.String");
    list.add(encryption_info);
    ResultDescriptor encryption_timestamp = new ResultDescriptorImpl();
    encryption_timestamp.setColumnName("encryption_timestamp");
    encryption_timestamp.setTableName("p_user");
    encryption_timestamp.setColumnIndex(19);
    encryption_timestamp.setColumnType("commonj.sdo.String");
    list.add(encryption_timestamp);
    ResultDescriptor record_status = new ResultDescriptorImpl();
    record_status.setColumnName("record_status");
    record_status.setTableName("p_user");
    record_status.setColumnIndex(20);
    record_status.setColumnType("commonj.sdo.String");
    list.add(record_status);
    ResultDescriptor ver = new ResultDescriptorImpl();
    ver.setColumnName("ver");
    ver.setTableName("p_user");
    ver.setColumnIndex(21);
    ver.setColumnType("commonj.sdo.String");
    list.add(ver);
    ResultDescriptor password = new ResultDescriptorImpl();
    password.setColumnName("password");
    password.setTableName("p_user");
    password.setColumnIndex(22);
    password.setColumnType("commonj.sdo.String");
    list.add(password);
    return list;
}
}
