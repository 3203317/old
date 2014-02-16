/**
 * 
 */
package net.htjs.editor.designmap.editors.dmpages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import net.htjs.build.buildcodes.BuildBase;
import net.htjs.editor.designmap.GMFConsole;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * @author wenming Nov 17, 2008 edited by houxiaopeng 20090325
 *         该类主要是为生成SqlMap使用，增加取主键的方法
 */
public class UtilModel
{
  private static BasicDataSource dataSource = null;

  public static List getTableColumns(String tableName) throws Exception
  {
    // 先把是否是主键取出来
    List pkList = UtilModel.getPkColumn(tableName);
    GMFConsole.println(pkList);
    List list = new ArrayList();
    String dbType = "oracle";
    String strArr[] = tableName.split("\\.");
    if (dbType.equals("oracle"))
    {
      String sql = "";
      if (strArr.length == 1)
      {
         sql = "SELECT A.COLUMN_NAME,A.DATA_TYPE,A.DATA_LENGTH,A.DATA_DEFAULT,A.NULLABLE,B.COMMENTS FROM USER_TAB_COLUMNS A,USER_COL_COMMENTS B WHERE A.TABLE_NAME=B.TABLE_NAME(+) AND A.COLUMN_NAME=B.COLUMN_NAME(+) AND A.TABLE_NAME='"
             + tableName.toUpperCase() + "'  ORDER BY A.COLUMN_ID ASC";
      }
      else
      {
    	sql = "SELECT A.COLUMN_NAME,A.DATA_TYPE,A.DATA_LENGTH,A.DATA_DEFAULT,A.NULLABLE,B.COMMENTS FROM ALL_TAB_COLUMNS A,ALL_COL_COMMENTS B WHERE A.TABLE_NAME=B.TABLE_NAME(+) AND A.COLUMN_NAME=B.COLUMN_NAME(+) AND A.TABLE_NAME='"
            + strArr[1].toUpperCase() + "' AND A.OWNER = '" + strArr[0].toUpperCase() + "'  ORDER BY A.COLUMN_ID ASC";
      }
      GMFConsole.println(sql);
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try
      {
        conn = getDataSource().getConnection();
        if (conn != null)
        {
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          // int numcols = rs.getMetaData().getColumnCount();
          while (rs.next())
          {
            Map map = new HashMap();
            map.put("name", rs.getString(1).toUpperCase());
            map.put("type", getType1(rs.getString(2)));
            map.put("ColType", rs.getString(2));// 未经转化的数据库原始类型
            map.put("length", rs.getString(3));
            map.put("defaultValue", nullToStr(rs.getString(4)));// 如果是null,该成空串
            map.put("nullable", rs.getString(5));
            // 下面判断该列是否是主键
            if (pkList.contains(rs.getString(1).toUpperCase()))
              map.put("isPk", "Y");
            else
              map.put("isPk", "N");
            map.put("comments", rs.getString(6));
            list.add(map);
          }
        }
      }
      finally
      {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (conn != null)
          conn.close();
      }
    }
    return list;
  }

  /**
   * 获得主键的相关信息
   * 
   * @param tableName
   * @return
   * @throws Exception
   */
  public static List getPkColumn(String tableName) throws Exception
  {
    List list = new ArrayList();
    String dbType = BuildBase.getConfig().getProperty("dbType");
    String strArr[] = tableName.split("\\.");
    if (dbType.equals("oracle"))
    {
      String sql = "";
      if (strArr.length == 1){
        sql = "SELECT a.TABLE_NAME,b.column_name,b.constraint_name FROM user_constraints a,user_cons_columns b where a.constraint_name=b.constraint_name and constraint_type='P' AND a.table_name='"
            + tableName.toUpperCase() + "'";
      }
      else
      {
    	sql = "SELECT a.TABLE_NAME,b.column_name,b.constraint_name FROM all_constraints a,all_cons_columns b where a.constraint_name=b.constraint_name and constraint_type='P' AND a.table_name='"
            + strArr[1].toUpperCase() + "' AND a.OWNER = '" + strArr[0].toUpperCase() + "'";
      }
      GMFConsole.println(sql);
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try
      {
        conn = getDataSource().getConnection();
        if (conn != null)
        {
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          // int numcols = rs.getMetaData().getColumnCount();
          while (rs.next())
          {
            Map map = new HashMap();
            map.put("table_name", rs.getString(1).toUpperCase());
            map.put("column_name", rs.getString(2));
            map.put("constraint_name", rs.getString(3));
            list.add(map);
          }
        }
      }
      finally
      {
        if (rs != null)
          rs.close();
        if (stmt != null)
          stmt.close();
        if (conn != null)
          conn.close();
      }
    }
    System.out.println("list is" + list);
    return list;
  }

  public static DataSource getDataSource1()
  {
    if (dataSource == null)
    {
      String driveClassName = BuildBase.getConfig().getProperty(
          "dbDriveClassName");
      String dbConnUrl = BuildBase.getConfig().getProperty("dbConnUrl");
      String username = BuildBase.getConfig().getProperty("dbConnUsername");
      String password = BuildBase.getConfig().getProperty("dbConnPassword");
      dataSource = new BasicDataSource();
      dataSource.setDriverClassName(driveClassName);
      dataSource.setUrl(dbConnUrl);
      dataSource.setUsername(username);
      dataSource.setPassword(password);
    }
    return dataSource;
  }

  public static DataSource getDataSource() throws Exception
  {
    if (dataSource == null)
    {
      String driveClassName = BuildBase.getConfig().getProperty(
          "dbDriveClassName");
      String dbConnUrl = BuildBase.getConfig().getProperty("dbConnUrl");
      String username = BuildBase.getConfig().getProperty("dbConnUsername");
      String password = BuildBase.getConfig().getProperty("dbConnPassword");

      Properties p = new Properties();
      p.setProperty("driverClassName", driveClassName);
      p.setProperty("url", dbConnUrl);
      p.setProperty("password", password);
      p.setProperty("username", username);
      p.setProperty("maxActive", "5");
      p.setProperty("maxIdle", "5");
      p.setProperty("maxWait", "5");
      p.setProperty("removeAbandoned", "false");
      p.setProperty("removeAbandonedTimeout", "120");
      p.setProperty("testOnBorrow", "true");
      p.setProperty("logAbandoned", "true");
      dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);

      /*
       * Properties p = new Properties(); p.setProperty("driverClassName",
       * "oracle.jdbc.driver.OracleDriver"); p.setProperty("url",
       * "jdbc:oracle:thin:@10.10.10.34:1521:al32"); p.setProperty("password",
       * "clgl"); p.setProperty("username", "clgl"); p.setProperty("maxActive",
       * "10"); p.setProperty("maxIdle", "10"); p.setProperty("maxWait", "10");
       * p.setProperty("removeAbandoned", "false");
       * p.setProperty("removeAbandonedTimeout", "120");
       * p.setProperty("testOnBorrow", "true"); p.setProperty("logAbandoned",
       * "true"); dataSource = (BasicDataSource)
       * BasicDataSourceFactory.createDataSource(p);
       */
    }
    return dataSource;
  }

  public static void shutdownDataSource() throws SQLException
  {
    ((BasicDataSource) dataSource).close();
  }

  private static String getType(String type)
  {
    if (type.equalsIgnoreCase("NUMBER") || type.equalsIgnoreCase("LONG"))
    {
      return "DECIMAL";
    }
    else if (type.equalsIgnoreCase("DATE"))
    {
      return "DATETIME";
    }
    else
    {
      return "VARCHAR";
    }
  }

  private static String getType1(String type)
  {
    if (type.equalsIgnoreCase("NUMBER") || type.equalsIgnoreCase("LONG"))
    {
      return "NUMBER";
    }
    else if (type.equalsIgnoreCase("DATE"))
    {
      return "DATE";
    }
    else
    {
      return "VARCHAR";
    }
  }

  /**
   * 将空值转换为空字符串
   * 
   * @param str 字符串
   * @return String 返回处理后的字符串
   */
  public static String nullToStr(String str)
  {
    return str == null || str.equals("null") ? "" : str.trim();
  }
}
