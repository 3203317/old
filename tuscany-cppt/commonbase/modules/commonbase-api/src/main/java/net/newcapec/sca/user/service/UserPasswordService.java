package net.newcapec.sca.user.service;

/**
 * <p>Title: service接口 </p>
 * <p>Description:用户密码修改业务构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2012-10-26
 * 修改日期：
 * 修改人：
 * 复审人：
 */

import org.oasisopen.sca.annotation.Remotable;

import net.newcapec.sca.user.UserBaseInfo;


@Remotable
public interface UserPasswordService {

	public UserBaseInfo resetPassword(String sessionId,UserBaseInfo userBaseInfo);
	public UserBaseInfo updatePassword(String sessionId,UserBaseInfo userBaseInfo);
	public UserBaseInfo encodePassword(UserBaseInfo userBaseInfo);
	public  String  md5EncodePassword(String  rawPass );


}
