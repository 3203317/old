package net.newcapec.sca.report.das.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 *
 * @author huangxin
 *
 */
public class TestDB {

	/**
	 * 执行存储过程测试
	 *
	 * @param user
	 * @param password
	 * @param ip
	 * @param port
	 * @param server
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<JSONObject> callProc(String user, String password, String ip, String port, String server, String sql) {
		List<String[]> __list = null;
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<JSONObject> __jsons = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			CallableStatement proc = null;

			String __sql = "{ call " + sql + " }";

			proc = conn.prepareCall(__sql);
			proc.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			proc.execute();
			rs = (ResultSet) proc.getObject(1);

			ResultSetMetaData metaDate = rs.getMetaData();

			int number = metaDate.getColumnCount();

			__jsons = new ArrayList<JSONObject>();

			while (rs.next()) {
				JSONObject __jObj = new JSONObject();
				for (int i = 1, j = number + 1; i < j; i++) {
					__jObj.put(metaDate.getColumnName(i), rs.getString(metaDate.getColumnName(i)));
				}
				__jsons.add(__jObj);
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
			return __jsons;
		}
	}

	@SuppressWarnings("finally")
	public List<JSONObject> testSql(String user, String password, String ip, String port, String server, String sql) {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		List<JSONObject> __jsons = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + server;
			conn = DriverManager.getConnection(url, user, password);

			stm = conn.createStatement();
			ResultSet __set = stm.executeQuery(sql);

			ResultSetMetaData metaDate = __set.getMetaData();

			int number = metaDate.getColumnCount();

			__jsons = new ArrayList<JSONObject>();

			while (__set.next()) {
				JSONObject __jObj = new JSONObject();
				for (int i = 1, j = number + 1; i < j; i++) {
					__jObj.put(metaDate.getColumnName(i), __set.getString(metaDate.getColumnName(i)));
				}
				__jsons.add(__jObj);
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
			return __jsons;
		}
	}

	public static void main(String[] args) {
		TestDB __db = new TestDB();

		List<JSONObject> __list = __db.callProc("CPPT", "GGJCYY", "192.168.131.118", "1521", "orcl", " TESTC1 (?,'1','1')");

		System.out.println(__list.size());

		List<JSONObject> __list2 = __db.testSql("CPPT", "GGJCYY", "192.168.131.118", "1521", "orcl", "select CODE,NAME from p_role ");

		System.out.println(__list2.size());
	}

}
