package net.newcapec.sca.operation.das;

import java.util.List;

import net.newcapec.sca.SelectItem;
import net.newcapec.sca.operation.Operation;
import net.newcapec.sca.param.FilterParam;

public interface OperationDAS
{
	public Operation getOperationById(Integer id);
	public List<Operation> findOperationList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertOperation(Operation org);
	public Boolean updateOperation(Operation org);
	public Boolean delOperationById(Integer id);
	public List<Operation> findOperationListByType(Integer typeId);
	public Integer getOperationListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter);
	public List<SelectItem> findSelectItemForResourceType();
	public List<Operation> findDetailOperationList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId);
}
