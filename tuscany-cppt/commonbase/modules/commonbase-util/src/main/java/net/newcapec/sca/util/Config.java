package net.newcapec.sca.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
/**
*
*类描述：读取session.ini 文件
*@author: 赵鹏飞
*@date： 日期：2012-9-27 时间：下午03:48:08
*@version 1.0
*修改人：
*修改时间：
*修改备注：
*/
public class Config {
	private String config = "session.ini";
	private static InputStreamReader streamReader;
	private static final Logger _log = Logger.getLogger(Config.class);
	public Config(){
		if(null == streamReader){
			try{
				streamReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(config));
			}
			catch(Exception e){
				_log.error(e.toString());
			}
		}
	}
	public  String getByKey(String key) throws IOException{
		String strLine, value = "";
		InputStreamReader streamReader1 = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(config));
		BufferedReader bufferedReader = new BufferedReader(streamReader1);
		try {
			while ((strLine = bufferedReader.readLine()) != null) {
				strLine = strLine.trim();
				strLine = strLine.split("[#]")[0];
				strLine = strLine.trim();
				String[] strArray = strLine.split("=");
				if (strArray.length == 1) {
					value = strArray[0].trim();
					if (value.equalsIgnoreCase(key)) {
						value = "";
						return value;
					}
				} else if (strArray.length == 2) {
					value = strArray[0].trim();
					if (value.equalsIgnoreCase(key)) {
						value = strArray[1].trim();
						return value;
					}
				} else if (strArray.length > 2) {
					value = strArray[0].trim();
					if (value.equalsIgnoreCase(key)) {
						value = strLine.substring(strLine.indexOf("=") + 1).trim();
						return value;
					}
				}
			}
		}catch(Exception e){

		} finally {
			bufferedReader.close();
		}
		return null;
	}

	public static Boolean setValue(String key,int value) throws IOException{
		String fileContent, allLine, strLine, newLine, remarkStr;
		String getValue;
		BufferedReader bufferedReader = new BufferedReader(streamReader);
		fileContent = "";
		try {
			while ((allLine = bufferedReader.readLine()) != null) {
				allLine = allLine.trim();
				if (allLine.split("[#]").length > 1)
					remarkStr = ";" + allLine.split("#")[1];
				else
					remarkStr = "";
				strLine = allLine.split("#")[0];
				strLine = strLine.trim();
				String[] strArray = strLine.split("=");
				getValue = strArray[0].trim();
				if (getValue.equalsIgnoreCase(key)) {
					newLine = getValue + " = " + value + " " + remarkStr;
					fileContent += newLine + "\r\n";
					while ((allLine = bufferedReader.readLine()) != null) {
						fileContent += allLine + "\r\n";
					}
					bufferedReader.close();
//					BufferedWriter bufferedWriter = new BufferedWriter(
//							new Inputs FileWriter(file, false));
//					bufferedWriter.write(fileContent);
//					bufferedWriter.flush();
//					bufferedWriter.close();

					return true;
				}
				fileContent += allLine + "\r\n";
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			bufferedReader.close();
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		Config config = new Config();
		String aa = config.getByKey("age");
		System.out.println(aa);
		config.setValue("age", 1900);
	}
}
