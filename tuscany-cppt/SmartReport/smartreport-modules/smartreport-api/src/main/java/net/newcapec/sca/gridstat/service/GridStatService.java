package net.newcapec.sca.gridstat.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridstat.GridStat;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface GridStatService {

	public GridStat getGridStatById(String sessionId, Integer id);

	public DojoListData findGridStatList(String sessionId, Integer resourceId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public GridStat insertGridStat(String sessionId, GridStat gridStat);

	public GridStat updateGridStat(String sessionId, GridStat gridStat);

	public GridStat delGridStatById(String sessionId, Integer id);

	public GridStat delGridStatByIds(String sessionId, String ids);
}
