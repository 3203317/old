package net.newcapec.sca.dbconn;


import net.newcapec.sca.sequence.Sequence;
import net.newcapec.sca.sequence.SequenceInit;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.oasisopen.sca.NoSuchServiceException;

public class TestSequenceInit {

    private static final Logger log = Logger.getLogger(TestSequenceInit.class);

    private static  Node node;

    private static Thread t1;
    private static Thread t2;
    private static Thread t3;

    @Before
    public void setUp()
    {
        System.out.println("begin TestSequenceService ==============================");
        node = TuscanyRuntime.runComposite("sequenceInitService.composite",
        "target/test-classes");
    }
    @Test
    public void TestGetNextValue() throws NoSuchServiceException , InterruptedException
    {
        SequenceInit seqI = node.getService(SequenceInit.class, "SequenceInitComponent");
        SequenceInit seqI1 = node.getService(SequenceInit.class, "SequenceInitComponent");
        
        final Sequence seq = seqI.getSequence("getMaxResourceID");
        final Sequence seq1 = seqI1.getSequence("getMaxResourceID");
        
        t1 = new Thread(new Runnable() {
            public void run() { System.out.println("seqat1 : " + seq1.getNextValue()); }
        });
        t3 = new Thread(new Runnable() {
            public void run() { System.out.println("seqat3 : " + seq1.getNextValue()); }
        });
        t2 = new Thread(new Runnable() {
            public void run() { System.out.println("seqat2 : " +seq.getNextValue());}
        });

        for (int i = 0 ; i < 10 ; i++)
        {
            System.out.println( i + "----------------------------------------");
            t1.run();
            t2.run();
            t3.run();
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
