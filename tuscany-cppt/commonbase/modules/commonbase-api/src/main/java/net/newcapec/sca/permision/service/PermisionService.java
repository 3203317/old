package net.newcapec.sca.permision.service;

import java.util.List;

import net.newcapec.sca.permision.Permision;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.param.FilterParam;

import org.oasisopen.sca.annotation.Remotable;

/**
 * <p>
 * Title: PermisionService
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
public interface PermisionService {

	public Permision getPermisionById(String sessionId, Integer id);

	public List<Permision> findPermisionList(String sessionId,
			Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit);

	public DojoListData findPermisionDojoList(DojoListParam param);

	public Permision insertPermision(String sessionId, Permision permision);

	public Permision updatePermision(String sessionId, Permision permision);

	public Permision delPermisionById(String sessionId, Integer id);

	public Permision delPermisionByIds(String sessionId, String ids);

	public Integer getPermisionListRowCount(String sessionId,
			Integer resourceId, List<FilterParam> filter);

}