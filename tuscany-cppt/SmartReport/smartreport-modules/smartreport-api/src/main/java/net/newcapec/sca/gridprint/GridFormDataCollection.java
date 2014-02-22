package net.newcapec.sca.gridprint;

import java.util.List;

import net.newcapec.sca.gridcondition.GridCondition;
import net.newcapec.sca.gridview.GridView;



public class GridFormDataCollection {

	 private GridView gridView;
	 private List<GridCondition>  gridConditionList;
	public GridView getGridView() {
		return gridView;
	}
	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}
	public List<GridCondition> getGridConditionList() {
		return gridConditionList;
	}
	public void setGridConditionList(List<GridCondition> gridConditionList) {
		this.gridConditionList = gridConditionList;
	}




}
