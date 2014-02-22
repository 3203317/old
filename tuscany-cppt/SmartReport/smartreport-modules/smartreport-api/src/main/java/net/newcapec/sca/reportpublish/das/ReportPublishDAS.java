package net.newcapec.sca.reportpublish.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.reportpublish.ReportPublish;

/**
 * <p>Title: 接口 </p>
 * <p>Description:报表数据构件接口</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-15
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public interface ReportPublishDAS {

	public  List<ReportPublish> findUnpublishReportList(Integer domainId, Integer orgId,
			List<FilterParam> filter, Integer begin, Integer limit);
	public  Integer  getUnpublishReportListRowCount(Integer resourceId, List<FilterParam> filter);

	public  Boolean  publishReport(ReportPublish  reportPublish);
	public Boolean  revokePublishReport(ReportPublish  reportPublish);

}
