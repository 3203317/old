package net.newcapec.sca.user.service.impl;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.UserPhoto;
import net.newcapec.sca.user.das.UserBaseInfoDAS;
import net.newcapec.sca.user.das.UserPhotoDAS;
import net.newcapec.sca.user.service.UserBaseInfoService;
import net.newcapec.sca.user.service.UserPasswordService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class UserBaseInfoServiceImpl implements UserBaseInfoService {


    private SessionService sessionService;

    @Reference(name = "sessionService", required = true)
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    private SequenceService sequenceService;

    @Reference(name = "sequenceService", required = true)
    public void setSequenceService(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }
    private UserBaseInfoDAS userBaseInfoDAS;

    @Reference(name = "userBaseInfoDAS", required = true)
    public void setUserBaseInfoDAS(UserBaseInfoDAS userBaseInfoDAS) {
        this.userBaseInfoDAS = userBaseInfoDAS;
    }
    
    private UserPhotoDAS userPhotoDAS;

    @Reference(name = "userPhotoDAS", required = true)
    public void setUserPhotoDAS(UserPhotoDAS userPhotoDAS) {
        this.userPhotoDAS = userPhotoDAS;
    }

    private UserPasswordService  userPasswordService;

    @Reference(name = "userPasswordService", required = true)
    public void setUserPasswordService(UserPasswordService userPasswordService) {
        this.userPasswordService = userPasswordService;
    }

    private static final Logger userBaseServiceLog = Logger.getLogger(UserBaseInfoServiceImpl.class);
    //private static final String INITPASSWORD  = "9b4b5ac88c4aacc0df2dae07ebb0794e";



    public UserBaseInfo getUserBaseInfoById(String sessionId, Integer id) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        UserBaseInfo userBaseInfo = this.userBaseInfoDAS.getUserBaseInfoById(id);
        return userBaseInfo;
    }

    public UserBaseInfo getUserBaseInfoByCode(String sessionId, String code) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        UserBaseInfo userBaseInfo = this.userBaseInfoDAS.getUserBaseInfoByCode(code);
        ResultMsg resultMsg = new ResultMsg();

        if(userBaseInfo != null)
        {
            resultMsg.setStatus(1);
        }
        else
        {
            resultMsg.setStatus(0);
        }
        userBaseInfo.setResultMsg(resultMsg);
        return userBaseInfo;

    }

    public List<UserBaseInfo> findUserBaseInfoList(String sessionId,
            Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        List<UserBaseInfo> list  = this.userBaseInfoDAS.findUserBaseInfoList(1, 1, filter, beginId, limit);
        return list;
    }
    public DojoListData findUserBaseInfoDojoList(DojoListParam param)
    {
        Session session = sessionService.getSession(param.getSessionId());
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        List<UserBaseInfo> list =
            this.userBaseInfoDAS.findUserBaseInfoDetailList(Integer.valueOf(session.getDomain_code()),
                    Integer.valueOf(session.getUnit_code()),
                    param.getFilter(),
                    param.getBegin(),
                    param.getLimit());

        DojoListData dld = new DojoListData();
        dld.setIdentifier("account_id");
        dld.setItems(list.toArray());
        dld.setLabel("name");
        Integer userRolesNum = list.size();
        dld.setNumRows(userRolesNum);
        return dld;
    }
    public UserBaseInfo insertUserBaseInfo(String sessionId,
            UserBaseInfo userBaseInfo) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        String  initPassword  = userPasswordService.md5EncodePassword("1");

        System.out.println("初始密码:"+initPassword);

        ResultMsg resultMsg = new ResultMsg();
        userBaseInfo.setActivation_date("2099-1-1");
        userBaseInfo.setActivation_type_code("0");
        userBaseInfo.setStatus_code("2");
        userBaseInfo.setEncryption_info("1");
        userBaseInfo.setEncryption_timestamp("1");
        userBaseInfo.setRecord_status("0");
        userBaseInfo.setVer("1");
        userBaseInfo.setPassword(initPassword);
        userBaseInfo.setCreate_user_account_id(session.getUser_code());
        userBaseInfo.setUser_unit_code(session.getUnit_code());
        userBaseInfo.setUuid("1");
        userBaseInfo.setOpen_date("2099-1-1");
        
        try
        {
            userBaseInfo.setAccount_id(this.sequenceService.getNextValue("getMaxUserBaseInfoID").toString());
            boolean sign = 	this.userBaseInfoDAS.insertUserBaseInfo(userBaseInfo);
            if(sign)
            {
                int i = manipulateUserPhoto(userBaseInfo)?1:2;
                resultMsg.setStatus(i);
            }
            else
            {
                resultMsg.setStatus(0);
            }
        }
        catch (Exception e)
        {
            userBaseServiceLog.debug(e.getMessage(),e);
            resultMsg.setStatus(0);
        }
        userBaseInfo.setResultMsg(resultMsg);
        return userBaseInfo;
    }

    public boolean manipulateUserPhoto(UserBaseInfo userBaseInfo)
    {
        if(null == userBaseInfo.getUserPhotoId() || "".equals(userBaseInfo.getUserPhotoId()))
        {
            return true;
        }
        
        try
        {
            UserPhoto userPhoto = new UserPhoto();
            userPhoto.setCode(userBaseInfo.getUserPhotoId());
            userPhoto.setAccount_id(userBaseInfo.getAccount_id());
            userPhoto.setCust_code(userBaseInfo.getCode());
            userPhoto.setUser_unit_code(userBaseInfo.getUser_unit_code());
            this.userPhotoDAS.updateUserPhoto(userPhoto);
            return true;
        }
        catch (Exception e)
        {
            userBaseServiceLog.debug(e.getMessage(),e);
            return false;
        }
    }
    
    public SelectItem[] getUserTypeSelectItem(String sessionId)
    {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        String whereStatement = " and user_unit_code = " + session.getUnit_code();
        List<SelectItem> list = this.userBaseInfoDAS.selectToAllWantFilterSelect("code", "name", "p_user_type",whereStatement);
        SelectItem[] rtnArray = new SelectItem[list.size()];
        list.toArray(rtnArray);
        return rtnArray;
    }
    public SelectItem[] getUserUnitSelectItem(String sessionId)
    {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        String whereStatement = null;
        List<SelectItem> list = this.userBaseInfoDAS.selectToAllWantFilterSelect("code", "name", "p_unit",whereStatement);
        SelectItem[] rtnArray = new SelectItem[list.size()];
        list.toArray(rtnArray);
        return rtnArray;
    }
    public SelectItem[] getUserDeptSelectItem(String sessionId,String unitId)
    {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        String whereStatement = " and user_unit_code = " + unitId;
        List<SelectItem> list = this.userBaseInfoDAS.selectToAllWantFilterSelect("code", "name", "p_department",whereStatement);
        SelectItem[] rtnArray = new SelectItem[list.size()];
        list.toArray(rtnArray);
        return rtnArray;
    }

    public SelectItem[] getCustSkinsSelectItem(String sessionId)
    {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        String whereStatement =  " and code_dictionary_name = 'SKIN'";
        List<SelectItem> list = this.userBaseInfoDAS.selectToAllWantFilterSelect("code", "data_value", "z_code_dictionary_data",whereStatement);
        SelectItem[] rtnArray = new SelectItem[list.size()];
        list.toArray(rtnArray);
        return rtnArray;
    }

    public UserBaseInfo updateUserBaseInfo(String sessionId,
            UserBaseInfo userBaseInfo) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        ResultMsg resultMsg = new ResultMsg();
        try
        {
            boolean sign = this.userBaseInfoDAS.updateUserBaseInfo(userBaseInfo);
            if(sign)
            {
                resultMsg.setStatus(1);
            }
            else
            {
                resultMsg.setStatus(0);
            }
        }
        catch (Exception e)
        {
            userBaseServiceLog.debug(e.getMessage(),e);
            resultMsg.setStatus(0);
        }
        userBaseInfo.setResultMsg(resultMsg);
        return userBaseInfo;
    }

    public UserBaseInfo delUserBaseInfoById(String sessionId, Integer id) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        ResultMsg resultMsg = new ResultMsg();
        try
        {
            boolean sign = this.userBaseInfoDAS.delUserBaseInfoById(id);
            if(sign)
            {
                resultMsg.setStatus(1);
            }
            else
            {
                resultMsg.setStatus(0);
            }
        }
        catch (Exception e)
        {
            userBaseServiceLog.debug(e.getMessage(),e);
            resultMsg.setStatus(0);
        }
        userBaseInfo.setResultMsg(resultMsg);
        return userBaseInfo;
    }
    public Boolean delUserBaseInfoByIds(String sessionId, String ids) {
        Session session = sessionService.getSession(sessionId);
        if(!this.isSessionVaild(session))
        {
            return null;
        }
        Boolean rtnSign = false;
        try
        {
            String[] allID = ids.split(",");
            for(String id : allID)
            {
                this.userBaseInfoDAS.delUserBaseInfoById(Integer.valueOf(id));
            }
            rtnSign = true;
        }
        catch (Exception e)
        {
            userBaseServiceLog.debug(e.getMessage(),e);
        }
        return rtnSign;
    }
    /**
     * true 有效， false 失效
     * @param session
     * @return
     */
    protected boolean isSessionVaild(Session session)
    {
        boolean sessionVaild = true;
        try
        {
            if (null == session.getId() && session.getState() == 0)
            {
                sessionVaild = false;
            }
        }
        catch (Exception e)
        {
            userBaseServiceLog.error("session异常: sessionid= " + session.getId() +" , "+ e.getStackTrace());
            sessionVaild = false;
        }
        return sessionVaild;
    }
}
