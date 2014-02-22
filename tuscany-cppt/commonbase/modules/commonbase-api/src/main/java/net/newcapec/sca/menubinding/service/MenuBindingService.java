package net.newcapec.sca.menubinding.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.menubinding.MenuBinding;
import net.newcapec.sca.param.FilterParam;
@Remotable
public interface MenuBindingService {
	public MenuBinding getMenuBindingById(String sessionId,Integer id);
	public List<MenuBinding> findMenuBindingList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit);
	public MenuBinding insertMenuBinding(String sessionId,MenuBinding menuBinding);
	public MenuBinding updateMenuBinding(String sessionId,MenuBinding menuBinding);
	public MenuBinding delMenuBindingById(String sessionId,Integer id);
	public DojoListData findMenuBindingDojoList (DojoListParam param);
	public Integer getMenuBindingListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter);

}
