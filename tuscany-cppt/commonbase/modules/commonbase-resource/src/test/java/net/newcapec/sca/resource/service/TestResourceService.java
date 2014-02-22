package net.newcapec.sca.resource.service;

import java.util.Date;

import net.newcapec.sca.resource.Resource;
import net.newcapec.sca.resource.bean.Tree;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestResourceService {
	public static Node node;
	public static ResourceService resourceService;
	public static SessionService sessionService;
	public static SequenceService sequenceService;
	public static Long testResourceId;
	public static String sessionId;
	@BeforeClass
	public static void setUp() throws NoSuchServiceException
	{
		node = TuscanyRuntime.runComposite("ResourceService.composite","target/test-classes");
		resourceService = node.getService(ResourceService.class, "ResourceServiceComponent/ResourceService");
		sessionService = node.getService(SessionService.class, "SessionComponent");
		sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
		
		Session session = new Session();
		session.setAge("1800");
		session.setDomain_code("1");
		session.setUnit_code("1");
		session.setUser_code("9");
		session.setContent("dddddsdfds222222");
		session.setInvalid_date("");
		session = sessionService.createSession(session);
		sessionId = session.getId();
	}


	@Test
	public void insertResource()
	{
		Resource resource = new Resource();
		resource.setResource_type_code(Long.valueOf(1));
		resource.setName(new Date().toString());
		resource.setStatus(Long.valueOf(1));
		resource.setInfo("zzycs");
		resource.setSys_code(Long.valueOf(1));
		resource.setSuper_code(Long.valueOf(1));
		resource.setIs_have_child(Long.valueOf(1));
		resource.setIs_enabled(Long.valueOf(1));
		//如果没有请给它空值
		resource.setSmall_icon_code("1");
		resource.setLarge_icon_code("1");
		resource.setUuid("11");
		resource.setUrl("zzycs");

		resource.setSortid(Long.valueOf(1));

		try
		{
			
			resourceService.insertResource(sessionId,resource);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		testResourceId = Long.valueOf(Long.valueOf(sequenceService.getCurrentValue("getMaxResourceID")));
	}

	@Test
	public void updateResource()
	{
		Resource resource = new Resource();

		resource.setCreate_user_account_id(Long.valueOf(2));
		resource.setUser_unit_code(Long.valueOf(1));
		resource.setCode(testResourceId);
		try
		{
			resourceService.updateResource(sessionId,resource);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("修改失败");
		}
	}
	@Test
	public void getSysMenuTreesTest()
	{
		try
		{
			Tree[] treeArray = resourceService.getTrees(sessionId);
			System.out.println(treeArray.length);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteResource()
	{
		try
		{
			resourceService.delResourceById(sessionId, testResourceId.intValue());
		}
		catch (Exception e)
		{
			System.out.println("删除失败");
			e.printStackTrace();
		}
	}
	@Test
	public void testGetResourceListRowCount()
	{
		System.out.println(resourceService.getResourceListRowCount(sessionId,null));
	}
	@AfterClass
	public static void tearDown()throws NoSuchServiceException
	{
		sessionService.invalid(sessionId);
		node.stop();
	}

}
