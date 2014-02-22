package net.newcapec.sca.user.das;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserBaseInfo;

public interface UserBaseInfoDAS
{
    public UserBaseInfo getUserBaseInfoByCode(String code);
    public UserBaseInfo getUserBaseInfoById(Integer id);
    public List<UserBaseInfo> findUserBaseInfoList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId);
    public Boolean insertUserBaseInfo(UserBaseInfo org);
    public Boolean updateUserBaseInfo(UserBaseInfo org);
    public Boolean delUserBaseInfoById(Integer id);
    public List<UserBaseInfo> findUserBaseInfoDetailList(Integer domainId, Integer orgId,
            List<FilterParam> filter, Integer beginId, Integer limitId) ;
    //通用的组成selectItemList的方法
    public List<SelectItem> selectToAllWantFilterSelect(String column1,String column2,String tableName,String whereStatement);
}
