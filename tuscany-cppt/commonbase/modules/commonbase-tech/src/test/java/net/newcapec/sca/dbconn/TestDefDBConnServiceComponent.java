package net.newcapec.sca.dbconn;

import net.newcapec.sca.dbconn.service.DefDBConnService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestDefDBConnServiceComponent
{
	private static final Logger _log = Logger.getLogger(TestDefDBConnServiceComponent.class);

	private static  Node node;

	private static DefDBConnService defDBConnService;


	@Before
	public void setUp() throws Exception {
		node = TuscanyRuntime.runComposite("defDBConnectionService.composite",
		"target/test-classes");
		// Get the Helloworld service proxy
		defDBConnService = node.getService(DefDBConnService.class,
				"DefDBConnServiceComponent");

	}
	@Test
	public void testUpdateDefDBConn() throws NoSuchServiceException {
		_log.info("testing...");
		// Run the SCA composite in a Tuscany runtime

		try {


			DBConnection dbConn = new DBConnection();
			dbConn.setConfigName("MySQL");
			dbConn.setDataBaseName("MySQL");
			dbConn.setConnectIP("192.168.131.89");
			dbConn.setConnectPort("3306");
			dbConn.setConnectSID("12121");
			dbConn.setAccoutName("root");
			dbConn.setAccountPassword("zpf");
			//_log.debug(defDBConnService.updateDefDBConn(dbConn));
			System.out.println(defDBConnService.getDefDBConnByName("mysql").getConnectSID());
			//defDBConnService.updateDefDBConn(dbConn);

			//System.out.println(convertStreamToString(getClass().getClassLoader().getResourceAsStream("defaultConnectConfig.xml")));

		}
		finally {
			// Stop the Tuscany runtime Node
			node.stop();
		}
	}


}
