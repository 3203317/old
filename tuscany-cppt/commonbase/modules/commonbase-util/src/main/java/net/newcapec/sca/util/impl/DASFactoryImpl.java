package net.newcapec.sca.util.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.newcapec.sca.dbconn.DBConnection;
import net.newcapec.sca.util.DASFactory;

import org.apache.tuscany.das.rdb.DAS;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DASFactoryImpl implements DASFactory{
	private static final String DEFAULTDBCONNECTION_DB = "oracle";
	private static final String DEFAULTDBCONNECTION_CONFIGFILE = "1";
	//生成das
	public DAS createDAS(DBConnection db,String path)
	{
		if(null != path && !"".equals(path))
		{
			DAS das = DAS.FACTORY.createDAS(this.getConfigStr(db,path));
			return das;
		}
		else
		{
			try
			{
				String url="";
				if (DEFAULTDBCONNECTION_CONFIGFILE.equals(db.getDataBaseName())||DEFAULTDBCONNECTION_DB.equalsIgnoreCase(db.getDataBaseName()))
				{
					url = "jdbc:oracle:thin:@"+db.getConnectIP()+":" +db.getConnectPort()+":"+db.getConnectSID();
					Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				}
				Connection conn = DriverManager.getConnection(url, db.getAccoutName(), db.getAccountPassword());
				conn.setAutoCommit(false);
				DAS das = DAS.FACTORY.createDAS(conn);
				return das;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	}

	//根据DBConnection提供的数据库丽连接配置信息修改document对象返回inputstream
	private InputStream getConfigStr(DBConnection db,String path)
	{
		Document doc = this.loadConfigByFileName(path);
		Element connInfo = doc.getRootElement().addElement("ConnectionInfo").addElement("ConnectionProperties");

		//1--是oracle数据库，如果没传就会载入文件的配置
		if(null != db &&
				(DEFAULTDBCONNECTION_DB.equals(db.getDataBaseName())
						||DEFAULTDBCONNECTION_CONFIGFILE.equals(db.getDataBaseName())))
		{
			connInfo.addAttribute("driverClass","oracle.jdbc.driver.OracleDriver");
			connInfo.addAttribute("databaseURL","jdbc:oracle:thin:@"+db.getConnectIP()+":"+db.getConnectPort()+":"+db.getConnectSID());
			connInfo.addAttribute("userName",db.getAccoutName());
			connInfo.addAttribute("password",db.getAccountPassword());
			connInfo.setAttributeValue("loginTimeout","600000");
		}

		InputStream inputStream = null;
		try
		{
			inputStream = new ByteArrayInputStream(doc.asXML().getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException ue)
		{
			ue.printStackTrace();
		}
		return inputStream;
	}
	/**
	 * @warning 这个是按照windows路径的处理方式进行处理的。如需要linux请加配置后重构
	 * @param path 绝对路径
	 * @return dom4j对象
	 */
	private  Document loadConfigByFileName(String path)
	{
		Document document = null;
		try
		{
			SAXReader saxReader = new SAXReader();
			if(-1 != path.indexOf("!"))
			{
				//在windows部署打包打开工程下走此路径
				path = path.substring(6, path.length());
				String jarPath = path.split("!")[0];
				jarPath = jarPath.replace("%5C", "/");
				String filePath = path.split("!")[1];
				filePath = filePath.substring(1, filePath.length());
				JarFile jarFile = new JarFile(jarPath);
				JarEntry entry = jarFile.getJarEntry(filePath);
				InputStream input = jarFile.getInputStream(entry);
				document = saxReader.read(input);
			}
			else
			{
				//运行test的时候走次路径
				path = path.substring(1, path.length());
				File file = new File(path);
				document = saxReader.read(file);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return document;
	}
}
