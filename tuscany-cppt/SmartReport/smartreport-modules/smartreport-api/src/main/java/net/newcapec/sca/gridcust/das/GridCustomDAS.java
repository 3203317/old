package net.newcapec.sca.gridcust.das;

import java.util.List;

import net.newcapec.sca.customform.CustomForm;
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


public interface GridCustomDAS {

	public GridCustom getGridCustomById(Integer id);
	public List<GridCustom> findGridCustomList(Integer domainId, Integer orgId,
		   List<FilterParam> filter, Integer begin, Integer limit);
	public Boolean insertGridCustom(GridCustom gridCustom);
	public Boolean updateGridCustom(GridCustom gridCustom);
	public Boolean delGridCustomById(Integer id);
	public Boolean delGridCustomByIds(Integer[] ids);

	public  Integer  getGridCustomListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);



}
