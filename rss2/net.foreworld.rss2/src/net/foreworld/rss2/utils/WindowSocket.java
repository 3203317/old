package net.foreworld.rss2.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author ����
 * @Nov 15, 2007 6:27:16 PM
 */
public class WindowSocket {
	  private Socket socket;
	  private OutputStream os = null;
	  private InputStream is = null;
	  private String address = "localhost";
	  private int port = 5678;
	  private static WindowSocket wsocket;
	  
	  public static WindowSocket getInstance(){
		  if(wsocket == null){
			  wsocket = new WindowSocket();
		  }
		  return wsocket;
	  }
	  
	  public WindowSocket(){
	  }
	  

	  public WindowSocket(String newAddress, int newPort){
	  }

	  /**
	   * ������֤���ݵ����ܻ���֤ description
	   * 
	   * @param xml ��֤XML�ַ���
	   * @return ���ݷ������
	   */
	  public String sendData(String xml){
	    try{
	      os.write(xml.getBytes());
	      os.flush();
	      return getData();
	    }
	    catch (Exception ex){
	      ex.printStackTrace();
	      try{
	        openSocket();
	      }
	      catch (Exception e){
	        e.printStackTrace();
	      }
	      return null;
	    }
	  }

	  /**
	   * ������֤���ݵ����ܻ���֤ description
	   * 
	   * @param xml ��֤XML�ַ���
	   * @return ���ݷ������
	   */
	  public String getData() throws Exception{
	    String ss = "";
	    boolean b = true;
	    byte temp[] = new byte[300];
	    while (b)
	    {
	      if (ss.toLowerCase().indexOf("#") < 0)
	      {
	        int len = is.read(temp);
	        if (len >= 0)
	        {
	          ss = ss + new String(temp, 0, len, "GB2312");
	        }
	        else
	        {
	          throw new Exception("read data error.....");
	        }
	      }
	      else
	      {
	        b = false;
	      }
	    }
	    ss = ss.substring(0, ss.toLowerCase().indexOf("#"));
	    // System.out.println("receive:" + ss);
	    return ss;
	  }

	  /**
	   * ������ description
	   */
		public void openSocket(){
			try{
				socket = new Socket(address,port);
				//	    socket.setSoTimeout(10000);
				os = socket.getOutputStream();
				is = socket.getInputStream();
				System.out.println("Info:����Զ�̷������ɹ���");
			}
			catch(Exception e){
				System.err.println("Error:����Զ�̷�����ʧ�ܡ�");
			}
		}

	  /**
	   * �ر����� description
	   */
	  public void closeSocket()
	  {
	    try
	    {
	      if (os != null)
	      {
	        os.close();
	      }
	      if (is != null)
	      {
	        is.close();
	      }
		  System.out.println("Info:�ر�Զ�����ӡ�");
	      socket.close();
	    }
	    catch (Exception ex)
	    {

	    }
	  }
	}
