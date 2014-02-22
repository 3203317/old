package net.newcapec.sca.user.service;


import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.service.impl.UserPasswordServiceImpl;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserPasswordService {

	private static final Logger _log = Logger
	.getLogger(UserPasswordServiceImpl.class);
    private static Node node;
    private static UserPasswordService __service;
    public static SessionService sessionService;
	public static String sessionId;

    @Before
	public void  setUp()  throws Exception {

		 node = TuscanyRuntime.runComposite("userPasswordService.composite",
				"target/test-classes");
		  __service = node.getService(UserPasswordService.class,
		 "UserPasswordServiceComponent");
		 sessionService = node.getService(SessionService.class, "SessionComponent");

		 Session session = new Session();
		 session.setAge("123401111");
		 session.setDomain_code("1");
		 session.setUnit_code("1");
		 session.setUser_code("1");
		 session.setContent("dddddsdfds222222");
		 session.setInvalid_date("");
		 session = sessionService.createSession(session);
		 sessionId = session.getId();
	}

    @Test
    public  void  testResetPassword() throws Exception{

        System.out.println("1111");
        UserBaseInfo userBaseInfo = new UserBaseInfo();
	    userBaseInfo.setName("pxx");
	    userBaseInfo.setPassword("6666");
	    __service.resetPassword(sessionId, userBaseInfo);
    }

    @Test
    public  void  testUpdatePassword() throws Exception{
    	System.out.println("2222");
    	UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setName("pxx");
		userBaseInfo.setOpassword("6666");
		userBaseInfo.setPassword("8888");
		 __service.updatePassword(sessionId, userBaseInfo);
    }

    @Test
    public  void  testEncodePassword() throws Exception{
        UserBaseInfo userBaseInfo = new UserBaseInfo();
 	    userBaseInfo.setName("pxx");
 	    userBaseInfo.setPassword("1");
       	UserBaseInfo userBaseInfoMD5 ;
    	userBaseInfoMD5 = __service.encodePassword(userBaseInfo);
    	System.out.println(userBaseInfoMD5.getPassword());
    }

    @After
    public void tearDown()
	 {
    	 sessionService.invalid(sessionId);
	     node.stop();
	 }



}
