package net.newcapec.sca.dbconn.service;

import java.util.List;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;
@Remotable
public interface DBConnService {

	public DBConnection getDBConnById(String SessionId,Integer id);
	public List<DBConnection> findDBConnList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer beginId, Integer limit);
	public Boolean insertDBConn(String SessionId,DBConnection org);
	public Boolean updateDBConn(String SessionId,DBConnection org);
	public Boolean delDBConnById(String SessionId,Integer id);
	public Integer getDBConnListRowCount(String sessionId, List<FilterParam> filter);
	public DojoListData findDBConnDojoList(DojoListParam param);
	public Boolean delDBConnByIds(String sessionId, String ids);
	public Boolean getDBConfig2Connection(String sessionId ,DBConnection db);

}
