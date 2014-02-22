package net.newcapec.sca.dbconn.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.dbconn.service.DefDBConnService;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 *缺省的数据库连接配置文件：defaultConnectConfig.xml
 *缺省的DAS配置文件：dasConfig.xml
 *
 */
public class DefDBConnServiceImpl implements DefDBConnService {

    private static String CONFIGFILE = "startupconfig.xml";

    private final static String DEFAULCONFDB = "oracle";
    /**
    * @warn: 如果给该方法传递文件流那么调用完成后该文件会自动关闭
    * 载入一个xml文档
    * @return 成功返回Document对象，失败返回null
    * @param uri 文件路径
    */
    private  org.dom4j.Document loadConfigByFileName(String filename)
    {
        org.dom4j.Document document = null;
        try
        {
              SAXReader saxReader = new SAXReader();
            //获得当前class文件所在的jar包
//            String filePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
//            String[] paths = filePath.split("/");
//            StringBuffer needPath = new StringBuffer();
//            //装配需要的路径
//            for(int i=1; i < (paths.length-1);i++)
//            {
//                needPath.append(paths[i]+File.separator);
//            }
//            File file = new File(needPath.toString()+filename);
            //document = saxReader.read(file);
            document = saxReader.read(this.getClass().getClassLoader().getResourceAsStream(filename));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return document;
    }
    /**
     * 获得缺省的jdbc连接对象
     */
    public DBConnection updateDefDBConn(DBConnection dbConn)
    {
        return updateDBconn(dbConn,CONFIGFILE);
    }
    /**
     *修改数据库连接详细信息，根据配置文件和提供的DBConnection bean
     * @param dbConn
     * @param configFileName
     * @return
     */
    @SuppressWarnings("unchecked")
    public DBConnection updateDBconn(DBConnection dbConn,String configFileName)
    {
        org.dom4j.Document doc = loadConfigByFileName(configFileName);

        List<Element> list = doc.getRootElement().elements("dbConfig");
        for(Element item : list)
        {
            if(isConfigNameEqual(item,dbConn.getConfigName()))
            {
                item.selectSingleNode("dataBaseName").setText(dbConn.getDataBaseName()==null?"":dbConn.getDataBaseName());
                item.selectSingleNode("connectIP").setText(dbConn.getConnectIP()==null?"":dbConn.getConnectIP());
                item.selectSingleNode("connectPort").setText(dbConn.getConnectPort()==null?"":dbConn.getConnectPort());
                item.selectSingleNode("connectSID").setText(dbConn.getConnectSID()==null?"":dbConn.getConnectSID());
                item.selectSingleNode("accoutName").setText(dbConn.getAccoutName()==null?"":dbConn.getAccoutName());
                item.selectSingleNode("accountPassword").setText(dbConn.getAccountPassword()==null?"":dbConn.getAccountPassword());
                item.selectSingleNode("marker").setText(dbConn.getMarker()==null?"":dbConn.getMarker());
            }
        }
        outPutModifyFile(doc);
        return dbConn;
    }
    /**
     * 判断item中的name属性和configName是否相等
     * @param item
     * @param configName
     * @return
     */
    private boolean isConfigNameEqual(Element item, String configName)
    {
        return item.elementTextTrim("name").equalsIgnoreCase(configName);
    }
    /**
     * 输出xml文档
     * @param doc
     */
    private void outPutModifyFile(org.dom4j.Document doc)
    {
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding("UTF-8");    // 指定XML编码
    try
    {
        String path = getClass().getClassLoader().getResource(CONFIGFILE).getPath();
        File outputFile = new File(path);
        XMLWriter writer =
            new XMLWriter(new FileWriter(outputFile),format);
        writer.write(doc);
        writer.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    /**
     * 通过configName得到缺省配置文件中的DBConnection bean
     */
    public DBConnection getDefDBConnByName(String name)
    {
        return getDBConnByName(name,CONFIGFILE);
    }
    /**
     * 通过configName得到缺省配置文件中的DBConnection bean
     */
    public DBConnection getDefDBConn()
    {
        return getDefDBConnByName(DEFAULCONFDB);
    }
    /**
     *根据configName和提供的配置文件的到需要的DBConnnection
     * @param name
     * @param configFileName
     * @return
     */
    @SuppressWarnings("unchecked")
    public DBConnection getDBConnByName(String name,String configFileName)
    {
        DBConnection dbConn =  new DBConnection();
        org.dom4j.Document doc = loadConfigByFileName(configFileName);
        List<Element> list = doc.getRootElement().elements("dbConfig");
        for(Element item : list)
        {
            if(isConfigNameEqual(item,name))
            {
                dbConn.setConfigName(name);
                dbConn.setDataBaseName(item.selectSingleNode("dataBaseName").getText());
                dbConn.setConnectIP(item.selectSingleNode("connectIP").getText());
                dbConn.setConnectPort(item.selectSingleNode("connectPort").getText());
                dbConn.setConnectSID(item.selectSingleNode("connectSID").getText());
                dbConn.setAccoutName(item.selectSingleNode("accoutName").getText());
                dbConn.setAccountPassword(item.selectSingleNode("accountPassword").getText());
                dbConn.setMarker(item.selectSingleNode("marker").getText());
            }
        }
        return dbConn;
    }
}
