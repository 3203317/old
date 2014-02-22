package net.newcapec.sca.gridcondition;

import java.util.List;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.result.ResultMsg;

public class GridStatDataCollection {
	private List<FieldPrep> fieldPrepList;
	private List<DicItem> dicItemList;
	private ResultMsg resultMsg;
	public List<FieldPrep> getFieldPrepList() {
		return fieldPrepList;
	}
	public void setFieldPrepList(List<FieldPrep> fieldPrepList) {
		this.fieldPrepList = fieldPrepList;
	}
	public List<DicItem> getDicItemList() {
		return dicItemList;
	}
	public void setDicItemList(List<DicItem> dicItemList) {
		this.dicItemList = dicItemList;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}



}
