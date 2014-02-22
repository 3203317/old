package net.newcapec.sca.customform.service;

import java.util.List;
import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.resource.bean.MenuItem;
import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface CustomFormService {

	public CustomForm getCustomFormById(String sessionId, Integer id);

	public List<CustomForm> findCustomFormList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin,
			Integer limit);

	public DojoListData findCustomFormDojoList(DojoListParam param);

	public CustomForm insertCustomForm(String sessionId, CustomForm customForm);

	public CustomForm updateCustomForm(String sessionId, CustomForm customForm);

	public CustomForm delCustomFormById(String sessionId, Integer id);

	public CustomForm delCustomFormByIds(String sessionId, String ids);

	public Integer getCustomFormListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

	public MenuItem[] getFilteringSelectList(String sessionId );

}
