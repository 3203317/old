package net.newcapec.sca.reportdsfield.das;

import java.util.List;

import net.newcapec.sca.reportdsfield.ReportDsField;

/**
 *
 * @author huangxin
 *
 */
public interface ReportDsFieldDAS {

	public List<ReportDsField> findReportDsFieldList(Integer domainId, Integer orgId, Integer reportDsId, Integer dsId);

	public Boolean updateReportDsField(ReportDsField reportDsField);

	public Boolean insertReportDsField(Integer reportDsId, Integer dsId);
}
