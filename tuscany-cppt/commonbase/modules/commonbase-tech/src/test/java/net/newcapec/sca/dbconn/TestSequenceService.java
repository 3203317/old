package net.newcapec.sca.dbconn;


import net.newcapec.sca.sequence.SequenceService;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestSequenceService {

    private static final Logger log = Logger.getLogger(TestSequenceService.class);

    private static  Node node;

    private static Thread t1;
    private static Thread t2;

    @Before
    public void setUp()
    {
        System.out.println("begin TestSequenceService ==============================");
        node = TuscanyRuntime.runComposite("TestSequenceService.composite",
        "target/test-classes");
    }
    @Test
    public void TestGetNextValue() throws NoSuchServiceException , InterruptedException
    {
        final SequenceService seq = node.getService(SequenceService.class, "SequenceServiceComponent/SequenceService");
        final SequenceService seq1 = node.getService(SequenceService.class, "SequenceServiceComponent/SequenceService");
        t1 = new Thread(new Runnable() {
            public void run() { System.out.println("seqat1 : " + seq1.getNextValue("getMaxResourceID")); }
        });
        t2 = new Thread(new Runnable() {
            public void run() { System.out.println("seqat2 : " +seq.getNextValue("getMaxResourceID"));}
        });

        for (int i = 0 ; i < 10 ; i++)
        {
            System.out.println( i + "----------------------------------------");
            t1.run();
            t2.run();
            System.out.println( i + "+++++++++++++++++++++++++++++++++++++++++");
        }

    }
    @After
    public void tearDown()
    {
        t1.stop();
        t2.stop();
        node.stop();
        System.out.println("end TestSequenceFactory ==============================");
    }
}
