package net.newcapec.sca.dbconn.service;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.dbconn.DBConnection;
@Remotable
public interface DefDBConnService {
	DBConnection updateDefDBConn(DBConnection dbConn);
	DBConnection getDefDBConnByName(String name);
	DBConnection getDefDBConn();
}
