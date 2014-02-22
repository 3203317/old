package net.newcapec.sca.reportdsfield.service;

import java.util.List;

import net.newcapec.sca.reportdsfield.ReportDsField;

import org.oasisopen.sca.annotation.Remotable;

/**
 *
 * @author huangxin
 *
 */
@Remotable
public interface ReportDsFieldService {

	public List<ReportDsField> findReportDsFieldList(String sessionId, Integer reportDsId, Integer dsId);

	public ReportDsField updateReportDsField(String sessionId, ReportDsField reportDsField);
}
