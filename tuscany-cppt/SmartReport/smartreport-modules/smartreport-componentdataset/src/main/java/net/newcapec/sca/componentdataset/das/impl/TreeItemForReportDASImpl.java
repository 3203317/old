package net.newcapec.sca.componentdataset.das.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.componentdataset.das.TreeItemForReportDAS;
import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.das.DataSourceDAS;
import net.newcapec.sca.dbconn.das.DBConnDAS;
import net.newcapec.sca.resource.bean.MenuItem;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 *
 */
public class TreeItemForReportDASImpl implements TreeItemForReportDAS{

    private Logger treeItemForReportDASLog = Logger.getLogger(TreeItemForReportDASImpl.class);

    private DBConnDAS dbConnDAS;

    @Reference(name = "dbConnDAS", required = true)
    public void setDBConnDAS(DBConnDAS dbConnDAS) {
            this.dbConnDAS = dbConnDAS;
    }
    private DataSourceDAS dataSourceDAS;

    @Reference(name = "dataSourceDAS", required = true)
    public void setDataSourceDAS(DataSourceDAS dataSourceDAS) {
            this.dataSourceDAS = dataSourceDAS;
    }

    public String getTreeItemsByComponentDSCode(Integer dataSource_code,String code, String name,String super_code,String parentInfo)
    {

        Connection conn = null;
        DataSource dataSource = null;

        dataSource = dataSourceDAS.getDataSourceById(dataSource_code);

        String sourceMethod = dataSource.getMethod();

        if(null == dataSource && (sourceMethod ==null || sourceMethod.trim().equals("")))
        {
            return null;
        }
        if( null != dataSource.getDbconn_code())
        {
            //conn 是设置了autoCommit(false)
            conn = dbConnDAS.getJavaConnectionBYConfigId(dataSource.getDbconn_code());
        }

        if( null == conn)
        {
            return null;
        }
        DAS das = DAS.FACTORY.createDAS(conn);


        String sql = "select " + code + "," + name + "," + super_code + " from \n ( "
        + sourceMethod +" ) tree  where " +super_code + " = " + parentInfo ;
        treeItemForReportDASLog.debug("查询树基本信息 ： "+ sql);
        Command command = das.createCommand(sql);
        //组装查询结果集的描述信息
        List<ResultDescriptor> list = preFillResultDesc(code,name,super_code);
        command.setResultDescriptors(list);

        DataObject root = command.executeQuery();
        List<DataObject> doList = root.getList("tree");
        List<MenuItem> rtnList = new ArrayList<MenuItem>();
        if(doList != null || doList.size() >= 1)
        {
            for(DataObject item : doList)
            {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(item.getString(code));
                menuItem.setName(item.getString(name));
                menuItem.setParent(item.getString(super_code));
                String findChildrenSQL = "select count(1) "+ code + " from \n ( "
                + sourceMethod +" where "+ super_code +" = "+ item.getString(code) + "  ) tree";
                treeItemForReportDASLog.debug("查询下级子节点 ： "+ findChildrenSQL);
                Command countCommand = das.createCommand(findChildrenSQL);
                List<ResultDescriptor> listCount = new ArrayList<ResultDescriptor>();
                ResultDescriptor codeResultDescriptor = new ResultDescriptorImpl();
                codeResultDescriptor.setColumnName(code);
                codeResultDescriptor.setTableName("tree");
                codeResultDescriptor.setColumnIndex(0);
                codeResultDescriptor.setColumnType("commonj.sdo.String");
                listCount.add(codeResultDescriptor);
                countCommand.setResultDescriptors(listCount);

                DataObject countRoot = countCommand.executeQuery();
                DataObject itemChild = (DataObject)(countRoot.getList("tree").get(0));
                if(itemChild.getString(code).equals("0"))
                {
                    menuItem.setType("false");
                }
                else
                {
                    menuItem.setType("true");
                }
                rtnList.add(menuItem);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(MenuItem item: rtnList)
        {
            sb.append("{'id':'"+item.getId()+"','name':'"+item.getName()+"','children':"+item.getType()+"},");
        }
        return sb.toString().substring(0, sb.toString().length()-1);
    }

    public String getTreeItemsByComponentDSCode1(Integer dataSource_code,String code, String name,String super_code,String codeValue)
    {

        Connection conn = null;
        DataSource dataSource = null;

        dataSource = dataSourceDAS.getDataSourceById(dataSource_code);

        String sourceMethod = dataSource.getMethod();

        if(null == dataSource && (sourceMethod ==null || sourceMethod.trim().equals("")))
        {
            return null;
        }
        if( null != dataSource.getDbconn_code())
        {
            //conn 是设置了autoCommit(false)
            conn = dbConnDAS.getJavaConnectionBYConfigId(dataSource.getDbconn_code());
        }

        if( null == conn)
        {
            return null;
        }
        DAS das = DAS.FACTORY.createDAS(conn);
        if("root".equals(codeValue)){
        	codeValue = "0";
        }
        String sql = "select " + code + "," + name + "," + super_code + " from \n ( "
        + sourceMethod +" ) tree  where " +super_code + " = " + codeValue ;
        treeItemForReportDASLog.debug("查询树基本信息 ： "+ sql);
        Command command = das.createCommand(sql);
        //组装查询结果集的描述信息
        List<ResultDescriptor> list = preFillResultDesc(code,name,super_code);
        command.setResultDescriptors(list);

        DataObject root = command.executeQuery();
        List<DataObject> doList = root.getList("tree");
        List<MenuItem> rtnList = new ArrayList<MenuItem>();
        if(doList != null || doList.size() >= 1)
        {
            for(DataObject item : doList)
            {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(item.getString(code));
                menuItem.setName(item.getString(name));
                menuItem.setParent(item.getString(super_code));
                String findChildrenSQL = "select count(1) "+ code + " from \n ( "
                + sourceMethod +" where "+ super_code +" = "+ item.getString(code) + "  ) tree";
                treeItemForReportDASLog.debug("查询下级子节点 ： "+ findChildrenSQL);
                Command countCommand = das.createCommand(findChildrenSQL);
                List<ResultDescriptor> listCount = new ArrayList<ResultDescriptor>();
                ResultDescriptor codeResultDescriptor = new ResultDescriptorImpl();
                codeResultDescriptor.setColumnName(code);
                codeResultDescriptor.setTableName("tree");
                codeResultDescriptor.setColumnIndex(0);
                codeResultDescriptor.setColumnType("commonj.sdo.String");
                listCount.add(codeResultDescriptor);
                countCommand.setResultDescriptors(listCount);

                DataObject countRoot = countCommand.executeQuery();
                DataObject itemChild = (DataObject)(countRoot.getList("tree").get(0));
                if(itemChild.getString(code).equals("0"))
                {
                    menuItem.setType("false");
                }
                else
                {
                    menuItem.setType("true");
                }
                rtnList.add(menuItem);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(MenuItem item: rtnList){
        	if(item.getType() == "false"){
        		sb.append("{'"+code+"':'"+item.getId()+"','"+name+"':'"+item.getName()+"'},");
        	}else{
        		sb.append("{'"+code+"':'"+item.getId()+"','"+name+"':'"+item.getName()+"','children':"+item.getType()+"},");
        	}

        }
        return sb.toString().substring(0, sb.toString().length()-1);
    }

    private List<ResultDescriptor> preFillResultDesc(String code,String name,String super_code) {
        List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
        ResultDescriptor codeResultDescriptor = new ResultDescriptorImpl();
        codeResultDescriptor.setColumnName(code);
        codeResultDescriptor.setTableName("tree");
        codeResultDescriptor.setColumnIndex(0);
        codeResultDescriptor.setColumnType("commonj.sdo.String");
        list.add(codeResultDescriptor);
        codeResultDescriptor = new ResultDescriptorImpl();
        codeResultDescriptor.setColumnName(name);
        codeResultDescriptor.setTableName("tree");
        codeResultDescriptor.setColumnIndex(1);
        codeResultDescriptor.setColumnType("commonj.sdo.String");
        list.add(codeResultDescriptor);
        codeResultDescriptor = new ResultDescriptorImpl();
        codeResultDescriptor.setColumnName(super_code);
        codeResultDescriptor.setTableName("tree");
        codeResultDescriptor.setColumnIndex(2);
        codeResultDescriptor.setColumnType("commonj.sdo.String");
        list.add(codeResultDescriptor);
        return list;
    }

}
