package net.newcapec.sca.operation.service;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.operation.Operation;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface OperationService {
	public Operation getOperationById(String sessionId,Integer id);
	public List<Operation> findOperationListByType(String sessionId,Integer typeId);
	public List<Operation> findOperationList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit);
	public Operation insertOperation(String sessionId,Operation operation);
	public Operation updateOperation(String sessionId,Operation operation);
	public Operation delOperationById(String sessionId,Integer id);
	public Boolean delOperationByIds(String sessionId,String ids);
	public DojoListData findOperationDojoList(DojoListParam param);
	public List<SelectItem> findSelectItemForResourceType(String sessionId);

}
