package test;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestSession {

	@Test
	public void SessionTest() throws NoSuchServiceException{
		Node node = TuscanyRuntime.runComposite("session.composite", "target/test-classes");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				SessionService sessionService = node.getService(SessionService.class, "SessionComponent");
				Session session = new Session();
				//session.setAge("123401111");
				//session.setId("1211");
				//session.setUpdate_time(sdf.format(new Date()));
				session.setDomain_code("1");
				session.setUnit_code("1");
				session.setUser_code("12312312");
				//session.setContent("");
				//session.setInvalid_date("");
				Session __session = sessionService.createSession(session);
				System.out.println("----------"+__session);
				System.out.println("----------"+__session.getId());
				System.out.println("----------"+__session.getState());
				//sessionService.getSession("bf65b36de2a94f15a69d3912e28b6a8c");
				//sessionService.invalid("bf65b36de2a94f15a69d3912e28b6a8c");
			   // Assert.assertEquals("Hello Amelia", sessionService.sayHello("Amelia"));
			} finally {
				// Stop the Tuscany runtime Node
				node.stop();
			}
	}

}
