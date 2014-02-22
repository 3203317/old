package net.newcapec.sca.diccode.service;

/**
 * <p>Title: service实现 </p>
 * <p>Description:自定义字典代码业务构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-31
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import java.util.List;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;

import org.json.JSONObject;
import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface DicCodeService {
	public DicTable getDicTableByTableId(String sessionId,Integer tableId);

	public DicItem getDicItemByItemId(String sessionId,Integer itemId);

	public List<DicItem> findDicItemListByTableId(Integer tableId);

	public List<DicTable> findDicTableList(String sessionId, Integer resourceId,
			                                List<FilterParam> filter, Integer begin, Integer limit);
	public DicTable insertDicTable(String sessionId,DicTable dicTable);

	public DicItem insertDicItem(String sessionId,DicItem dicItem);

	public DicTable updateDicTable(String sessionId,DicTable dicTable);

	public DicItem updateDicItem(String sessionId,DicItem dicItem);

	public DicTable delDicCodeByTableId(String sessionId,Integer id);

	public DicItem delDicCodeByItemId(String sessionId,Integer id);

	public DicTable delDicCodeByTableIds(String sessionId, String ids);

	public DicItem delDicCodeByItemIds(String sessionId, String ids);

	public  DojoListData findDicTableDojoList(DojoListParam param);

	public  DojoListData  findDicItemDojoListByTableId(DojoListParam param);

	public  Integer  getDicTableListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

	public List<DicItem> findDicItemListByCondition(String sessionId,List<FilterParam> filter);

	public DojoListData findDicItemDojoListByCondition(DojoListParam param);

	public DojoListData  getDicTableDojoList(DojoListParam param) ;


}
