package net.newcapec.sca.dbconn;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.service.DBConnService;
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

public class TestDBConnService {

    public static Node node;
    public static DBConnService dbConnService;
    public static SessionService sessionService;
    public static SequenceService sequenceService;
    public static String sessionId;
    public static Long testDbConnId;
    @BeforeClass
    public static void setUp() throws NoSuchServiceException
    {
        node = TuscanyRuntime.runComposite("DBconnService.composite","target/test-classes");
        dbConnService = node.getService(DBConnService.class, "DBConnServiceComponent");
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
    /**
     * 本方法测试p_dbconn的添加、修改、删除、查找
     */
    @Test
    public void testmanipulateDBConn()
    {
        this.insertDBConn();
        this.updateConn();
        this.getDBConnById();
        this.deleteConn();

    }

    private void insertDBConn()
    {
        DBConnection db = new DBConnection();
        //db.setCode(Long.valueOf(67));
        db.setAccoutName("212");
        db.setAccountPassword("zpf");
        db.setConnectIP("1212");
        db.setConnectPort("1212");
        db.setConnectSID("1212");
        db.setCreate_user_code("1");
        db.setDataBaseName("1");
        db.setDomain_code(Long.valueOf(1));
        db.setEncryption_info("");
        db.setEncryption_timestamp("");
        db.setMemo("");
        db.setName("1212");
        db.setUnit_code(Long.valueOf(1));
        db.setVer(Long.valueOf(1));
        dbConnService.insertDBConn(sessionId,db);
        
        testDbConnId = Long.valueOf(this.sequenceService.getCurrentValue("getMaxDBConnectionID"));
    }
    
    private void getDBConnById()
    {
        DBConnection db = dbConnService.getDBConnById(sessionId,testDbConnId.intValue());
        System.out.println(db.getAccoutName());
    }
    
    private void updateConn()
    {
        DBConnection db = new DBConnection();
        db.setCode(testDbConnId);
        db.setAccoutName("zpf");
        dbConnService.updateDBConn(sessionId,db);

    }
    
    private void deleteConn()
    {
        dbConnService.delDBConnById(sessionId,testDbConnId.intValue());
    }
    @Test
    public void findDBConnList()
    {
        List<FilterParam> filterList = new ArrayList<FilterParam>();
        FilterParam filter = new FilterParam();
        filter.setField("dbtype_code");
        filter.setLogical("=");
        filter.setRelation("and");
        filter.setValue("3");
        filterList.add(filter);
        List list = dbConnService.findDBConnList(sessionId,1,filterList,null,null);
        System.out.println(list.size());
    }
  
    @AfterClass
    public static void tearDown()
    {
        sessionService.invalid(sessionId);
        node.stop();
    }

}
