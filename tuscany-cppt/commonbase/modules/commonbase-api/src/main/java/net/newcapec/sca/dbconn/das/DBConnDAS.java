package net.newcapec.sca.dbconn.das;

import java.sql.Connection;
import java.util.List;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.param.FilterParam;

public interface DBConnDAS {
    public DBConnection getDBConnById(Integer id);
    public List<DBConnection> findDBConnList(Integer domainId, Integer orgId,
            List<FilterParam> filter, Integer beginId, Integer limit);
    public Boolean insertDBConn(DBConnection org);
    public Boolean updateDBConn(DBConnection org);
    public Boolean delDBConnById(Integer id);
    public Integer getDBConnListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter);
    public Boolean getDBConfig2Connection(DBConnection db);
    
    public Connection getJavaConnectionBYConfigId (Integer id);


}
