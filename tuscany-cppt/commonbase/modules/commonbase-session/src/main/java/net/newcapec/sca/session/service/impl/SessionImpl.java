package net.newcapec.sca.session.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;
import net.newcapec.sca.util.Config;
import net.newcapec.sca.util.DASFactory;
import net.newcapec.sca.util.DateUtil;
import net.newcapec.sca.util.UUIDGenerator;

import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class SessionImpl implements SessionService {
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
			this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String DAS_CONFIG = "/session.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(DAS_CONFIG).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}


	public Session getSession(String sessionId) {
		DAS das = this.getDAS();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = null;
		try{
			Command  command = das.getCommand("getSessionById");
			command.setParameter("id", sessionId);
			DataObject root =  command.executeQuery();
			List sessionList = root.getList("p_session");
			if(sessionList.size()>0){
				DataObject params = (DataObject) sessionList.get(0);
				session = new Session();
				String id = params.getString("id");//会话id
				BigDecimal ageTime = params.getBigDecimal("age");//超时间隔
				//Date updateTime = params.getDate("update_time");//最后访问时间
				String invalidTime = params.getString("b");//失效时间
				boolean b = this.isTimeout(sessionId, invalidTime);
				if(b == false){
					session.setId(id);//会话 id
					session.setState(1);//会话状态
					//session.setAge(params.getBigDecimal("age").toString());//超时间隔
					//session.setUpdate_time(sdf.format(params.getDate("update_time")));//访问时间
					session.setUser_code(params.getBigDecimal("user_code").toString());
					//session.setContent(params.getString("content"));
					session.setDomain_code(params.getString("domain_code"));
					session.setUnit_code(params.getString("unit_code"));
				}else{
					session = new Session();
					session.setId(null);
					session.setState(0);
				}
			}else{
				session = new Session();
				session.setId(null);
				session.setState(0);
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			das.releaseResources();
		}
		return session;
	}

	public Session createSession(Session session) {
		DAS das = this.getDAS();
		try{
			Command  command = das.getCommand("createSession");
			String uuid = UUIDGenerator.getUUID();
			command.setParameter("id", uuid);
			command.setParameter("user_code",session.getUser_code());
			Config config = new Config();//获取配置文件中的超时间隔
			command.setParameter("age", config.getByKey("age"));//超时间隔
			command.setParameter("update_time",  sdf.format(new Date()));//创建最后访问时间
			if(session.getContent() == null){
				command.setParameter("content", "");
			}else{
				command.setParameter("content", session.getContent());
			}
			command.setParameter("domain_code", session.getDomain_code());
			command.setParameter("unit_code", session.getUnit_code());
			String invalidTime = DateUtil.invalidDate(session.getInvalid_date());//创建失效时间
			command.setParameter("invalid_date", invalidTime);
			command.execute();
			this.deleteByInvalidDate();//删除所有失效会话
			session.setState(1);
			session.setId(uuid);
		}catch(Exception e){
			session.setState(0);
			e.printStackTrace();
		}finally{
			das.releaseResources();
		}
		return session;
	}
	//会话状态立即失效
	public Boolean invalid(String sessionId) {
		boolean  b = false;
		b = this.deleteBySessionId(sessionId);
		return b;
	}
	//当 b == true时，说明会话失效
	private Boolean isTimeout(String sessionId,String invalidTime) throws ParseException {
		boolean b = true ;
		String currentTime = sdf.format(new Date());
		Date updateTime = sdf.parse(currentTime);
		Date invalidDate = sdf.parse(invalidTime);
		if(updateTime.after(invalidDate)){
			this.deleteBySessionId(sessionId);//删除会话信息
			b = true;
		}else{
			Session session = new Session();
			session.setId(sessionId);
			Session session1 = this.update(session);
			if(session1.getState() == 1){
				b = false;
			}else{
				b = true;
			}
		}
		return b;
	}

	public Session update(Session session) {
		DAS das = this.getDAS();
		Session session1 = new Session();
		try{
			Command  command = das.getCommand("updateSessionById");
			command.setParameter("update_time", sdf.format(new Date()));//创建最后访问时间
			String invalidTime = DateUtil.invalidDate(session.getInvalid_date());//更新失效时间
			command.setParameter("invalid_date", invalidTime);
			command.setParameter("id", session.getId());
			command.execute();
			session1.setState(1);
			return session1;
		}catch(Exception e){
			System.out.println(e.toString());
			session1.setState(0);
			return session1;
		}finally{
			das.releaseResources();
		}
	}
	//根据sessionid删除失效的会话
	private Boolean deleteBySessionId(String sessionId){
		DAS das = this.getDAS();
		try{
			Command  command = das.getCommand("deleteSessionById");
			command.setParameter("id",sessionId);
			command.execute();
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
			return false;
		}finally{
			das.releaseResources();
		}
	}
	//删除所有失效的会话信息
	private Boolean deleteByInvalidDate(){
		DAS das = this.getDAS();
		try{
			Command  command = das.getCommand("deleteInvalidSession");
			command.execute();
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
			return false;
		}finally{
			das.releaseResources();
		}
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		SessionImpl impl = new SessionImpl();
		Session session = new Session();
		session.setAge("123401111");
		session.setId("11334");
		session.setUpdate_time(sdf.format(date));
		session.setDomain_code("1111");
		session.setUnit_code("224");
		session.setUser_code("12312312");
		session.setContent("dddddsdfds222222");
		session.setInvalid_date("");
		impl.createSession(session);
		//impl.getSession("9850e73a05a04604b0ebf9b133120873");
	//	System.out.println(sdf.format(date));
	//	Session session = impl.getSession("1211");
	//	System.out.println(session.getId());
	//	System.out.println(session.getState());
		//impl.update(session);
		//impl.delete("1211");
		System.out.println(UUIDGenerator.getUUID());
	//	System.out.println(dataHelper.toDateTime(new Date()));

	}
}
