package net.newcapec.sca.user.das;

import net.newcapec.sca.user.UserBaseInfo;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestUserPasswordDAS {

	private static Node node;
	private UserPasswordDAS userPasswordDAS;

	@Before
	public void setUp() throws Exception {
		// Run the SCA composite in a Tuscany runtime
		node = TuscanyRuntime.runComposite("userPasswordDAS.composite",
				"target/test-classes");
		this.userPasswordDAS = node.getService(UserPasswordDAS.class,
		"UserPasswordDASComponent");
	}

	@Test
	public void testGetPassword() throws NoSuchServiceException {

		System.out.println("1111");

		UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setName("pxx");
		userBaseInfo.setAccount_id("12");

		UserBaseInfo temp = new UserBaseInfo();
		temp  = userPasswordDAS.getPassword(userBaseInfo);
		if  (temp != null)
		   System.out.println("原始密码为："+temp.getOpassword());
	}

	@Test
	public void testResetPassword() throws NoSuchServiceException {

		System.out.println("2222");
		UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setName("pxx");
		userBaseInfo.setAccount_id("12");
		userBaseInfo.setPassword("2222");

		userPasswordDAS.resetPassword(userBaseInfo);
		UserBaseInfo temp = new UserBaseInfo();
		temp  = userPasswordDAS.getPassword(userBaseInfo);
		if (temp != null)
		   System.out.println("重置后的密码为："+temp.getPassword());;
	}

	@Test
	public void testUpdatePassword() throws NoSuchServiceException {

        System.out.println("3333");
		UserBaseInfo userBaseInfo = new UserBaseInfo();
		userBaseInfo.setName("pxx");
		userBaseInfo.setAccount_id("12");
		userBaseInfo.setOpassword("22222");
		userBaseInfo.setPassword("8888");
		userPasswordDAS.updatePassword(userBaseInfo);
		UserBaseInfo temp = new UserBaseInfo();
		temp  = userPasswordDAS.getPassword(userBaseInfo);
		if (temp != null)
		  System.out.println("修改后的密码为："+temp.getPassword());;
	}

	@After
	public void tearDown()
	{
		node.stop();
	}

}
