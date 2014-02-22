package net.newcapec.sca.gridcondition.service;

import java.util.List;

import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridcondition.GridConditonDataCollection;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;
@Remotable
public interface GridConditionService {
	public GridCondition getGridConditionById(String sessionId, Integer id);
	public List<GridCondition> findGridConditionList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limitId);
	public GridCondition insertGridCondition(String sessionId, GridCondition gridCondition);
	public GridCondition updateGridCondition(String sessionId, GridCondition gridCondition);
	public GridCondition delGridConditionById(String sessionId, Integer id);

	public Boolean updateGridConditions(String sessionId,List<GridCondition> gridConditions);

	public Boolean insertGridConditions(String sessionId,
			List<GridCondition> gridConditions);
	/**
	 *
	 * @author 赵鹏飞
	 * 描述：根据sessionId和表单id查询查询条件管理的相关数据
	 * 时间： 2012-12-5
	 * GridConditionService.java
	 * @param sessionId
	 * @param form_code
	 * @return
	 */
	public List<GridCondition> getGridConditionByFormCode(String sessionId,Integer form_code);
	/**
	 *
	 * @author 赵鹏飞
	 * 描述：根据sessionId和表单id,数据源id和查询条件，查询field,view,condition,dicitem的相关信息
	 * 时间： 2012-12-5
	 * GridConditionService.java
	 * @param sessionId
	 * @param form_code
	 * @return
	 */
	public GridConditonDataCollection getGridConditonDataCollection(String sessionId,Integer ds_code,Integer form_code,List<FilterParam> filter);
}
