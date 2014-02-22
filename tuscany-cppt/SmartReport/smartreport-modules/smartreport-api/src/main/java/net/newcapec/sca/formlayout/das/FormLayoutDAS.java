package net.newcapec.sca.formlayout.das;

import java.util.List;

import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.param.FilterParam;

public interface FormLayoutDAS {

	public FormLayout getFormLayoutById(Integer id);
	public List<FormLayout> findFormLayoutList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit);
	public Boolean insertFormLayout(FormLayout formLayout);
	public Boolean updateFormLayout(FormLayout formLayout);
	public Boolean delFormLayoutByIds(Integer[] ids);

	public  Integer  getFormLayoutListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}
