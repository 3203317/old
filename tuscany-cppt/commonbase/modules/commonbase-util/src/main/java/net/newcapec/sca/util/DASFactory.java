package net.newcapec.sca.util;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.util.impl.DASFactoryImpl;

import org.apache.tuscany.das.rdb.DAS;

public interface DASFactory {
	DASFactoryImpl Factory = new DASFactoryImpl();

	DAS createDAS(DBConnection db,String path);

}
