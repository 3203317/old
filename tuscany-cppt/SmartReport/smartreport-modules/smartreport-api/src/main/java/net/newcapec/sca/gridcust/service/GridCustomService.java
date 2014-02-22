package net.newcapec.sca.gridcust.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.gridcust.GridCustom;
import net.newcapec.sca.param.FilterParam;

/**
 * <p>Title: service接口 </p>
 * <p>Description:查询浏览列表用户自定义配置业务构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-25
 * 修改日期：
 * 修改人：
 * 复审人：
 */

@Remotable
public interface GridCustomService {

	public  GridCustom getGridCustomById(String sessionId,Integer id);
	public  List<GridCustom> findGridCustomList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer begin, Integer limit);
	public  GridCustom insertGridCustom(String sessionId,GridCustom gridCustom);
	public  GridCustom updateGridCustom(String sessionId,GridCustom gridCustom);
	public  GridCustom delGridCustomById(String sessionId,Integer id);
	public  GridCustom delGridCustomByIds(String sessionId, String ids);

	public  DojoListData findGridCustomDojoList(DojoListParam param);
	public  Integer  getGridCustomListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}
