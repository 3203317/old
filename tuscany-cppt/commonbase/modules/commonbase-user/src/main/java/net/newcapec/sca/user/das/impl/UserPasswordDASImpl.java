package net.newcapec.sca.user.das.impl;

import java.util.List;

import net.newcapec.sca.dbconn.service.DefDBConnService;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.user.UserBaseInfo;
import net.newcapec.sca.user.das.UserPasswordDAS;
import net.newcapec.sca.util.DASFactory;

import org.apache.log4j.Logger;
import org.apache.tuscany.das.rdb.Command;
import org.apache.tuscany.das.rdb.DAS;
import org.oasisopen.sca.annotation.Reference;

import commonj.sdo.DataObject;

public class UserPasswordDASImpl implements UserPasswordDAS {


	private static final Logger _log = Logger
			.getLogger(UserPasswordDASImpl.class);

	private DefDBConnService defDBConnService;

	@Reference(name = "defDBConnService", required = true)
	public void setDefDBConnService(DefDBConnService defDBConnService) {
		this.defDBConnService = defDBConnService;
	}

	//默认的功能模块das配置文件
	private static String FUNCTIONCONFIGFILE = "/user.xml";
	public DAS getDAS()
	{
		String path = "";
		path = this.getClass().getResource(FUNCTIONCONFIGFILE).getPath();
		DAS das = DASFactory.Factory.createDAS(defDBConnService.getDefDBConn(),path);
		return das;
	}

	public UserBaseInfo resetPassword(UserBaseInfo userBaseInfo) {
		// TODO Auto-generated method stub
		DAS das = this.getDAS();
		Command command = das.getCommand("updatePasswordByAccountId");
		command.setParameter(1, userBaseInfo.getPassword());
		command.setParameter(2, Integer.parseInt(userBaseInfo.getAccount_id()));
		ResultMsg __rm = new  ResultMsg();
		try
		{
			command.execute();
			__rm.setStatus(1);
		}
		catch (Exception e)
		{
			__rm.setErrorId(205);
			__rm.setStatus(0);
			__rm.setErrorMsg("用户密码重置失败");
			_log.info("user:resetPassword");
			_log.error("用户密码重置失败！");

		}

		userBaseInfo.setResultMsg(__rm);
		return userBaseInfo;
	}

	public Boolean updatePassword(UserBaseInfo userBaseInfo) {
		DAS das = this.getDAS();
		Command command = das.getCommand("updatePasswordByAccountId");
		command.setParameter(1, userBaseInfo.getPassword());
		command.setParameter(2, Integer.parseInt(userBaseInfo.getAccount_id()));
		Boolean  result = false  ;
		try
		{
			command.execute();
			result = true;
            return result;
		}catch(Exception e){
			result = false;
			_log.info("user:updatePassword");
			_log.error("用户密码修改失败！");
			return result;

		}


	}

	public UserBaseInfo getLoginPassword(UserBaseInfo userBaseInfo) {

		DAS das = this.getDAS();
		Command command = das.getCommand("getPasswordByName");
		command.setParameter(1, userBaseInfo.getName());
		System.out.println("loginname:"+userBaseInfo.getName());
		DataObject dobj = null;
		try
		{
			dobj = command.executeQuery();
			List<DataObject> list = dobj.getList("p_user");
			if ( list !=null){
				DataObject obj = (DataObject)(dobj.getList("p_user").get(0));
				userBaseInfo.setPassword(obj.getString("password"));
				userBaseInfo.setOpassword(obj.getString("password"));
				userBaseInfo.setCode(obj.getString("code"));
				userBaseInfo.setAccount_id(obj.getString("account_id"));
			}
		}
		catch (Exception e)
		{
			/* 设置操作结果消息 */
			ResultMsg __resultMsg = new ResultMsg();
			__resultMsg.setErrorId(102);
			__resultMsg.setErrorMsg("用户名或密码错误！");
			_log.info("user:getPassword");
			_log.error("获取用户密码失败！");

			userBaseInfo.setResultMsg(__resultMsg);
		}

		return userBaseInfo;
	}

	public UserBaseInfo getPassword(UserBaseInfo userBaseInfo) {

		DAS das = this.getDAS();
//		Command command = das.getCommand("getPasswordByName");
//		command.setParameter(1, userBaseInfo.getName());
		Command command = das.getCommand("getPasswordByAccountId");
		command.setParameter(1, Integer.parseInt(userBaseInfo.getAccount_id()));
		DataObject dobj = null;
		ResultMsg __resultMsg = new ResultMsg();
		try
		{
			dobj = command.executeQuery();
			List<DataObject> list = dobj.getList("p_user");
			System.out.println(list.size());
			if ( dobj.getList("p_user") !=null){
				DataObject obj = (DataObject)(dobj.getList("p_user").get(0));
				userBaseInfo.setPassword(obj.getString("password"));
				userBaseInfo.setOpassword(obj.getString("password"));
				userBaseInfo.setCode(obj.getString("code"));
				userBaseInfo.setAccount_id(obj.getString("account_id"));
				__resultMsg.setStatus(1);
			}else{
				__resultMsg.setStatus(0);
			}

			userBaseInfo.setResultMsg(__resultMsg);
			return userBaseInfo;
		}
		catch (Exception e)
		{
			/* 设置操作结果消息 */
			__resultMsg.setStatus(0);
			_log.info("user:getPassword");
			_log.error("获取用户密码失败！");
			userBaseInfo.setResultMsg(__resultMsg);
			return userBaseInfo;
		}



	}



}
