package net.newcapec.sca.sequence;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.sequence.impl.SequenceImpl;
import net.newcapec.sca.util.DASFactory;

import org.apache.tuscany.das.rdb.DAS;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class SequenceInitImpl implements SequenceInit
{
    public static HashMap<String,Sequence> commandSquences = new HashMap<String,Sequence>();
    private DefDBConnService defDBConnService;

    @Reference(name = "defDBConnService", required = true)
    public void setDefDBConnService(DefDBConnService defDBConnService) {
            this.defDBConnService = defDBConnService;
    }

    //默认的功能模块das配置文件
    private static String DAS_CONFIG = "/allsequence.xml";
    public DAS getDAS()
    {
        String path = "";
        path = this.getClass().getResource(DAS_CONFIG).getPath();
        DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
        return das;
    }
    /**
     * 初始化commandSequence
     * @param fileName
     */
    private void initSequenceFactoryContextInfo(String fileName) {
        //取的command列表
        List<String> list = getDefaultSequenceCommandName(fileName);
        //根据command列表取的序列的最大值
        fillMapUseTableName(fileName,list);
    }

    /**
     *读取xml配置文件
     * @param fileName
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<String> getDefaultSequenceCommandName(String fileName) {
        Document doc = load(fileName);
        List<String> list = new ArrayList<String>();
        List<Element> listElement = doc.getRootElement().elements("Command");
        for(Element item : listElement)
        {
            list.add(item.attributeValue("name"));
        }
        return list;
    }
   
    /**
     * 加载配置文件
     * @param filename
     * @return
     */
    private  Document load(String fileName)
    {
        Document document = null;
        try
        {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(getConfig(fileName));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return document;
    }


    private InputStream getConfig(String fileName)
    {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    private void fillMapUseTableName(String fileName,List<String> list)
    {

        DAS das = this.getDAS();

        for(String commandName : list )
        {
            DataObject root = das.getCommand(commandName).executeQuery();
            List<DataObject> commandlist = root.getList("findMax");
            if(null != commandlist && commandlist.size() > 0)
            {
                DataObject obj = commandlist.get(0);
                Sequence seq = new SequenceImpl(new Integer(obj.getString("id")));
                commandSquences.put(commandName,seq);
            }
            else
            {
                Sequence seq = new SequenceImpl(0);
                commandSquences.put(commandName,seq);
            }
        }
    }


    /**
     *根据command名称获得对应的序列对象
     * @param commandName
     * @return
     */
    public Sequence getSequence(String commandName)
    {
        // 判断是否存在，不存在创建，存在返回
        if(null == commandSquences || commandSquences.size() == 0)
        {
            initSequenceFactoryContextInfo("allsequence.xml");
        }
        return commandSquences.get(commandName);
    }
}

