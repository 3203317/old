package net.newcapec.sca.fieldprep.service;

import java.util.List;

import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface FieldPrepService {

	public FieldPrep getFieldPrepById(String sessionId, Integer id);

	public List<FieldPrep> findFieldPrepList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit);

	public List<FieldPrep> findFieldPrepListByDsCode(String sessionId, Integer resourceId, String ds_code, String ds_type, String ds_sql, String user, String password, String ip, String port, String server);

	public DojoListData findFieldPrepDojoList(DojoListParam param);

	public FieldPrep insertFieldPrep(String sessionId, FieldPrep fieldPrep);

	public FieldPrep updateFieldPreps(String sessionId, Integer ds_code, List<FieldPrep> fieldPreps);

	public FieldPrep updateFieldPrep(String sessionId, FieldPrep fieldPrep);

	public FieldPrep delFieldPrepById(String sessionId, Integer id);

	public FieldPrep delFieldPrepByIds(String sessionId, String ids);

	public Integer getFieldPrepListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter);

	public List<FieldPrep> findFieldListByDscode(String sessionId,Integer ds_code);// zpf添加

}
