package net.newcapec.sca.datasource.das;

import java.util.List;
import java.util.Map;

import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.json.JSONObject;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.diccode.DicTable;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public interface DataSourceDAS {
	public DataSource getDataSourceById(Integer id);

	public List<DataSource> findDataSourceList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertDataSource(DataSource datasource);

	public Boolean updateDataSource(DataSource datasource);

	public Boolean delDataSourceById(Integer id);

	public Boolean delDataSourceByIds(Integer[] ids);

	public Integer getDataSourceListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

	public  Map<String,Object> findUndefinedListByCondition(String sessionId, Integer resourceId,
            List<FilterParam> filter, Integer begin, Integer limit)  throws Exception ;


}
