package net.newcapec.sca.user.das.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserPhoto;
import net.newcapec.sca.user.das.UserPhotoDAS;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.Parameter;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ParameterImpl;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class UserPhotoDASImpl implements UserPhotoDAS{
    private static final Logger userPhotoDASLog = Logger.getLogger(UserBaseInfoDASImpl.class);

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
public UserPhoto getUserPhotoById(Integer id)
{	
    DAS das = this.getDAS();
    Command command = das.getCommand("getUserPhotoById");
    command.setParameter(1, id);
    DataObject root = command.executeQuery();
    List<DataObject> list = root.getList("p_user_photo");
    DataObject p_user_photoDO = null; 
    UserPhoto userphoto = null; 
    if(null != list && list.size() > 0) 
    {
        p_user_photoDO = list.get(0); 
        userphoto = this.translateDOTOUserPhoto(p_user_photoDO); 
    } 
    return userphoto;
}

public UserPhoto getUserPhotoByUserCode(String userCode)
{
    DAS das = this.getDAS();
    Command command = das.getCommand("getUserPhotoByUserCode");
    command.setParameter(1, userCode);
    DataObject root = command.executeQuery();
    List<DataObject> list = root.getList("p_user_photo");
    DataObject p_user_photoDO = null; 
    UserPhoto userphoto = null; 
    if(null != list && list.size() > 0) 
    {
        p_user_photoDO = list.get(0); 
        userphoto = this.translateDOTOUserPhoto(p_user_photoDO); 
    } 
    return userphoto;
}
private UserPhoto translateDOTOUserPhoto(DataObject p_user_photoDO)
{
    byte[] noBytes = new byte[1];
    noBytes[0] = 'n';
    UserPhoto userphoto = new UserPhoto();
    userphoto.setCode(null == p_user_photoDO.get("code")?"":p_user_photoDO.get("code").toString());
    userphoto.setAccount_id(null == p_user_photoDO.get("account_id")?"":p_user_photoDO.get("account_id").toString());
    userphoto.setUser_unit_code(null == p_user_photoDO.get("user_unit_code")?"":p_user_photoDO.get("user_unit_code").toString());
    userphoto.setCust_code(null == p_user_photoDO.get("cust_code")?"":p_user_photoDO.get("cust_code").toString());
    userphoto.setPhoto_no(null == p_user_photoDO.get("photo_no")?"":p_user_photoDO.get("photo_no").toString());
    userphoto.setPhoto(null == p_user_photoDO.getBytes("photo")?noBytes:p_user_photoDO.getBytes("photo"));
    userphoto.setPhoto_file_path(null == p_user_photoDO.get("photo_file_path")?"":p_user_photoDO.get("photo_file_path").toString());
    userphoto.setCreate_user_account_id(null == p_user_photoDO.get("create_user_account_id")?"":p_user_photoDO.get("create_user_account_id").toString());
    userphoto.setCreate_date(null == p_user_photoDO.get("create_date")?"":p_user_photoDO.get("create_date").toString());
    userphoto.setVer(null == p_user_photoDO.get("ver")?"":p_user_photoDO.get("ver").toString());
    return userphoto;
}
public List<UserPhoto> findUserPhotoList(Integer domainId, Integer orgId,
             List<FilterParam> filter, Integer beginId, Integer limitId) {
    String sql =
         "select * " + 
        "  from (select code,account_id,user_unit_code,cust_code,photo_no,photo,photo_file_path,create_user_account_id,create_date,ver, " + 
        "       rownum testnum " + 
        "       from p_user_photo " + 
        "       where  1 = 1 " + 
        "		  and domain_code = ?  " + 
        "       and user_unit_code = ? ";  
    if (filter != null) {
      for (FilterParam paramItem : filter) {
        sql += " " + paramItem.getRelation() + " "
            + paramItem.getField() + " " + paramItem.getLogical()
                  + " " + paramItem.getValue();
        }
    }
    sql = sql +") where 1=1\n";    if(null != beginId)
    {
      sql = sql + " and testnum > ? ";
    }
    if(null != limitId)
    {
      sql = sql + " and testnum <= ? ";
    }
    sql = sql + " order by code asc ";
    DAS das = this.getDAS();
    Command command = das.createCommand(sql);
    List list = this.getFilledResultDescriptionList_p_user_photo();
    command.setResultDescriptors(list);
    command.setParameter(1,domainId);
    command.setParameter(2,orgId);
    if(null != beginId)
    {
      command.setParameter(3,beginId);
    }
    if(null != limitId)
    {
      command.setParameter(4,beginId+limitId);
    }
    DataObject root = command.executeQuery();
    List<UserPhoto> userphotoList = new ArrayList<UserPhoto>();
    List<DataObject> doList = (List<DataObject>)root.getList("p_user_photo"); 
    for(DataObject dObj : doList)
    {
      UserPhoto userphoto = this.translateDOTOUserPhoto(dObj);
      userphotoList.add(userphoto);
    }
    return userphotoList;
}
public Boolean insertUserPhoto(UserPhoto userphoto) {
    Boolean rtn = true;
    String sql =
      "  insert into p_user_photo " +
      "  (code,account_id,user_unit_code,cust_code,photo_no,photo,photo_file_path,create_user_account_id,create_date,ver) " +
      "  values(?,?,?,?,?,?,?,?,sysdate,?)";
    DAS das = this.getDAS();
    Command command = das.createCommand(sql);
    byte[] noBytes = new byte[1];
    noBytes[0] = 'n';
    command.setParameter(1, null ==userphoto.getCode()?"":userphoto.getCode());
    command.setParameter(2, null ==userphoto.getAccount_id()?"":userphoto.getAccount_id());
    command.setParameter(3, null ==userphoto.getUser_unit_code()?"":userphoto.getUser_unit_code());
    command.setParameter(4, null ==userphoto.getCust_code()?"":userphoto.getCust_code());
    command.setParameter(5, null ==userphoto.getPhoto_no()?"":userphoto.getPhoto_no());
    command.setParameter(6, null ==userphoto.getPhoto()?noBytes:userphoto.getPhoto());
    command.setParameter(7, null ==userphoto.getPhoto_file_path()?"":userphoto.getPhoto_file_path());
    command.setParameter(8, null ==userphoto.getCreate_user_account_id()?"":userphoto.getCreate_user_account_id());
    command.setParameter(9, null ==userphoto.getVer()?"":userphoto.getVer());
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
public Boolean updateUserPhoto(UserPhoto userphoto) {
        Boolean rtn = true;
        Boolean needChangeBlob = false;
        DAS das = this.getDAS();
        
        String updateSQL = "update p_user_photo set code = " + userphoto.getCode() ;
        
        if(null != userphoto.getAccount_id())
        {
            updateSQL = updateSQL + " ,account_id = " + userphoto.getAccount_id();
        }
        if(null != userphoto.getUser_unit_code())
        {
            updateSQL = updateSQL + " ,user_unit_code = " + userphoto.getUser_unit_code();
        }
        if(null != userphoto.getCust_code())
        {
            updateSQL = updateSQL + " ,cust_code = '" + userphoto.getCust_code()+"'";
        }
        if(null != userphoto.getPhoto_no())
        {
            updateSQL = updateSQL + " ,photo_no = '" + userphoto.getPhoto_no()+"'";
        }
        if(null != userphoto.getPhoto())
        {
             updateSQL = updateSQL + " ,photo = ?";
             needChangeBlob = true;
        }
        if(null != userphoto.getPhoto_file_path())
        {
            updateSQL = updateSQL + " ,photo_file_path = '"  + userphoto.getPhoto_file_path()+"'";
        }
        if(null != userphoto.getCreate_user_account_id())
        {
            updateSQL = updateSQL + " ,create_user_account_id = " + userphoto.getCreate_user_account_id();
        }
        if(null != userphoto.getVer())
        {
            updateSQL = updateSQL + " ,ver = " + userphoto.getVer();
        }
        updateSQL = updateSQL + " where code = " + userphoto.getCode();
        try
        {
            userPhotoDASLog.debug(updateSQL);
            Command command = das.createCommand(updateSQL);
            if(needChangeBlob)
            {
                command.setParameter(1, userphoto.getPhoto());
            }
            command.execute();
        }
        catch (Exception e)
        {
            rtn = false;
            e.printStackTrace();
        }
        return rtn;
}
public Boolean delUserPhotoById(Integer id) {
    Boolean rtn = true;
    String sql =
      "  delete p_user_photo where code = ?";
    DAS das = this.getDAS();
    Command command = das.createCommand(sql);
    command.setParameter(1, id);
    try
    {
      command.execute();
    }
    catch (Exception e)
    {
      rtn = false;
    }
    return rtn;
}
public Boolean delUserPhotoByCust_code(String id) {
    Boolean rtn = true;
    String sql =
      "  delete p_user_photo where cust_code = ?";
    DAS das = this.getDAS();
    Command command = das.createCommand(sql);
    command.setParameter(1, id);
    try
    {
      command.execute();
    }
    catch (Exception e)
    {
      rtn = false;
    }
    return rtn;
}
public List getFilledResultDescriptionList_p_user_photo()
{ 
    List list = new ArrayList();
    ResultDescriptor code = new ResultDescriptorImpl();
    code.setColumnName("code");
    code.setTableName("p_user_photo");
    code.setColumnIndex(0);
    code.setColumnType("commonj.sdo.String");
    list.add(code); 
    ResultDescriptor account_id = new ResultDescriptorImpl();
    account_id.setColumnName("account_id");
    account_id.setTableName("p_user_photo");
    account_id.setColumnIndex(1);
    account_id.setColumnType("commonj.sdo.String");
    list.add(account_id); 
    ResultDescriptor user_unit_code = new ResultDescriptorImpl();
    user_unit_code.setColumnName("user_unit_code");
    user_unit_code.setTableName("p_user_photo");
    user_unit_code.setColumnIndex(2);
    user_unit_code.setColumnType("commonj.sdo.String");
    list.add(user_unit_code); 
    ResultDescriptor cust_code = new ResultDescriptorImpl();
    cust_code.setColumnName("cust_code");
    cust_code.setTableName("p_user_photo");
    cust_code.setColumnIndex(3);
    cust_code.setColumnType("commonj.sdo.String");
    list.add(cust_code); 
    ResultDescriptor photo_no = new ResultDescriptorImpl();
    photo_no.setColumnName("photo_no");
    photo_no.setTableName("p_user_photo");
    photo_no.setColumnIndex(4);
    photo_no.setColumnType("commonj.sdo.String");
    list.add(photo_no); 
    ResultDescriptor photo = new ResultDescriptorImpl();
    photo.setColumnName("photo");
    photo.setTableName("p_user_photo");
    photo.setColumnIndex(5);
    photo.setColumnType("commonj.sdo.Bytes");
    list.add(photo); 
    ResultDescriptor photo_file_path = new ResultDescriptorImpl();
    photo_file_path.setColumnName("photo_file_path");
    photo_file_path.setTableName("p_user_photo");
    photo_file_path.setColumnIndex(6);
    photo_file_path.setColumnType("commonj.sdo.String");
    list.add(photo_file_path); 
    ResultDescriptor create_user_account_id = new ResultDescriptorImpl();
    create_user_account_id.setColumnName("create_user_account_id");
    create_user_account_id.setTableName("p_user_photo");
    create_user_account_id.setColumnIndex(7);
    create_user_account_id.setColumnType("commonj.sdo.String");
    list.add(create_user_account_id); 
    ResultDescriptor create_date = new ResultDescriptorImpl();
    create_date.setColumnName("create_date");
    create_date.setTableName("p_user_photo");
    create_date.setColumnIndex(8);
    create_date.setColumnType("commonj.sdo.String");
    list.add(create_date); 
    ResultDescriptor ver = new ResultDescriptorImpl();
    ver.setColumnName("ver");
    ver.setTableName("p_user_photo");
    ver.setColumnIndex(9);
    ver.setColumnType("commonj.sdo.String");
    list.add(ver); 
    return list;
}
}
