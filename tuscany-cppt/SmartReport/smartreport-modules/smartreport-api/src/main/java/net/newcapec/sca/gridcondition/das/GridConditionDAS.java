package net.newcapec.sca.gridcondition.das;

import java.util.List;

import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.param.FilterParam;

public interface GridConditionDAS {
	public GridCondition getGridConditionById(Integer id);
	public List<GridCondition> findGridConditionList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertGridCondition(GridCondition gridCondition);
	public Boolean updateGridCondition(GridCondition gridCondition);
	public Boolean delGridConditionById(Integer id);
	// zpf add 根据form——code查询gridconditions
	public List<GridCondition> getByFormCode(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId);
}
