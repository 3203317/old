package net.newcapec.sca.darule.service;

import net.newcapec.sca.darule.DataAccessRule;

import org.oasisopen.sca.annotation.Remotable;

/**
 * <p>
 * Title: DataaccessruleService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright(c) 2011 郑州新开普电子股份有限公司
 * </p>
 *
 * @author 黄鑫 (huangxin)
 * @version
 * @data 创建日期：2011-11-11 修改日期： 修改人： 复审人：
 * @generated
 */
@Remotable
public interface DataAccessRuleService {

	public DataAccessRule getDataAccessRuleByResId(String sessionId, Integer id);

	public DataAccessRule setDataAccessRule(String sessionId, DataAccessRule dataAccessRule);

	public DataAccessRule delDataAccessRuleByResId(String sessionId, Integer id);

}