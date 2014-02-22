package net.newcapec.sca.gridview.das;

import java.util.List;

import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface GridViewDAS {
	public GridView getGridViewById(Integer id);

	public List<GridView> findGridViewList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertGridView(GridView gridview);

	public Boolean updateGridView(GridView gridview);

	public Boolean delGridViewById(Integer id);

	public Boolean delGridViewByIds(Integer[] ids);
}
