package net.newcapec.sca.componentdataset.das;

import java.util.List;

import org.json.JSONObject;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.param.FilterParam;

/**
 * <p>Title: DAS接口</p>
 * <p>Description:控件绑定数据集数据构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public interface ComponentDatasetDAS {
	public  ComponentDataset getComponentDatasetById(Integer id);
	public  List<ComponentDataset> findComponentDatasetList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);
	public  Integer  getComponentDatasetListRowCount(Integer resourceId, List<FilterParam> filter);
	public  Boolean  insertComponentDataset(ComponentDataset  componentDataset);
	public  Boolean  updateComponentDataset(ComponentDataset  componentDataset);
	public  Boolean  delComponentDatasetByIds(Integer[] ids);
	public  List<JSONObject> obtainComponentDatasource() ;
	public List<JSONObject>  obtainFieldByDatasource( String dsCode);

}
