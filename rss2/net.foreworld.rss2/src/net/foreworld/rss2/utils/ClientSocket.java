package net.foreworld.rss2.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket¿Í»§¶Ë
 * @author »ÆöÎ
 * @Nov 17, 2007 9:54:04 AM
 */
public class ClientSocket {
	  private Socket cs;
	  private OutputStream os = null;
	  private InputStream is = null;
	  private String ip;
	  private int port;
	  
	  public ClientSocket(String ip,int port){
		  this.port = port;
		  this.ip = ip;
	  }
	  
	  public int sendData(String data){
		  return 1;
	  }
	  
	  public String getData(){
		  String data = "";
		  int len = 0;
		  boolean bool = true;
		  byte temp[] = new byte[300];
		  while(bool){
			  if(data.toLowerCase().indexOf("<!--OVERDATA-->") < 0){
				  try{
					  len = this.is.read(temp);
				  }
				  catch(Exception e){
					  System.err.println(e.getMessage());
					  break;
				  }
				  if(len >= 0){
					  try{
						  data = data + new String(temp,0,len,"GB2312");
					  }
					  catch(Exception e){
						  System.err.println(e.getMessage());
						  break;
					  }
				  }
				  else{
					  System.out.println("read data error...");
					  break;
				  }
			  }
			  else{
				  bool = false;
			  }
		  }
		  if(data.equals("")) return "";
		  return data.substring(0, data.toLowerCase().indexOf("<!--OVERDATA-->"));
	  }

}
