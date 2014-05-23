package cn.newcapec.function.digitalcampus.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.newcapec.function.digitalcampus.common.SoftListener;

public class ApplicationContextListener implements ServletContextListener,SoftListener {

	/**全局context**/
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
	public void contextDestroyed(ServletContextEvent event) {
		applicationContext = null;

	}

	public synchronized void contextInitialized(ServletContextEvent event) {
//		try {
//			ApplicationContext appContext = null;
//			
//			String serverType = event.getServletContext().getInitParameter("serverType");
//
//			String appContextXml = event.getServletContext().getInitParameter("contextConfigLocation");
//			
//			if (appContextXml == null || "".equals(appContextXml)) {
//				throw new RuntimeException(
//						"请在web.xml中设置参数:contextConfigLocation,applicationContext.xml的位置.");
//			}
//			if ("tomcat".equals(serverType.toLowerCase())) {
//				
//				appContext = (ApplicationContext) new ClassPathXmlApplicationContext(
//						new String[] { appContextXml });
//				
//			} else if ("jboss".equals(serverType.toLowerCase())) {
//				
//				appContext = (ApplicationContext) new VFSClassPathXmlApplicationContext(
//						new String[] { appContextXml });
//				
//			}
//			applicationContext =appContext;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		applicationContext =  WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());

	}

}
