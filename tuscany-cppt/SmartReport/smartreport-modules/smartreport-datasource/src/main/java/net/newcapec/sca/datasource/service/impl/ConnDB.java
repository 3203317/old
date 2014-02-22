package net.newcapec.sca.datasource.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.datasource.ProtoDBField;

import org.apache.log4j.Logger;

/**
 *
 * @author huangxin
 *
 */
public class ConnDB {

	private static final Logger _log = Logger.getLogger(ConnDB.class);

	@SuppressWarnings("finally")
	public List<String> getDbAllTablesByUser(String user, String password, String ip, String port, String server) {
		List<String> __list = new ArrayList<String>();
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet set = stm.executeQuery("select * from all_tables where owner='" + user + "' order by table_name");

			while (set.next()) {
				__list.add(set.getString("table_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	@SuppressWarnings("finally")
	public List<String> getDbAllViewsByUser(String user, String password, String ip, String port, String server) {
		List<String> __list = new ArrayList<String>();
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet set = stm.executeQuery("Select * From user_objects Where object_type='VIEW' or object_type='TABLE'");

			while (set.next()) {
				__list.add(set.getString("object_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	@SuppressWarnings("finally")
	public List<ProtoDBField> getDbAllTableFieldsByUser(String user, String password, String ip, String port, String server, String tableName) {
		List<ProtoDBField> __list = new ArrayList<ProtoDBField>();
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet set = stm.executeQuery("select * from user_tab_columns where table_name='" + tableName + "'");

			while (set.next()) {
				ProtoDBField __field_3 = new ProtoDBField();
				__field_3.setTable_name(set.getString("table_name"));
				__field_3.setColumn_name(set.getString("column_name"));
				__field_3.setData_type(set.getString("data_type"));
				__list.add(__field_3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	@SuppressWarnings("finally")
	public List<String> getDbAllProcByUser(String user, String password, String ip, String port, String server) {
		List<String> __list = new ArrayList<String>();
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet __set = stm.executeQuery("select distinct(name) proc_name from user_source where type='PROCEDURE'");

			while (__set.next()) {
				__list.add(__set.getString("proc_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	/**
	 * 获取存储过程的字段列表
	 * <p/>
	 * CREATE OR REPLACE PROCEDURE TESTC1(p_CURSOR out
	 * TESTPACKAGE.Test_CURSOR,param1 in number,param2 in varchar) IS BEGIN OPEN
	 * p_CURSOR FOR SELECT * FROM EMP; END TESTC1;
	 *
	 *
	 * @param user
	 *            数据库用户名
	 * @param password
	 *            密码
	 * @param ip
	 *            数据库ip
	 * @param port
	 *            端口
	 * @param server
	 *            orcl
	 * @param procName
	 *            存储过程名称
	 * @return 存储过程字段List
	 */
	@SuppressWarnings("finally")
	public List<String[]> getDbProcFieldsByUser(String user, String password, String ip, String port, String server, String procName) {
		List<String[]> __list = null;
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			CallableStatement proc = null;

			String __sql = "{ call " + procName + "(?";

			int __fieldCount = this.getDbProcInParamCountByUser(user, password, ip, port, server, procName);

			for (int i = 1; i < __fieldCount; i++) {
				__sql += ",1";
			}

			__sql += ") }";

			proc = conn.prepareCall(__sql);
			proc.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			proc.execute();
			rs = (ResultSet) proc.getObject(1);
			// while (rs.next()) {
			// System.out.println(rs.getString(1) + "			" + rs.getString(2));
			// }

			ResultSetMetaData metaDate = rs.getMetaData();

			int number = metaDate.getColumnCount();
			__list = new ArrayList<String[]>();

			for (int i = 1, j = number + 1; i < j; i++) {
				String __className = "";

				if ("java.math.BigDecimal".equals(metaDate.getColumnClassName(i))) {
					__className = "number";
				} else if ("java.sql.Timestamp".equals(metaDate.getColumnClassName(i))) {
					__className = "date";
				} else if ("java.lang.String".equals(metaDate.getColumnClassName(i))) {
					__className = "string";
				}

				String[] field = { metaDate.getColumnName(i).toLowerCase(), __className, Integer.toString(metaDate.getColumnDisplaySize(i)), metaDate.getColumnTypeName(i) };
				__list.add(field);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	/**
	 * 获取存储过程输入参数数量
	 *
	 * @param user
	 * @param password
	 * @param ip
	 * @param port
	 * @param server
	 * @param procName
	 * @return
	 */
	@SuppressWarnings("finally")
	public int getDbProcInParamCountByUser(String user, String password, String ip, String port, String server, String procName) {
		Connection conn = null;
		Statement stm = null;
		int count = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet __set = stm.executeQuery("select count(1) count from all_arguments t where t.object_name='" + procName + "'");

			while (__set.next()) {
				count = __set.getInt("count");
			}

		} catch (Exception e) {
			_log.debug(e.getMessage());
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
		}
	}

	/**
	 * 获取sql语句执行后的字段列表
	 *
	 * @param user
	 * @param password
	 * @param ip
	 * @param port
	 * @param server
	 * @param procName
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<String[]> getDbTableFieldsByUser(String user, String password, String ip, String port, String server, String sql) {
		List<String[]> __list = null;
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			ResultSetMetaData metaDate = rs.getMetaData();

			int number = metaDate.getColumnCount();

			__list = new ArrayList<String[]>();

			for (int i = 1, j = number + 1; i < j; i++) {
				String __className = "";

				if ("java.math.BigDecimal".equals(metaDate.getColumnClassName(i))) {
					__className = "number";
				} else if ("java.sql.Timestamp".equals(metaDate.getColumnClassName(i))) {
					__className = "date";
				} else if ("java.lang.String".equals(metaDate.getColumnClassName(i))) {
					__className = "string";
				}

				String[] field = { metaDate.getColumnName(i).toLowerCase(), __className, Integer.toString(metaDate.getColumnDisplaySize(i)), metaDate.getColumnTypeName(i) };
				__list.add(field);
			}

		} catch (Exception e) {
			_log.debug(e.getMessage());
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return __list;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnDB __db = new ConnDB();
		List<String[]> __list = __db.getDbTableFieldsByUser("CPPT", "GGJCYY", "192.168.131.118", "1521", "orcl", "select * from p_user");

		System.out.println(__list);

		for (int i = 0, j = __list.size(); i < j; i++) {
			String[] __field = __list.get(i);
			System.out.println(__field[0]);
			System.out.println(__field[1]);
			System.out.println(__field[2]);
			System.out.println(__field[3]);
		}
	}

}
