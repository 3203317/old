package net.foreworld.java.daos;

import net.foreworld.java.sysmanage.sysmanage_user.Sysmanage_userDAOImpl;
import net.foreworld.java.sysmanage.sysmanage_website.Sysmanage_websiteDAOImpl;
import net.foreworld.java.sysmanage.sysmanage_codetype.Sysmanage_codetypeDAOImpl;
import net.foreworld.java.sysmanage.sysmanage_code.Sysmanage_codeDAOImpl;
import net.foreworld.java.sysmanage.sysmanage_user_preference.Sysmanage_user_preferenceDAOImpl;
import net.foreworld.java.mail.mail_mailaccountt.Mail_mailaccounttDAOImpl;
import net.foreworld.java.mail.mail_maill.Mail_maillDAOImpl;
import net.foreworld.java.mail.mail_mailfilee.Mail_mailfileeDAOImpl;
import net.foreworld.java.mail.v_file_navigator.V_file_navigatorDAOImpl;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public class DaoFactory {
	
	private static final DaoFactory _instance = new DaoFactory();
	
	private DaoFactory() {}
	
	public static DaoFactory getInstance(){
		return _instance;
	}

	private SqlMapClient _sqlMap;

	public void setSqlMap(SqlMapClient $sqlMap) {
		this._sqlMap = $sqlMap;
	}
	
	public SqlMapClient getSqlMap() {
		return this._sqlMap;
	}
	
	public IDao getDao(String $daoName){
		
		IDao __idao = null;

		if(SYSMANAGE_USERDAO.equals($daoName)){
			__idao = Sysmanage_userDAOImpl.getInstance();
		}
		else		if(SYSMANAGE_WEBSITEDAO.equals($daoName)){
			__idao = Sysmanage_websiteDAOImpl.getInstance();
		}
		else		if(SYSMANAGE_CODETYPEDAO.equals($daoName)){
			__idao = Sysmanage_codetypeDAOImpl.getInstance();
		}
		else		if(SYSMANAGE_CODEDAO.equals($daoName)){
			__idao = Sysmanage_codeDAOImpl.getInstance();
		}
		else		if(SYSMANAGE_USER_PREFERENCEDAO.equals($daoName)){
			__idao = Sysmanage_user_preferenceDAOImpl.getInstance();
		}
		else		if(MAIL_MAILACCOUNTTDAO.equals($daoName)){
			__idao = Mail_mailaccounttDAOImpl.getInstance();
		}
		else		if(MAIL_MAILLDAO.equals($daoName)){
			__idao = Mail_maillDAOImpl.getInstance();
		}
		else		if(MAIL_MAILFILEEDAO.equals($daoName)){
			__idao = Mail_mailfileeDAOImpl.getInstance();
		}
		else		if(V_FILE_NAVIGATORDAO.equals($daoName)){
			__idao = V_file_navigatorDAOImpl.getInstance();
		}
		
		return __idao;
	}
	
	public static final String SYSMANAGE_USERDAO = "sysmanage_userDAO";
	public static final String SYSMANAGE_WEBSITEDAO = "sysmanage_websiteDAO";
	public static final String SYSMANAGE_CODETYPEDAO = "sysmanage_codetypeDAO";
	public static final String SYSMANAGE_CODEDAO = "sysmanage_codeDAO";
	public static final String SYSMANAGE_USER_PREFERENCEDAO = "sysmanage_user_preferenceDAO";
	public static final String MAIL_MAILACCOUNTTDAO = "mail_mailaccounttDAO";
	public static final String MAIL_MAILLDAO = "mail_maillDAO";
	public static final String MAIL_MAILFILEEDAO = "mail_mailfileeDAO";
	public static final String V_FILE_NAVIGATORDAO = "v_file_navigatorDAO";
	
}
