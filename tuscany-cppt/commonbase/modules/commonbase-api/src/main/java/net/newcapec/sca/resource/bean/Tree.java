package net.newcapec.sca.resource.bean;

public class Tree {
	private String treeId;//树的id
	private String paneId; //面板id
	private String treeName;//树面板名称
	private String queryName;//展开树
	private MenuItem[] items;//树面板包含的树节点
	private String iconClass;//树面板的小图标


	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public String getPaneId() {
		return paneId;
	}
	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}
	public String getTreeName() {
		return treeName;
	}
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	public MenuItem[] getItems() {
		return items;
	}
	public void setItems(MenuItem[] items) {
		this.items = items;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
