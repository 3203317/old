package net.newcapec.sca.report;

import java.util.List;

import net.newcapec.sca.componentdataset.ComponentDataset;
import net.newcapec.sca.gridprint.GridPrint;
import net.newcapec.sca.gridview.GridView;
import net.newcapec.sca.reportdsfield.ReportDsField;
import net.newcapec.sca.reportdsparam.ReportDsParam;
import net.newcapec.sca.result.ResultMsg;

/**
 *
 *实体描述：grid
 *@author: 赵鹏飞
 *@date： 日期：2013-1-14 时间：下午04:12:02
 *@version 1.0
 *修改人：
 *修改时间：
 *修改备注：
 */
public class ReportGridConditionDataCollection {

	private ResultMsg resultMsg;
	private GridView gridView;//外观定制
	private GridPrint gridPrint;//打印设置
	private List<ReportDsField> reportDsField;//字段
	private List<ReportDsParam> reportDsParam;//查询参数
	private ComponentDataset componentDataset;//控件数据集
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
	public GridView getGridView() {
		return gridView;
	}
	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}
	public List<ReportDsField> getReportDsField() {
		return reportDsField;
	}
	public void setReportDsField(List<ReportDsField> reportDsField) {
		this.reportDsField = reportDsField;
	}
	public List<ReportDsParam> getReportDsParam() {
		return reportDsParam;
	}
	public void setReportDsParam(List<ReportDsParam> reportDsParam) {
		this.reportDsParam = reportDsParam;
	}
	public GridPrint getGridPrint() {
		return gridPrint;
	}
	public void setGridPrint(GridPrint gridPrint) {
		this.gridPrint = gridPrint;
	}
	public ComponentDataset getComponentDataset() {
		return componentDataset;
	}
	public void setComponentDataset(ComponentDataset componentDataset) {
		this.componentDataset = componentDataset;
	}

}
