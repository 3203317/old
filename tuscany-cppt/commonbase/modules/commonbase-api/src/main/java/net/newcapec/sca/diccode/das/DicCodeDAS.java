package net.newcapec.sca.diccode.das;

/**
 * <p>Title: service实现 </p>
 * <p>Description:自定义字典代码数据访问数据访问构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-31
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.util.List;

import org.json.JSONObject;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.param.FilterParam;

public interface DicCodeDAS {
	public DicTable getDicTableByTableId(Integer tableId);
	public DicItem getDicItemByItemId(Integer itemId);
	public List<DicItem> findDicItemListByTableId(Integer tableId);
	public List<DicTable> findDicTableList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertDicTable(DicTable dicTable);
	public Boolean insertDicItem(DicItem dicItem);
	public Boolean updateDicTable(DicTable dicTable);
	public Boolean updateDicItem(DicItem dicItem);
	public Boolean delDicCodeByTableId(Integer id);
	public Boolean delDicCodeByItemId(Integer id);

	public Boolean delDicCodeByTableIds(Integer[] ids);
	public Boolean delDicCodeByItemIds(Integer[] ids);

	public  Integer  getDicTableListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

	public List<DicItem> findDicItemListByCondition(List<FilterParam> filter);
	public  Integer  getDicItemListByConditionRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);
	public  Integer  judgedelDicCode(Integer[] ids);
	public List<JSONObject> getDicTableList()  ;


}
