package net.newcapec.sca.reportpublish.service;
/**
 * <p>Title: 接口 </p>
 * <p>Description:报表业务构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-15
 * 修改日期：
 * 修改人：
 * 复审人：
 */


import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.reportpublish.ReportPublish;

import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface ReportPublishService {

	public  DojoListData findUnpublishReportDojoList(DojoListParam param);
	public  ReportPublish  publishReport(String sessionId,ReportPublish reportPublish);
	public  ReportPublish  revokePublishReport(String sessionId,ReportPublish  reportPublish);

}
