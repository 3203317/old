package net.newcapec.sca.componentdataset.service;

import java.util.List;

import org.json.JSONObject;
import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;

/**
 * <p>Title: 接口 </p>
 * <p>Description:控件绑定数据集业务构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

@Remotable
public interface ComponentDatasetService {

	public  ComponentDataset  getComponentDatasetById(String sessionId,Integer id);
	public  List<ComponentDataset>  findComponentDatasetList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) ;
	public  DojoListData findComponentDatasetDojoList(DojoListParam param);
	public  Integer  getComponentDatasetListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);
	public  ComponentDataset insertComponentDataset(String sessionId, ComponentDataset componentDataset);
	public  ComponentDataset  updateComponentDataset(String sessionId, ComponentDataset  componentDataset);
	public  ComponentDataset  delComponentDatasetByIds(String sessionId, String ids);
	public  DojoListData obtainComponentDatasource(String sessionId);
	public  DojoListData  obtainFieldByDatasource( String sessionId,String dsCode);

}
