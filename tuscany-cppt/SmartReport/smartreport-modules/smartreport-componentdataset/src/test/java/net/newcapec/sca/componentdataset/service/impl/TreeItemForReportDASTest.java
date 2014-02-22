/**
 * 
 */
package net.newcapec.sca.componentdataset.service.impl;

import net.newcapec.sca.componentdataset.das.TreeItemForReportDAS;

import org.apache.log4j.Logger;
import org.apache.tuscany.sca.Node;
import org.apache.tuscany.sca.TuscanyRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class TreeItemForReportDASTest {
    private static final Logger _log = Logger
    .getLogger(TreeItemForReportDASTest.class);

    private  static   Node node;
    private static  TreeItemForReportDAS  treeitemforreportdas;
    @BeforeClass
    public static  void  setUp()  throws Exception {
         node = TuscanyRuntime.runComposite("TreeItemForReportDAS.composite","target/test-classes");
         treeitemforreportdas = node.getService(TreeItemForReportDAS.class,"TreeItemForReportDASComponent");
         
    }
    
    @Test
    public void getTreeItemsByComponentDSCodeTest()
    {
        try
        {
            String str = treeitemforreportdas.getTreeItemsByComponentDSCode(181, "code", "name", "super_code","0");
            
            System.out.println(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @AfterClass
    public  static void tearDown()
     {
         node.stop();
     }
}
