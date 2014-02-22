package net.foreworld.java.services;

import net.foreworld.java.sysmanage.sysmanage_user.Sysmanage_userServiceImpl;
import net.foreworld.java.sysmanage.sysmanage_website.Sysmanage_websiteServiceImpl;
import net.foreworld.java.sysmanage.sysmanage_codetype.Sysmanage_codetypeServiceImpl;
import net.foreworld.java.sysmanage.sysmanage_code.Sysmanage_codeServiceImpl;
import net.foreworld.java.sysmanage.sysmanage_user_preference.Sysmanage_user_preferenceServiceImpl;
import net.foreworld.java.mail.mail_mailaccountt.Mail_mailaccounttServiceImpl;
import net.foreworld.java.mail.mail_maill.Mail_maillServiceImpl;
import net.foreworld.java.mail.mail_mailfilee.Mail_mailfileeServiceImpl;
import net.foreworld.java.mail.v_file_navigator.V_file_navigatorServiceImpl;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public class ServiceFactory {

	private static final ServiceFactory _instance = new ServiceFactory();
	
	public ServiceFactory() {}
	
	public static ServiceFactory getInstance(){
		return _instance;
	}
	
	public IService getService(String $serviceName){
		
		IService __iservice = null;

		if(SYSMANAGE_USERSERVICE.equals($serviceName)){
			__iservice = Sysmanage_userServiceImpl.getInstance();
		}
		else		if(SYSMANAGE_WEBSITESERVICE.equals($serviceName)){
			__iservice = Sysmanage_websiteServiceImpl.getInstance();
		}
		else		if(SYSMANAGE_CODETYPESERVICE.equals($serviceName)){
			__iservice = Sysmanage_codetypeServiceImpl.getInstance();
		}
		else		if(SYSMANAGE_CODESERVICE.equals($serviceName)){
			__iservice = Sysmanage_codeServiceImpl.getInstance();
		}
		else		if(SYSMANAGE_USER_PREFERENCESERVICE.equals($serviceName)){
			__iservice = Sysmanage_user_preferenceServiceImpl.getInstance();
		}
		else		if(MAIL_MAILACCOUNTTSERVICE.equals($serviceName)){
			__iservice = Mail_mailaccounttServiceImpl.getInstance();
		}
		else		if(MAIL_MAILLSERVICE.equals($serviceName)){
			__iservice = Mail_maillServiceImpl.getInstance();
		}
		else		if(MAIL_MAILFILEESERVICE.equals($serviceName)){
			__iservice = Mail_mailfileeServiceImpl.getInstance();
		}
		else		if(V_FILE_NAVIGATORSERVICE.equals($serviceName)){
			__iservice = V_file_navigatorServiceImpl.getInstance();
		}
		
		return __iservice;
	}
	
	public static final String SYSMANAGE_USERSERVICE = "sysmanage_userService";
	public static final String SYSMANAGE_WEBSITESERVICE = "sysmanage_websiteService";
	public static final String SYSMANAGE_CODETYPESERVICE = "sysmanage_codetypeService";
	public static final String SYSMANAGE_CODESERVICE = "sysmanage_codeService";
	public static final String SYSMANAGE_USER_PREFERENCESERVICE = "sysmanage_user_preferenceService";
	public static final String MAIL_MAILACCOUNTTSERVICE = "mail_mailaccounttService";
	public static final String MAIL_MAILLSERVICE = "mail_maillService";
	public static final String MAIL_MAILFILEESERVICE = "mail_mailfileeService";
	public static final String V_FILE_NAVIGATORSERVICE = "v_file_navigatorService";
							   
}
