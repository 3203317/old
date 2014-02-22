package net.newcapec.sca.gridstat.das;

import java.util.List;

import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface GridStatDAS {
	public GridStat getGridStatById(Integer id);

	public List<GridStat> findGridStatList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertGridStat(GridStat gridStat);

	public Boolean updateGridStat(GridStat gridStat);

	public Boolean delGridStatById(Integer id);

	public Boolean delGridStatByIds(Integer[] ids);

}
