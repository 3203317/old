package net.newcapec.sca.datasource.service;

import java.util.List;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.ProtoDBField;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;

import org.json.JSONObject;
import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface DataSourceService {
	public DataSource getDataSourceById(String sessionId, Integer id);

	public List<DataSource> findDataSourceList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit);

	public DojoListData findDataSourceDojoList(DojoListParam param);

	public DataSource insertDataSource(String sessionId, DataSource datasource);

	public DataSource updateDataSource(String sessionId, DataSource datasource);

	public DataSource delDataSourceById(String sessionId, Integer id);

	public DataSource delDataSourceByIds(String sessionId, String ids);

	public Integer getDataSourceListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter);

	//
	public DojoListData findUndefinedDojoListByCondition(DojoListParam param);

	public List<String> getDbAllTablesByUser(String sessionId, String user, String password, String ip, String port, String server);

	public List<String> getDbAllViewsByUser(String sessionId, String user, String password, String ip, String port, String server);

	public List<ProtoDBField> getDbAllTableFieldsByUser(String sessionId, String user, String password, String ip, String port, String server, String tableName);

	public List<String> getDbAllProcByUser(String sessionId, String user, String password, String ip, String port, String server);

	/**
	 * 测试存储过程是否正常
	 *
	 * @param sessionId
	 * @param user
	 * @param password
	 * @param ip
	 * @param port
	 * @param server
	 * @param procName
	 * @return
	 */
	public Boolean testProc(String sessionId, String user, String password, String ip, String port, String server, String procName);

	/**
	 * 测试Sql语句是否正常
	 *
	 * @param sessionId
	 * @param user
	 * @param password
	 * @param ip
	 * @param port
	 * @param server
	 * @param sql
	 * @return
	 */
	public Boolean testSql(String sessionId, String user, String password, String ip, String port, String server, String sql);

	/**
	 * zpf add 根据表单定制的向导配置，动态生成报表xml文件
	 */
	public String createReportXml(String sessionId, Integer form_code, List<JSONObject> griddata);
}
