package net.newcapec.sca.gridfield.das;

import java.util.List;

import net.newcapec.sca.gridfield.GridField;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface GridFieldDAS {
	public GridField getGridFieldById(Integer id);

	public List<GridField> findGridFieldList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertGridField(GridField gridField);

	public Boolean updateGridField(GridField gridField);

	public Boolean delGridFieldById(Integer id);

	public Boolean delGridFieldByIds(Integer[] ids);
	public  List<GridField>  findGridFieldListByCondition(List<FilterParam> filter);
}
