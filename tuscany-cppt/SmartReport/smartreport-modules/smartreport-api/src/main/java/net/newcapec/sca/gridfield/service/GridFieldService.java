package net.newcapec.sca.gridfield.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface GridFieldService {

	public GridField getGridFieldById(String sessionId, Integer id);

	public DojoListData findGridFieldList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit);

	public GridField insertGridField(String sessionId, GridField gridField);

	public GridField insertGridFields(String sessionId, List<GridField> gridFields);

	public GridField updateGridField(String sessionId, GridField gridField);

	public GridField delGridFieldById(String sessionId, Integer id);

	public GridField delGridFieldByIds(String sessionId, String ids);

	public Boolean updateGridFields(String sessionId, List<GridField> gridFields);
	public List<GridField> findGridFieldListByCondition(String sessionId, List<FilterParam> filter);
}
