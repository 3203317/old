package net.newcapec.sca.datasource.das.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.newcapec.sca.datasource.DataSource;
import net.newcapec.sca.datasource.das.DataSourceDAS;
import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.sequence.SequenceService;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.apache.tuscany.das.rdb.config.ResultDescriptor;
import org.apache.tuscany.das.rdb.config.impl.ResultDescriptorImpl;
import org.json.JSONObject;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

/**
 *
 * @author huangxin
 *
 */
public class DataSourceDASImpl implements DataSourceDAS {

    private static final Logger _log = Logger.getLogger(DataSourceDASImpl.class);

    private SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SequenceService sequenceService;

    @Reference(name = "sequenceService", required = true)
    public void setSequenceService(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    private DefDBConnService defDBConnService;

    @Reference(name = "defDBConnService", required = true)
    public void setDefDBConnService(DefDBConnService defDBConnService) {
        this.defDBConnService = defDBConnService;
    }

    // 获得DAS
    public DAS getDAS() {
        DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(), null);
        return das;
    }

    public DataSource getDataSourceById(Integer id) {
        String __sql = "select t.* from (select a.code,a.domain_code,a.unit_code,a.type,a.name,a.bind_type,a.service,a.dbconn_code,a.method,a.param,a.memo,a.use_scope_type from p_datasource a) t where t.code=?";

        _log.debug(__sql);

        List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

        ResultDescriptor __rdesc = null;

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("domain_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("unit_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("name");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("bind_type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("service");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("dbconn_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("method");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("param");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("memo");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("use_scope_type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);
        __cmd.setParameter(1, id);
        __cmd.setResultDescriptors(__list);

        DataObject __data = __cmd.executeQuery();

        List<DataObject> __dataList = __data.getList("p_datasource");

        DataSource __dataSource = null;

        if (__dataList != null && __dataList.size() > 0) {
            DataObject __data_3 = __dataList.get(0);

            __dataSource = new DataSource();

            __dataSource.setCode(__data_3.getInt("code"));
            __dataSource.setDomain_code(__data_3.getInt("domain_code"));
            __dataSource.setUnit_code(__data_3.getInt("unit_code"));
            __dataSource.setType(__data_3.getString("type"));
            __dataSource.setName(__data_3.getString("name"));

            __dataSource.setBind_type(__data_3.getInt("bind_type"));
            __dataSource.setService(__data_3.getString("service"));
            __dataSource.setDbconn_code(__data_3.getInt("dbconn_code"));
            __dataSource.setMethod(__data_3.getString("method"));
            __dataSource.setParam(__data_3.getString("param"));

            __dataSource.setMemo(__data_3.getString("memo"));
            __dataSource.setUse_scope_type(__data_3.getInt("use_scope_type"));
        }

        return __dataSource;
    }

    public List<DataSource> findDataSourceList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer begin, Integer limit) {
        String __sql = "select t.* from (select  rownum row_num, tt.* from (select a.code,a.domain_code,a.unit_code,a.type,a.name,a.bind_type,a.service, a.dbconn_code,a.method,a.param,a.memo,a.use_scope_type,b.data_value type_text,c.name domain_code_text,d.name unit_code_text,e.data_value bind_type_text,f.name dbconn_code_text,f.db_account,f.db_password,f.ip,f.port,f.server_id ,g.data_value use_scope_type_text from p_datasource a,z_code_dictionary_data b, z_domain c,p_unit d,z_code_dictionary_data e,p_dbconn f, z_code_dictionary_data g where b.code_dictionary_name='DATASOURCE_TYPE' and a.type=b.data_key and a.domain_code=c.code and a.unit_code=d.code and a.bind_type=e.data_key and e.code_dictionary_name='BIND_TYPE' and a.dbconn_code=f.code and g.code_dictionary_name='DATASOURCE_USETYPE' and a.use_scope_type=g.data_key ";

        if (filter != null) {
            for (FilterParam __param_3 : filter) {
                __sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
            }
        }
        __sql += " order by a.code desc) tt) t ";

        if (begin > -1 && limit > -1)
            __sql += "where t.row_num between ? and ? ";

        _log.debug(__sql);

        List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

        ResultDescriptor __rdesc = null;

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("row_num");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("domain_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("unit_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("name");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("bind_type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("service");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("dbconn_code");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("method");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("param");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("memo");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("use_scope_type");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("type_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("domain_code_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("unit_code_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("bind_type_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("dbconn_code_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        /**/

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("db_account");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("db_password");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("ip");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("port");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("server_id");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("use_scope_type_text");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);
        if (begin > -1 && limit > -1) {
            __cmd.setParameter(1, begin + 1);
            __cmd.setParameter(2, begin + limit);
        }
        __cmd.setResultDescriptors(__list);

        DataObject __data = __cmd.executeQuery();
        List<DataSource> __source = new ArrayList<DataSource>();

        for (DataObject __data_3 : (List<DataObject>) __data.getList("p_datasource")) {
            DataSource __res_4 = new DataSource();
            __res_4.setCode(__data_3.getInt("code"));
            __res_4.setDomain_code(__data_3.getInt("domain_code"));
            __res_4.setUnit_code(__data_3.getInt("unit_code"));
            __res_4.setType(__data_3.getString("type"));
            __res_4.setType_text(__data_3.getString("type_text"));
            __res_4.setName(__data_3.getString("name"));

            __res_4.setBind_type(__data_3.getInt("bind_type"));
            __res_4.setService(__data_3.getString("service"));
            __res_4.setDbconn_code(__data_3.getInt("dbconn_code"));
            __res_4.setMethod(__data_3.getString("method"));
            __res_4.setParam(__data_3.getString("param"));

            __res_4.setMemo(__data_3.getString("memo"));
            __res_4.setUse_scope_type(__data_3.getInt("use_scope_type"));

            __res_4.setDomain_code_text(__data_3.getString("domain_code_text"));
            __res_4.setUnit_code_text(__data_3.getString("unit_code_text"));
            __res_4.setBind_type_text(__data_3.getString("bind_type_text"));
            __res_4.setDbconn_code_text(__data_3.getString("dbconn_code_text"));

            __res_4.setDb_account(__data_3.getString("db_account"));
            __res_4.setDb_password(__data_3.getString("db_password"));
            __res_4.setIp(__data_3.getString("ip"));
            __res_4.setPort(__data_3.getString("port"));
            __res_4.setServer_id(__data_3.getString("server_id"));

            __res_4.setUse_scope_type_text(__data_3.getString("use_scope_type_text"));

            __source.add(__res_4);
        }
        return __source;
    }

    public Boolean insertDataSource(DataSource datasource) {

        String __sql = "insert into p_datasource (code, domain_code, unit_code, type, name, bind_type, service, dbconn_code, method, param, memo, use_scope_type) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        datasource.setCode(this.sequenceService.getNextValue("getMaxDataSourceID"));

        datasource.setParam("");
        datasource.setDomain_code(1);
        datasource.setUnit_code(1);

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);
        __cmd.setParameter(1, datasource.getCode());
        __cmd.setParameter(2, datasource.getDomain_code());
        __cmd.setParameter(3, datasource.getUnit_code());
        __cmd.setParameter(4, datasource.getType());
        __cmd.setParameter(5, datasource.getName());
        __cmd.setParameter(6, datasource.getBind_type());
        __cmd.setParameter(7, datasource.getService());
        __cmd.setParameter(8, datasource.getDbconn_code());
        __cmd.setParameter(9, datasource.getMethod());
        __cmd.setParameter(10, datasource.getParam());
        __cmd.setParameter(11, datasource.getMemo());
        __cmd.setParameter(12, datasource.getUse_scope_type());

        _log.debug(__sql);

        Boolean __result = true;

        try {
            __cmd.execute();
        } catch (Exception $ex) {
            $ex.printStackTrace();
            _log.error($ex);
            __result = false;
        } finally {

        }

        return __result;
    }

    public Boolean updateDataSource(DataSource datasource) {
        // String __sql =
        // "update p_datasource set domain_code = ?, unit_code = ?, type = ?,  name = ?, bind_type = ?, "
        // +
        // "service = ?, dbconn_code = ?, method = ?, param = ?, memo = ? where code = ?";
        //
        // Command __cmd = das.createCommand(__sql);
        // __cmd.setParameter(1, datasource.getDomain_code());
        // __cmd.setParameter(2, datasource.getUnit_code());
        // __cmd.setParameter(3, datasource.getType());
        // __cmd.setParameter(4, datasource.getName());
        // __cmd.setParameter(5, datasource.getBind_type());
        // __cmd.setParameter(6, datasource.getService());
        // __cmd.setParameter(7, datasource.getDbconn_code());
        // __cmd.setParameter(8, datasource.getMethod());
        // __cmd.setParameter(9, datasource.getParam());
        // __cmd.setParameter(10, datasource.getMemo());
        // __cmd.setParameter(11, datasource.getCode());

        String __sql = "update p_datasource set name = ?, service = ?, memo = ?,use_scope_type=? where code = ?";

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);
        // __cmd.setParameter(1, datasource.getDomain_code());
        // __cmd.setParameter(2, datasource.getUnit_code());
        // __cmd.setParameter(3, datasource.getType());
        // __cmd.setParameter(4, datasource.getName());
        // __cmd.setParameter(5, datasource.getBind_type());
        // __cmd.setParameter(6, datasource.getService());
        // __cmd.setParameter(7, datasource.getDbconn_code());
        // __cmd.setParameter(8, datasource.getMethod());
        // __cmd.setParameter(9, datasource.getParam());
        // __cmd.setParameter(10, datasource.getMemo());
        // __cmd.setParameter(11, datasource.getCode());

        __cmd.setParameter(1, datasource.getName());
        __cmd.setParameter(2, datasource.getService());
        __cmd.setParameter(3, datasource.getMemo());
        __cmd.setParameter(4, datasource.getUse_scope_type());
        __cmd.setParameter(5, datasource.getCode());

        _log.debug(datasource.getUse_scope_type());

        _log.debug(__sql);

        Boolean __result = true;

        try {
            __cmd.execute();
        } catch (Exception $ex) {
            _log.error($ex);
            __result = false;
        } finally {

        }

        return __result;
    }

    public Boolean delDataSourceById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean delDataSourceByIds(Integer[] ids) {
        if (ids == null || ids.length < 1)
            return false;

        String __appendSQL = "";

        for (int __i_3 = 0, __j_3 = ids.length; __i_3 < __j_3; __i_3++) {
            __appendSQL += ",?";
        }

        __appendSQL = __appendSQL.substring(1);

        String __sql = "delete p_datasource where code in (" + __appendSQL + ")";

        _log.info(__sql);

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);

        for (int __i_3 = 1, __j_3 = ids.length; __i_3 <= __j_3; __i_3++) {
            __cmd.setParameter(__i_3, ids[__i_3 - 1]);
        }

        Boolean __result = true;

        try {
            __cmd.execute();
        } catch (Exception $ex) {
            _log.error($ex);
            __result = false;
        } finally {

        }

        return __result;
    }

    public Integer getDataSourceListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
        String __sql = "select count(1) count " + "from " + "p_datasource a,z_code_dictionary_data b, z_domain c,p_unit d,z_code_dictionary_data e,p_dbconn f " + "where " + "b.code_dictionary_name='DATASOURCE_TYPE' and a.type=b.data_key " + "and a.domain_code=c.code and a.unit_code=d.code and a.bind_type=e.data_key and e.code_dictionary_name='BIND_TYPE' " + "and a.dbconn_code=f.code ";

        if (filter != null) {
            for (FilterParam __param_3 : filter) {
                __sql += " " + __param_3.getRelation() + " a." + __param_3.getField() + " " + __param_3.getLogical() + " " + __param_3.getValue();
            }
        }

        _log.debug(__sql);

        List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();

        ResultDescriptor __rdesc = null;

        __rdesc = new ResultDescriptorImpl();
        __rdesc.setColumnName("count");
        __rdesc.setTableName("p_datasource");
        __rdesc.setColumnType("commonj.sdo.String");
        __list.add(__rdesc);

        DAS das = this.getDAS();
        Command __cmd = das.createCommand(__sql);
        __cmd.setResultDescriptors(__list);

        DataObject __data = __cmd.executeQuery();

        List<DataObject> __dataList = __data.getList("p_datasource");

        int __count = 0;

        if (__dataList != null && __dataList.size() > 0) {
            DataObject __data_3 = __dataList.get(0);
            __count = __data_3.getInt("count");
        }

        return __count;
    }

    /**
     * @findUndefinedListByCondition
     * @Description:根据 code从p_datasource表中获得动态sql语句，然后组合动态sql语句+动态条件获得List结果
     * @return String
     * @throws
     * @author 普秀霞
     * @date 2012-11-23 修改日期： 修改人： 复审人：
     */

    public Map<String, Object> findUndefinedListByCondition(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) throws Exception {
        Connection conn = null;
        String type, method;
        String dsCode = "";
        List<JSONObject> listResult = null;
        Integer totalCount = 0;
        List<JSONObject> procParamAndTypes = null;
        Map<String, Object> mapResults = new HashMap<String, Object>();
        // 获得ds_code
        for (FilterParam filterParam : filter) {
            if ("ds_code".equals(filterParam.getField().toLowerCase())) {
                dsCode = filterParam.getValue().trim();
                break;
            }
        }
        if (dsCode == null || "".equals(dsCode))
            return null;

        // 获得数据源类型、数据库连接、sql语句或存储过程名
        Map<String, Object> connTypeMethod = getDBConnByDSCode(dsCode);
        if (connTypeMethod == null)
            return null;
        conn = (Connection) connTypeMethod.get("conn");
        type = (String) connTypeMethod.get("type");
        method = (String) connTypeMethod.get("method");
        System.out.println("成功获得连接");
        if (conn == null)
            return null;
        System.out.println("成功获得数据库连接");
        // 执行sql语句或 调用存储过程，结果集封装为JSONObject列表
        if ("sql".equals(type)) {
            listResult = execQuery(conn, method, filter, 1, 100);
            totalCount = getQueryRowCount(conn, method, filter);
            mapResults.put("listResult", listResult);
            mapResults.put("totalCount", totalCount);
        } else if ("proc".equals(type)) {
            System.out.println("执行存储过程：");
            System.out.println(method);
            procParamAndTypes = getProcParam(conn, method);
            mapResults = execProc(conn, method, filter);
        }
        try {
            conn.close();
            return mapResults;
        } catch (SQLException e) {
            System.out.println("conn close error");
            return null;
        }
    }

    // 根据ds_code建立数据库连接
    public Map<String, Object> getDBConnByDSCode(String dsCode) {
        DAS das = this.getDAS();
        StringBuffer sbSql = new StringBuffer();
        // 获取根据dsCode建立数据库连接的信息、数据源的类型type、sql语句或存储过程的名称 method
        sbSql.append(" select  distinct   code,ip,port,server_id ,db_account,db_password,method,type  from( ");
        sbSql.append("   select    p_dbconn.code,p_dbconn.ip,p_dbconn.port,p_dbconn.server_id,p_dbconn.db_account, ");
        sbSql.append("             p_dbconn.db_password ,p_datasource.method,p_datasource.type ");
        sbSql.append("   from     p_datasource,p_dbconn   ");
        sbSql.append("   where    p_datasource.dbconn_code = p_dbconn.code  and  p_datasource.code = ? ");
        sbSql.append(" ) t ");
        System.out.println("dsCode：" + dsCode);
        System.out.println(sbSql.toString());
        String columns[] = { "code", "ip", "port", "server_id", "db_account", "db_password", "method", "type" };
        List<ResultDescriptor> rdList = new ArrayList<ResultDescriptor>();
        rdList = getResultDescriptorList(columns, "t");
        Command cmd = das.createCommand(sbSql.toString());
        cmd.setParameter(1, dsCode);
        cmd.setResultDescriptors(rdList);
        DataObject dataObject = cmd.executeQuery();
        List<DataObject> dataList = dataObject.getList("t");
        DataObject data = null;
        if (dataList != null && dataList.size() > 0)
            data = dataList.get(0);
        String ip, port, server, user, password, method, type;
        Map<String, Object> mapResult = new HashMap<String, Object>();
        try {
            if (data != null) {
                ip = data.getString("ip");
                port = data.getString("port");
                server = data.getString("server_id");
                user = data.getString("db_account");
                password = data.getString("db_password");
                method = data.getString("method");
                type = data.getString("type");
                System.out.println(type);
                System.out.println(method);
                Connection conn = null;
                // 建立数据库连接
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
                conn = DriverManager.getConnection(url, user, password);
                if (conn == null)
                    return null;
                mapResult.put("conn", conn);
                mapResult.put("type", type);
                mapResult.put("method", method);
                return mapResult;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("conn  error");
            return null;
        }
    }

    // type为sql时执行查询语句
    public List<JSONObject> execQuery(Connection conn, String sqlStatement, List<FilterParam> filter, Integer begin, Integer limit) {
        String  tableAndCondition, columnName, columnType, sbSql ;
        Statement stm = null;
        ResultSet rs = null;
        List<JSONObject> listResult = null;
        // 对sql语句进行判断处理
        if (sqlStatement == null || "".equals(sqlStatement))
            return null;
        else
            sqlStatement = sqlStatement.toUpperCase();
        if (!sqlStatement.contains("SELECT") || !sqlStatement.contains("FROM"))
            return null;
        // 获得sql中的表和条件
        tableAndCondition = sqlStatement.substring(sqlStatement.indexOf("FROM") + 4).trim().toLowerCase();
        // 获得列字符串
        columnName = sqlStatement.substring(6, sqlStatement.indexOf("FROM") - 1).trim().toLowerCase();
     // 组合sql语句
        if (tableAndCondition.contains("WHERE"))
        	sbSql = "select t.* from ( select " + columnName + " ,rownum row_num  from  " + tableAndCondition + " ";
        else
            sbSql = "select t.* from ( select " + columnName + " ,rownum row_num  from  " + tableAndCondition + "  where  1=1 ";

        if (filter != null) {
            for (FilterParam filterParam : filter) {
                if ((!"ds_code".equals(filterParam.getField().trim().toLowerCase())) && (!("".equals(filterParam.getValue())))){
                	if ( !"date".equals(filterParam.getType()))
                		sbSql += " " + filterParam.getRelation()  + " "  + filterParam.getField() + " " + filterParam.getLogical() + " " + filterParam.getValue();
                	else
                        sbSql += " " + filterParam.getRelation() + " to_char( " + filterParam.getField() + ",'yyyy-mm-dd hh24:mi:ss')  " + filterParam.getLogical() + " " + filterParam.getValue();
                }
            }
        }
        sbSql += " ) t ";
        if (begin > -1 && limit > -1)
            sbSql += " where t.row_num  between " + String.valueOf(begin) + "  and  " + String.valueOf(limit);

        System.out.println("query");
        System.out.println(sbSql);
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sbSql);
            listResult = transferToJSON(rs);
            return listResult;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("获取数据失败");
            return null;
        }
    }

    // type为sql时获取总行数
    public Integer getQueryRowCount(Connection conn, String sqlStatement, List<FilterParam> filter) {
        String  tableAndCondition,sbSql;
        Statement stm = null;
        ResultSet rs = null;
        // 对sql语句进行判断处理
        if (sqlStatement == null || "".equals(sqlStatement))
            return 0;
        else
            sqlStatement = sqlStatement.toUpperCase();

        if (!sqlStatement.contains("SELECT") || !sqlStatement.contains("FROM"))
            return 0;
        // 获得sql中的表和条件
        tableAndCondition =  sqlStatement.substring(sqlStatement.indexOf("FROM") + 4).trim().toLowerCase();
        if (tableAndCondition.contains("WHERE"))
        	sbSql = " select  count(1)  code  from  " + tableAndCondition + "  ";
        else
            sbSql = " select  count(1)  code  from  " + tableAndCondition + "   where 1=1 ";

        if (filter != null) {

        	for (FilterParam filterParam : filter) {
                if ((!"ds_code".equals(filterParam.getField().trim().toLowerCase())) && (!("".equals(filterParam.getValue())))){
                	if (!"date".equals(filterParam.getType()))
                		sbSql += " " + filterParam.getRelation() + " " + filterParam.getField() + " " + filterParam.getLogical() + " " + filterParam.getValue();
                	else
                        sbSql += " " + filterParam.getRelation() + " to_char(" + filterParam.getField() + ",'yyyy-mm-dd hh24:mi:ss')  " + filterParam.getLogical() + " " + filterParam.getValue();
                }
            }
        }
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(sbSql.toString());
            Integer count = 0;
            if (rs.next())
                count = rs.getInt("code");
            System.out.println("count:" + String.valueOf(count));
            return count;
        } catch (Exception e) {
            System.out.println("获取数据行失败");
            return 0;
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    return 0;
                }
            }
        }
    }

    // type为proc时调用存储过程
    public Map<String, Object> execProc(Connection conn, String procName, List<FilterParam> filter) {
        Statement stm = null;
        ResultSet rs = null;
        CallableStatement proc = null;
        List<JSONObject> listResult = null;
        Map<String, Object> mapResult = new HashMap<String, Object>();
        String procStatement = " { call " + procName + "(?,?";
        if (filter != null) {
            for (FilterParam filterParam : filter) {
                if ((!"ds_code".equals(filterParam.getField().trim().toLowerCase())) && (!("".equals(filterParam.getValue()))))
                    procStatement += "," + filterParam.getValue();
            }
        }
        procStatement += " ) }";
        System.out.println(procStatement);
        try {
            System.out.println("执行存储过程前：");
            proc = conn.prepareCall(procStatement);
            proc.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            proc.registerOutParameter(2, java.sql.Types.INTEGER);
            proc.execute();
            System.out.println("执行存储过程后：");
            rs = (ResultSet) proc.getObject(1);
            Integer totalCount = (Integer) proc.getObject(2);
            listResult = transferToJSON(rs);
            mapResult.put("listResult", listResult);
            mapResult.put("totalCount", totalCount);
            System.out.println("总行数：" + totalCount);
            return mapResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    return null;
                }
            }
        }
    }

    // ResultSet结果封装为 JSONObject列表
    public List<JSONObject> transferToJSON(ResultSet rs) {
        List<JSONObject> listResult = null;
        try {
            ResultSetMetaData metaDate = rs.getMetaData();
            String className = "";
            List<JSONObject> columnAndTypes = new ArrayList<JSONObject>();
            JSONObject columnAndType = null;
            // 获得列及列类型
            for (int i = 1, j = metaDate.getColumnCount(); i <= j; i++) {
                if ("java.math.BigDecimal".equals(metaDate.getColumnClassName(i))) {
                    className = "number";
                } else if ("java.sql.Timestamp".equals(metaDate.getColumnClassName(i))) {
                    className = "date";
                } else if ("java.lang.String".equals(metaDate.getColumnClassName(i))) {
                    className = "string";
                }
                // System.out.println("className:"+className);
                columnAndType = new JSONObject();
                columnAndType.put("columnname", metaDate.getColumnName(i));
                columnAndType.put("columntype", className);
                columnAndTypes.add(columnAndType);
            }
            listResult = new ArrayList<JSONObject>();
            JSONObject jsonResult = null;
            String columnname, columntype;
            //封装查询结果为JSONObject列表
            while (rs.next()) {
                jsonResult = new JSONObject();
                for (JSONObject jsonTemp : columnAndTypes) {
                    columnname = jsonTemp.get("columnname").toString();
                    columntype = jsonTemp.get("columntype").toString();
                    System.out.println(columnname);
                    System.out.println(columntype);
                    if ("string".equals(columntype)) {
                        if (rs.getString(columnname) == null) {
                            jsonResult.put(columnname.toLowerCase(), "");
                        } else {
                            jsonResult.put(columnname.toLowerCase(), rs.getString(columnname));
                        }
                        System.out.println(rs.getString(columnname));
                    } else if ("number".equals(columntype)) {
                    	if (rs.getString(columnname) == null) {
                            jsonResult.put(columnname.toLowerCase(), "");
                    	} else {
                    		jsonResult.put(columnname.toLowerCase(), String.valueOf(rs.getInt(columnname)));
                    	}
                    }
                }
                listResult.add(jsonResult);
            }
             System.out.println("11********************11");
             for(JSONObject rowData:listResult){
                for(JSONObject jsonColumn :columnAndTypes){
                    columnname = jsonColumn.get("columnname").toString().toLowerCase();
                    System.out.println(columnname +":"+ rowData.get(columnname));
                }
             }
        } catch (Exception e) {
            System.out.println("转化失败");
            return null;
        }
        return listResult;
    }

    // type为proc时获得存储过程参数
    public List<JSONObject> getProcParam(Connection conn, String procName) {
        Statement stm = null;
        ResultSet rsSet = null;
        StringBuffer sbSql = new StringBuffer();
        List<JSONObject> listResult = new ArrayList<JSONObject>();
        sbSql.append(" select argument_name,data_type  from  user_arguments ");
        sbSql.append(" where  object_name = ");
        sbSql.append("'");
        sbSql.append(procName);
        sbSql.append("'");
        sbSql.append(" and  in_out = 'IN'");
        try {
            stm = conn.createStatement();
            rsSet = stm.executeQuery(sbSql.toString());
            JSONObject jsonTemp = null;
            while (rsSet.next()) {
                jsonTemp = new JSONObject();
                System.out.println(rsSet.getString("argument_name"));
                System.out.println(rsSet.getString("data_type"));
                jsonTemp.put("argument_name", rsSet.getString("argument_name"));
                jsonTemp.put("data_type", rsSet.getString("data_type"));
                listResult.add(jsonTemp);
            }
        } catch (Exception e) {
            return null;
        }

        return listResult;
    }

    /**
     * @getResultDescriptorList
     * @Description:根据 传入的列和表名获得List<ResultDescriptor>
     * @return List<ResultDescriptor>
     * @throws
     * @author 普秀霞
     * @date 2012-12-19 修改日期： 修改人： 复审人：
     */
    public List<ResultDescriptor> getResultDescriptorList(String[] fields, String tableName) {

        List<ResultDescriptor> __list = new ArrayList<ResultDescriptor>();
        ResultDescriptor __rdesc = null;
        for (String field : fields) {
            __rdesc = new ResultDescriptorImpl();
            __rdesc.setColumnName(field);
            __rdesc.setTableName(tableName);
            __rdesc.setColumnType("commonj.sdo.String");
            __list.add(__rdesc);
        }
        return __list;
    }
}
