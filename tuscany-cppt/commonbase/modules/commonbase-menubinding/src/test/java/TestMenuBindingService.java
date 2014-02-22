import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.menubinding.MenuBinding;
import net.newcapec.sca.menubinding.service.MenuBindingService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;


public class TestMenuBindingService {
    public static Node node;
    public static MenuBindingService menuBindingService;
    public static SessionService sessionService;
    public static SequenceService sequenceService;
    public static Long testMenuBindId;
    public static String sessionId;
    @BeforeClass
    public static void setUp() throws NoSuchServiceException
    {
        node = TuscanyRuntime.runComposite("MenuBindingService.composite","target/test-classes");
        menuBindingService = node.getService(MenuBindingService.class, "MenuBindingServiceComponent");
        sessionService = node.getService(SessionService.class, "SessionComponent");
        sequenceService = node.getService(SequenceService.class, "SequenceServiceComponent");
        
        Session session = new Session();
        session.setAge("1800");
        session.setDomain_code("1");
        session.setUnit_code("1");
        session.setUser_code("1");
        session.setContent("dddddsdfds222222");
        session.setInvalid_date("");
        session = sessionService.createSession(session);
        sessionId = session.getId();
    }

    @Test
    public void insertMenuBind()
    {


        MenuBinding menuBinding = new MenuBinding();
        menuBinding.setForm_code("1");
        menuBinding.setLimit("1");
        menuBinding.setMemo("111");
        menuBinding.setMenu_code("1");
        menuBinding.setType("1");

        menuBindingService.insertMenuBinding(sessionId, menuBinding);
        testMenuBindId = Long.valueOf(sequenceService.getCurrentValue("getMaxMenuBindingID"));
    }
    @Test
    public void getDoJoList()
    {
        DojoListParam dolist = new DojoListParam();
        dolist.setSessionId(sessionId);
        List<FilterParam> list = new ArrayList<FilterParam>();
        FilterParam filter = new FilterParam();
        filter.setField("code");
        filter.setLogical("=");
        filter.setRelation("and");
        filter.setValue("1");
        list.add(filter);
        dolist.setFilter(list);
        dolist.setBegin(0);
        dolist.setLimit(5);
        System.out.println(menuBindingService.findMenuBindingDojoList(dolist).getItems().length);
    }
    @Test
    public void updateMenuBind()
    {
        MenuBinding menuBinding = new MenuBinding();
        menuBinding.setCode(testMenuBindId.toString());
        menuBinding.setMemo("祝元111测试");

        menuBindingService.updateMenuBinding(sessionId, menuBinding);
        System.out.println(menuBindingService.findMenuBindingList(sessionId, null, null, null, null).size());
    }
    
    @Test
    public void deleteMenuBind()
    {
        menuBindingService.delMenuBindingById(sessionId, testMenuBindId.intValue());
    }
    @AfterClass
    public static void tearDown()
    {
        sessionService.invalid(sessionId);
        node.stop();
    }
}
