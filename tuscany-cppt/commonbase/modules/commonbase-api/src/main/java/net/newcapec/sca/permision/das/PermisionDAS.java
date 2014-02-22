package net.newcapec.sca.permision.das;

import java.util.List;

import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.param.FilterParam;

/**
 * <p>
 * Title: PermisionDAS
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
public interface PermisionDAS {

	public Permision getPermisionById(Integer id);

	public List<Permision> findPermisionList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);

	public Boolean insertPermision(Permision permision);

	public Boolean updatePermision(Permision permision);

	public Boolean delPermisionById(Integer id);

	public Boolean delPermisionByIds(Integer[] ids);

	public Integer getPermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}