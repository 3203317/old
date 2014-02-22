package net.newcapec.sca.customform.das;

import java.util.List;

import net.newcapec.sca.customform.CustomForm;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface CustomFormDAS {
	public CustomForm getCustomFormById(Integer id);

	public List<CustomForm> findCustomFormList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertCustomForm(CustomForm customForm);

	public Boolean updateCustomForm(CustomForm customForm);

	public Boolean delCustomFormById(Integer id);

	public Boolean delCustomFormByIds(Integer[] ids);

	public Integer getCustomFormListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);


}
