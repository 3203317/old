package net.newcapec.sca.session.service;

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.session.Session;
/**
 *
 *接口描述：会话构件服务接口
 *@author: 赵鹏飞
 *@date： 日期：2012-9-11 时间：下午02:06:39
 *@version 1.0
 *修改人：
 *修改时间：
 *修改备注：
 */
@Remotable
public interface SessionService {
	//获取会话
	public Session getSession(String sessionId);
	//创建会话信息
	public Session createSession(Session session);
//	public Boolean isTimeout(String sessionId);
	//更新会话信息
	public Session update(Session session);
	//会话立即失效
	public Boolean invalid(String sessionId);

}
