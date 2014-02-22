package net.newcapec.sca.gridcondition;

import java.util.List;

import net.newcapec.sca.diccode.DicItem;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.gridview.GridView;

/**
 *
 *类描述：该实体类包含字段、级联字段、关系、查询面板相关的List<Object>
 *@author: 赵鹏飞
 *@date： 日期：2012-12-5 时间：上午11:52:23
 *@version 1.0
 *修改人：
 *修改时间：
 *修改备注：
 */
public class GridConditonDataCollection {
	private List<FieldPrep> field;
	private List<DicItem> dis;
	private GridView gridView;
	private List<GridCondition> gridCondition;
	public List<FieldPrep> getField() {
		return field;
	}
	public void setField(List<FieldPrep> field) {
		this.field = field;
	}
	public List<DicItem> getDis() {
		return dis;
	}
	public void setDis(List<DicItem> dis) {
		this.dis = dis;
	}
	public GridView getGridView() {
		return gridView;
	}
	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}
	public List<GridCondition> getGridCondition() {
		return gridCondition;
	}
	public void setGridCondition(List<GridCondition> gridCondition) {
		this.gridCondition = gridCondition;
	}

}
