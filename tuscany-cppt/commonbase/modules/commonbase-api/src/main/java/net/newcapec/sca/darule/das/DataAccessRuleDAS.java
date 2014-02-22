package net.newcapec.sca.darule.das;

import net.newcapec.sca.darule.DataAccessRule;

/**
 * <p>
 * Title: DataaccessruleDAS
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
public interface DataAccessRuleDAS {

	public DataAccessRule getDataAccessRuleByResId(Integer id);

	public Boolean insertDataAccessRule(DataAccessRule dataAccessRule);

	public Boolean updateDataAccessRule(DataAccessRule dataAccessRule);

	public Boolean delDataAccessRuleByResId(Integer id);

}