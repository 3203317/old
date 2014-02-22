package net.newcapec.sca.gridcondition.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.gridcondition.GridStatDataCollection;
import net.newcapec.sca.param.FilterParam;
@Remotable
public interface GridStatDataCollectionService {
	public GridStatDataCollection getGridStatDataCollection(String sessionId,Integer ds_code,List<FilterParam> filter);
}
