package net.newcapec.sca.formlayout.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.formlayout.FormLayout;
import net.newcapec.sca.param.FilterParam;

@Remotable
public interface FormLayoutService {


	public FormLayout getFormLayoutById(String sessionId,Integer id);
	public List<FormLayout> findFormLayoutList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit);
	public FormLayout insertFormLayout(String sessionId,FormLayout formLayout);
	public FormLayout updateFormLayout(String sessionId,FormLayout formLayout);
	public FormLayout delFormLayoutById(String sessionId,Integer id);
	public FormLayout delFormLayoutByIds(String sessionId, String ids);

	public  DojoListData findFormLayoutDojoList(DojoListParam param);
	public  Integer  getFormLayoutListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);


}
