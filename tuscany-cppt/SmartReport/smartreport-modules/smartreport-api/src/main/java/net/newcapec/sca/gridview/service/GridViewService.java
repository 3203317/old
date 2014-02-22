package net.newcapec.sca.gridview.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface GridViewService {
	public GridView getGridViewById(String sessionId, Integer id);

	public DojoListData findGridViewList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public GridView insertGridView(String sessionId, GridView gridview);

	public GridView updateGridView(String sessionId, GridView gridview);

	public GridView delGridViewById(String sessionId, Integer id);

	public GridView delGridViewByIds(String sessionId, String ids);
}
