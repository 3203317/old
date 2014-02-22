package net.newcapec.sca.login.service;

import junit.framework.TestCase;
import net.newcapec.sca.user.UserBaseInfo;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Before;
import org.oasisopen.sca.NoSuchServiceException;

public class TestLoginService extends TestCase {

	private static final Logger _log = Logger.getLogger(TestLoginService.class);

	@Before
	public void setUp() throws Exception {
	}

	public void testLoginVerify() throws NoSuchServiceException {

		_log.info("testing... ");

		// Run the SCA composite in a Tuscany runtime
		Node node = TuscanyRuntime.runComposite("loginService.composite",
				"target/test-classes");
		try {

			// Get the Helloworld service proxy
			LoginService helloworld = node.getService(LoginService.class,
					"LoginServiceComponent");

			UserBaseInfo ubi = new UserBaseInfo();
			ubi.setName("1234");
			ubi.setPassword("8888");

			_log.debug(helloworld.loginVerify(ubi).getSessionId());

			// test that it works as expected
			// assertEquals("Hello Amelia", helloworld.sayHello("Amelia"));

		} finally {
			// Stop the Tuscany runtime Node
			node.stop();
		}
	}

}
