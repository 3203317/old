package net.newcapec.sca.dbconn.das.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.dbconn.das.CommonDAS;
import net.newcapec.sca.dbconn.das.DBConnDAS;
import net.newcapec.sca.param.FilterParam;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;

import commonj.sdo.DataObject;

public class DBConnDASImpl extends CommonDAS implements DBConnDAS {
    private final static String ORALCE = "3";
    private final static String MYSQL = "1";

    private static final Logger dbConnDASLogger = Logger.getLogger(DBConnDASImpl.class);    

    public DBConnection getDBConnById(Integer id) {
        dbConnDASLogger.info("根据p_dbconn的code得到其对应的属性，id: " + id + " 执行getDBConnById命令");
        DAS das = getDAS();
        Command command = das.getCommand("getDBConnById");
        command.setParameter(1, id);
        DataObject root = command.executeQuery();
        DataObject p_dbconnDO = root.getDataObject("p_dbconn[1]");
        das.releaseResources();
        DBConnection p_dbconn = this.translateDOTOP_dbconn(p_dbconnDO);
        return p_dbconn;
    }

    public Connection getJavaConnectionBYConfigId (Integer id)
    {
        Connection conn = null;
        try
        {
            DBConnection dbconn = getDBConnById(id);
            if(dbconn != null)
            {
                conn = getSqlConnectionBYDBConnectionConfig(dbconn);
            }
        }
        catch (Exception e)
        {
            dbConnDASLogger.error(e.getMessage(),e);
        }
        return conn;
    }
    
    public Boolean getDBConfig2Connection(DBConnection db) {
        dbConnDASLogger.info("根据DBConnection的信息判断是否是正确的连接！");
        Connection conn = null;
        Boolean rtnSign = false;
        try 
        {
            conn = getSqlConnectionBYDBConnectionConfig(db);
            if ( conn != null)
            {
                rtnSign = true;
            }
        } catch (Exception e) {
            dbConnDASLogger.error(e.getMessage(),e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                dbConnDASLogger.debug(e.getMessage(),e);
            }
        }
        return rtnSign;
    }

    public Connection getSqlConnectionBYDBConnectionConfig(DBConnection db){
        Connection conn = null;
        String url = "";
        try
        {
            if (ORALCE.equalsIgnoreCase(db.getDataBaseName())) {
                url = "jdbc:mysql://" + db.getConnectIP() + ":" + db.getConnectPort() + "/" + db.getConnectSID();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } else if (MYSQL.equalsIgnoreCase(db.getDataBaseName())) {
                url = "jdbc:oracle:thin:@" + db.getConnectIP() + ":" + db.getConnectPort() + ":" + db.getConnectSID();
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            }
            conn = DriverManager.getConnection(url, db.getAccoutName(), db.getAccountPassword());
            conn.setAutoCommit(false);
        }
        catch (Exception e)
        {
             dbConnDASLogger.debug(e.getMessage(),e);
        }
        return conn;
    }

    public Integer getDBConnListRowCount(Integer domainId, Integer orgId, List<FilterParam> filter) {
        dbConnDASLogger.info("取得p_dbconn中记录的个数");
        DAS das = this.getDAS();
        String sql = "select count(1) code from p_dbconn " + "  where domain_code = ? \n" + " and unit_code = ? \n";
        if (filter != null) {
            for (FilterParam paramItem : filter) {
                sql += " " + paramItem.getRelation() + " " + paramItem.getField() + " " + paramItem.getLogical() + " " + paramItem.getValue();
            }
        }
        dbConnDASLogger.debug(sql);
        Command command = das.createCommand(sql);
        List<ResultDescriptor> list = new ArrayList<ResultDescriptor>();
        ResultDescriptor code = new ResultDescriptorImpl();
        code.setColumnName("code");
        code.setTableName("p_dbconn");
        code.setColumnIndex(0);
        code.setColumnType("commonj.sdo.String");
        list.add(code);
        command.setResultDescriptors(list);

        command.setParameter(1, domainId);
        command.setParameter(2, orgId);
        Integer allNum = Integer.valueOf(command.executeQuery().getDataObject("p_dbconn[1]").getString("code"));
        das.releaseResources();
        return allNum;
    }

    public List<DBConnection> findDBConnList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limitId) {
        DAS das = this.getDAS();
        List<DBConnection> rtnList = new ArrayList<DBConnection>();
        String sql = "select * from \n" + "(select\n" + "      code,\n" + "      domain_code,\n" + "      unit_code,\n" + "      name,\n" + "      dbtype_code,\n" + "      ip,\n" + "      port,\n" + "      server_id,\n" + "      db_account,\n" + "      db_password,\n" + "      memo,\n" + "      create_user_code,\n" + "      to_char(create_date,'yyyy-mm-dd hh24:mi:ss') create_date,\n" + "      encryption_info,\n" + "      encryption_timestamp,\n" + "      ver,\n" + "      rownum testnum\n" + "      from\n" + "      p_dbconn \n" + "	   where domain_code = ? \n" + "	   and unit_code = ? \n";

        if (filter != null) {
            for (FilterParam paramItem : filter) {
                sql += " " + paramItem.getRelation() + " " + paramItem.getField() + " " + paramItem.getLogical() + " " + paramItem.getValue();
            }
        }
        sql = sql + ") where 1=1\n";
        if (null != beginId) {
            sql = sql + " and testnum > ? \n";
        }
        if (null != limitId) {
            sql = sql + " and testnum <= ? \n";
        }
        sql = sql + "order by code desc";
        dbConnDASLogger.debug(sql);
        Command command = das.createCommand(sql);
        List list = this.getFilledResultDescriptionList_p_dbconn();
        command.setResultDescriptors(list);
        command.setParameter(1, domainId);
        command.setParameter(2, orgId);
        if (null != beginId) {
            command.setParameter(3, beginId);
        }
        if (null != limitId) {
            command.setParameter(4, beginId + limitId);
        }

        DataObject root = command.executeQuery();
        for (DataObject dObj : (List<DataObject>) root.getList("p_dbconn")) {
            DBConnection resource = this.translateDOTOP_dbconn(dObj);
            rtnList.add(resource);
        }
        das.releaseResources();
        return rtnList;
    }

    public Boolean insertDBConn(DBConnection org) {
        DAS das = this.getDAS();
        Boolean rtn = true;
        String sql = "	insert into p_dbconn " + " 	(code,domain_code,unit_code,name,dbtype_code,ip,port,server_id,db_account," + "	db_password,memo,create_user_code,create_date,encryption_info,encryption_timestamp,ver)" + "	values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
        dbConnDASLogger.debug(sql);
        Command command = das.createCommand(sql);
        command.setParameter(1, org.getCode());
        command.setParameter(2, org.getDomain_code());
        command.setParameter(3, org.getUnit_code());
        command.setParameter(4, org.getName());
        if ("oracle".equalsIgnoreCase(org.getDataBaseName())) {
            command.setParameter(5, "1");
        } else if ("ms_sql".equalsIgnoreCase(org.getDataBaseName())) {
            command.setParameter(5, "2");
        } else if ("mysql".equalsIgnoreCase(org.getDataBaseName())) {
            command.setParameter(5, "3");
        } else if ("sysbase".equalsIgnoreCase(org.getDataBaseName())) {
            command.setParameter(5, "4");
        } else if ("other".equalsIgnoreCase(org.getDataBaseName())) {
            command.setParameter(5, "5");
        } else {
            command.setParameter(5, org.getDataBaseName());
        }
        command.setParameter(6, org.getConnectIP());
        command.setParameter(7, org.getConnectPort());
        command.setParameter(8, org.getConnectSID());
        command.setParameter(9, org.getAccoutName());
        command.setParameter(10, org.getAccountPassword());
        command.setParameter(11, org.getMemo());
        command.setParameter(12, org.getCreate_user_code());
        command.setParameter(13, org.getEncryption_info());
        command.setParameter(14, org.getEncryption_timestamp());
        command.setParameter(15, org.getVer());
        try {
            command.execute();
        } catch (Exception e) {
            rtn = false;
            dbConnDASLogger.debug(e.getMessage(), e);
        }

        return rtn;
    }

    public Boolean updateDBConn(DBConnection org) {
        DAS das = this.getDAS();
        Boolean rtn = true;
        Command command = das.getCommand("getDBConnById");
        command.setParameter(1, org.getCode());
        DataObject root = command.executeQuery();
        DataObject dbDO = root.getDataObject("p_dbconn[1]");
        if (null != org.getName()) {
            dbDO.setString("name", org.getName());
        }
        if (null != org.getDomain_code()) {
            dbDO.setString("domain_code", org.getDomain_code().toString());
        }
        if (null != org.getUnit_code()) {
            dbDO.setString("unit_code", org.getUnit_code().toString());
        }
        if (null != org.getDataBaseName()) {
            if ("oracle".equalsIgnoreCase(org.getDataBaseName())) {
                dbDO.setString("dbtype_code", "1");
            } else if ("ms_sql".equalsIgnoreCase(org.getDataBaseName())) {
                dbDO.setString("dbtype_code", "2");
            } else if ("mysql".equalsIgnoreCase(org.getDataBaseName())) {
                dbDO.setString("dbtype_code", "3");
            } else if ("sysbase".equalsIgnoreCase(org.getDataBaseName())) {
                dbDO.setString("dbtype_code", "4");
            } else if ("other".equalsIgnoreCase(org.getDataBaseName())) {
                dbDO.setString("dbtype_code", "5");
            } else {
                dbDO.setString("dbtype_code", org.getDataBaseName());
            }
        }
        if (null != org.getConnectIP()) {
            dbDO.setString("ip", org.getConnectIP());
        }
        if (null != org.getConnectPort()) {
            dbDO.setString("port", org.getConnectPort());
        }
        if (null != org.getConnectSID()) {
            dbDO.setString("server_id", org.getConnectSID());
        }
        if (null != org.getAccoutName()) {
            System.out.println(org.getAccoutName());
            dbDO.setString("db_account", org.getAccoutName());
        }
        if (null != org.getAccountPassword()) {
            dbDO.setString("db_password", org.getAccountPassword());
        }
        if (null != org.getMemo()) {
            dbDO.setString("memo", org.getMemo());
        }
        if (null != org.getCreate_user_code()) {
            dbDO.setString("create_user_code", org.getCreate_user_code());
        }
        if (null != org.getEncryption_info()) {
            dbDO.setString("encryption_info", org.getEncryption_info());
        }
        if (null != org.getEncryption_timestamp()) {
            dbDO.setString("encryption_timestamp", org.getEncryption_timestamp());
        }
        if (null != org.getVer()) {
            dbDO.setString("ver", org.getVer().toString());
        }
        try {
            das.applyChanges(root);
        } catch (Exception e) {
            dbConnDASLogger.debug(e.getMessage(), e);
            rtn = false;
        }

        return rtn;
    }

    public Boolean delDBConnById(Integer id) {
        DAS das = this.getDAS();
        Boolean rtn = true;
        String sql = "	delete p_dbconn where code = ?";
        dbConnDASLogger.info(sql);
        Command command = das.createCommand(sql);
        command.setParameter(1, id);
        try {
            command.execute();
        } catch (Exception e) {
            dbConnDASLogger.debug(e.getMessage(), e);
            rtn = false;
        }

        return rtn;
    }

    private DBConnection translateDOTOP_dbconn(DataObject p_dbconnDO) {
        DBConnection db = new DBConnection();
        db.setCode(Long.valueOf(p_dbconnDO.getString("code")));
        db.setDomain_code(Long.valueOf(p_dbconnDO.getString("domain_code")));
        db.setUnit_code(Long.valueOf(p_dbconnDO.getString("unit_code")));
        db.setName(p_dbconnDO.getString("name"));

        db.setDataBaseName(p_dbconnDO.getString("dbtype_code"));
        db.setConnectIP(p_dbconnDO.getString("ip"));
        db.setConnectPort(p_dbconnDO.getString("port"));
        db.setConnectSID(p_dbconnDO.getString("server_id"));

        db.setAccountPassword(p_dbconnDO.getString("db_password"));
        db.setAccoutName(p_dbconnDO.getString("db_account"));
        db.setCreate_date(p_dbconnDO.getString("create_date"));
        db.setCreate_user_code(p_dbconnDO.getString("create_user_code"));

        db.setEncryption_info(p_dbconnDO.getString("encryption_info"));
        db.setEncryption_timestamp(p_dbconnDO.getString("encryption_timestamp"));
        db.setMarker(p_dbconnDO.getString("memo"));

        db.setVer(Long.valueOf(p_dbconnDO.getString("ver")));
        return db;
    }

    public List getFilledResultDescriptionList_p_dbconn() {
        List list = new ArrayList();
        ResultDescriptor code = new ResultDescriptorImpl();
        code.setColumnName("code");
        code.setTableName("p_dbconn");
        code.setColumnIndex(0);
        code.setColumnType("commonj.sdo.String");
        list.add(code);
        ResultDescriptor domain_code = new ResultDescriptorImpl();
        domain_code.setColumnName("domain_code");
        domain_code.setTableName("p_dbconn");
        domain_code.setColumnIndex(1);
        domain_code.setColumnType("commonj.sdo.String");
        list.add(domain_code);
        ResultDescriptor unit_code = new ResultDescriptorImpl();
        unit_code.setColumnName("unit_code");
        unit_code.setTableName("p_dbconn");
        unit_code.setColumnIndex(2);
        unit_code.setColumnType("commonj.sdo.String");
        list.add(unit_code);
        ResultDescriptor name = new ResultDescriptorImpl();
        name.setColumnName("name");
        name.setTableName("p_dbconn");
        name.setColumnIndex(3);
        name.setColumnType("commonj.sdo.String");
        list.add(name);
        ResultDescriptor dbtype_code = new ResultDescriptorImpl();
        dbtype_code.setColumnName("dbtype_code");
        dbtype_code.setTableName("p_dbconn");
        dbtype_code.setColumnIndex(4);
        dbtype_code.setColumnType("commonj.sdo.String");
        list.add(dbtype_code);
        ResultDescriptor ip = new ResultDescriptorImpl();
        ip.setColumnName("ip");
        ip.setTableName("p_dbconn");
        ip.setColumnIndex(5);
        ip.setColumnType("commonj.sdo.String");
        list.add(ip);
        ResultDescriptor port = new ResultDescriptorImpl();
        port.setColumnName("port");
        port.setTableName("p_dbconn");
        port.setColumnIndex(6);
        port.setColumnType("commonj.sdo.String");
        list.add(port);
        ResultDescriptor server_id = new ResultDescriptorImpl();
        server_id.setColumnName("server_id");
        server_id.setTableName("p_dbconn");
        server_id.setColumnIndex(7);
        server_id.setColumnType("commonj.sdo.String");
        list.add(server_id);
        ResultDescriptor db_account = new ResultDescriptorImpl();
        db_account.setColumnName("db_account");
        db_account.setTableName("p_dbconn");
        db_account.setColumnIndex(8);
        db_account.setColumnType("commonj.sdo.String");
        list.add(db_account);
        ResultDescriptor db_password = new ResultDescriptorImpl();
        db_password.setColumnName("db_password");
        db_password.setTableName("p_dbconn");
        db_password.setColumnIndex(9);
        db_password.setColumnType("commonj.sdo.String");
        list.add(db_password);
        ResultDescriptor memo = new ResultDescriptorImpl();
        memo.setColumnName("memo");
        memo.setTableName("p_dbconn");
        memo.setColumnIndex(10);
        memo.setColumnType("commonj.sdo.String");
        list.add(memo);
        ResultDescriptor create_user_code = new ResultDescriptorImpl();
        create_user_code.setColumnName("create_user_code");
        create_user_code.setTableName("p_dbconn");
        create_user_code.setColumnIndex(11);
        create_user_code.setColumnType("commonj.sdo.String");
        list.add(create_user_code);
        ResultDescriptor create_date = new ResultDescriptorImpl();
        create_date.setColumnName("create_date");
        create_date.setTableName("p_dbconn");
        create_date.setColumnIndex(12);
        create_date.setColumnType("commonj.sdo.String");
        list.add(create_date);
        ResultDescriptor encryption_info = new ResultDescriptorImpl();
        encryption_info.setColumnName("encryption_info");
        encryption_info.setTableName("p_dbconn");
        encryption_info.setColumnIndex(13);
        encryption_info.setColumnType("commonj.sdo.String");
        list.add(encryption_info);
        ResultDescriptor encryption_timestamp = new ResultDescriptorImpl();
        encryption_timestamp.setColumnName("encryption_timestamp");
        encryption_timestamp.setTableName("p_dbconn");
        encryption_timestamp.setColumnIndex(14);
        encryption_timestamp.setColumnType("commonj.sdo.String");
        list.add(encryption_timestamp);
        ResultDescriptor ver = new ResultDescriptorImpl();
        ver.setColumnName("ver");
        ver.setTableName("p_dbconn");
        ver.setColumnIndex(15);
        ver.setColumnType("commonj.sdo.String");
        list.add(ver);
        return list;
    }



}
