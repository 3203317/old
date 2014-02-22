package net.newcapec.sca.user.service;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserBaseInfo;

import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface UserBaseInfoService {
	public UserBaseInfo getUserBaseInfoById(String sessionId,Integer id);
	public UserBaseInfo getUserBaseInfoByCode(String sessionId,String code);
	//域Id从组合构件上下文获取，组织Id从会话中获取
	public List<UserBaseInfo> findUserBaseInfoList(String sessionId, Integer resourceId,List<FilterParam> filter, Integer beginId, Integer limit);
	public UserBaseInfo insertUserBaseInfo(String sessionId,UserBaseInfo userBaseInfo);
	public UserBaseInfo updateUserBaseInfo(String sessionId,UserBaseInfo userBaseInfo);
	public UserBaseInfo delUserBaseInfoById(String sessionId,Integer id);
	public Boolean delUserBaseInfoByIds(String sessionId, String ids);
	public DojoListData findUserBaseInfoDojoList(DojoListParam param);
	public SelectItem[] getUserTypeSelectItem(String sessionId);
	public SelectItem[] getUserUnitSelectItem(String sessionId);
	public SelectItem[] getUserDeptSelectItem(String sessionId,String unitId);
	public SelectItem[] getCustSkinsSelectItem(String sessionId);

}
