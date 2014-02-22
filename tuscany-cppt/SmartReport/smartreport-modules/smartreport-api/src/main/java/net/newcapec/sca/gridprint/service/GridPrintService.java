package net.newcapec.sca.gridprint.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.gridprint.GridFormDataCollection;
import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.param.FilterParam;
@Remotable
public interface GridPrintService {
	public GridPrint getGridPrintById(String sessionId,Integer id);
	public List<GridPrint> findGridPrintList(String sessionId,
			Integer resourceId, List<FilterParam> filter,  Integer beginId, Integer limitId);
	public GridPrint insertGridPrint(String sessionId,GridPrint gridPrint);
	public GridPrint updateGridPrint(String sessionId,GridPrint gridPrint);
	public GridPrint delGridPrintById(String sessionId,Integer id);
	public GridFormDataCollection getGridFormDataCollection(String sessionId,Integer form_code);

}
