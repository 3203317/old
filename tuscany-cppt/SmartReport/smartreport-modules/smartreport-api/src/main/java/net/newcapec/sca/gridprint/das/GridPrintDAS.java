package net.newcapec.sca.gridprint.das;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.param.FilterParam;

public interface GridPrintDAS {
	public GridPrint getGridPrintById(Integer id);
	public List<GridPrint> findGridPrintList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer beginId, Integer limitId);
	public Boolean insertGridPrint(GridPrint gridPrint);
	public Boolean updateGridPrint(GridPrint gridPrint);
	public Boolean delGridPrintById(Integer id);

}
