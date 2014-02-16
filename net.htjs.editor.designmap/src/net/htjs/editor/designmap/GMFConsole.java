package net.htjs.editor.designmap;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


public class GMFConsole {
	private static MessageConsoleStream consoleStream;
	private static MessageConsole console;
	private static SimpleDateFormat sdf;
	
	static{
		console = new MessageConsole("DesignMap", Activator.getImageDescriptor("icons/console_view.gif"));
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{console});
		consoleStream = console.newMessageStream();
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	}
	  
	public static void print(Object message){
		consoleStream.print(sdf.format(new Date())+" "+message);
	}
	  
	public static void println(Object message){
		if(message instanceof Exception){
			Exception ex=(Exception)message;
			consoleStream.println(ex.getClass().getName()+":"+ex.getMessage());
			StackTraceElement e[]=ex.getStackTrace();
			for(int i=0;i<e.length;i++){
				consoleStream.println("at "+e[i].toString());
			}
		}
		else{
			consoleStream.println(sdf.format(new Date())+" "+message);
		}
	}
	  
	public static void clearConsole(){
		console.clearConsole();
	}
	
	public static OutputStream getOutputStream(){
    return consoleStream;
  }
	
	public static void flush(){
    try
    {
      consoleStream.flush();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
