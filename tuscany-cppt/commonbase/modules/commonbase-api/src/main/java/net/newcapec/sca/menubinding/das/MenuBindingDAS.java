package net.newcapec.sca.menubinding.das;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.menubinding.MenuBinding;
import net.newcapec.sca.param.FilterParam;

public interface MenuBindingDAS {
	public MenuBinding getMenuBindingById(Integer id);
	public List<MenuBinding> findMenuBindingList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limit);
	public Boolean insertMenuBinding(MenuBinding menuBinding);
	public Boolean updateMenuBinding(MenuBinding menuBinding);
	public Boolean delMenuBindingById(Integer id);

	//增加业务接口
	//public MenuBinding delMenuBindingByIds(String sessionId,String ids);
	//增加数据访问接口
	public Boolean delMenuBindingByIds(Integer[] ids);
	//新增加的提供客户端Dojo调用的，可直接返回Dojo可调用的格式的对象。
	public DojoListData findMenuBindingList (DojoListParam dojolistparam);

	//新增的针对每个返回List对象的接口增加一个配套的获取记录总数的接口
	public Integer getMenuBindingListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter);


}
