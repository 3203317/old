package net.newcapec.sca.fieldprep.das;

import java.util.List;

import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface FieldPrepDAS {
	public FieldPrep getFieldPrepById(Integer id);

	public List<FieldPrep> findFieldPrepList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertFieldPrep(FieldPrep fieldPrep);

	public Boolean updateFieldPrep(FieldPrep fieldPrep);

	public Boolean delFieldPrepById(Integer id);

	public Boolean delFieldPrepByDsId(Integer id);

	public Boolean delFieldPrepByIds(Integer[] ids);

	public Integer getFieldPrepListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

	public List<FieldPrep> findFieldByDscode(Integer ds_code);

}
