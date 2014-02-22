package net.foreworld.rss2.utils;

import java.util.ArrayList;


/**
 * 树对象，用于创建树状视图
 * @author 黄鑫
 * @Nov 13, 2007 8:32:05 PM
 */
public class TreeObject {
	public TreeObject(){
		children = new ArrayList(); 
	}
	
	public TreeObject(Object obj){
		this.obj = obj;
		children = new ArrayList();
	}
	
	private TreeObject treeObject,parent;
	private ArrayList children;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void addChildren(TreeObject o){
		this.children.add(o);
		o.setParent(this);
	}
	
	public void removeChildren(TreeObject o){
		this.children.remove(o);
		o.setParent(null);
	}
	
	private void setParent(TreeObject o){
		this.treeObject = o;
	}

	public boolean hasChildren() {
		return this.children.size()>0;
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) this.children.toArray(new TreeObject[this.children.size()]);
	}

	public TreeObject getParent() {
		return parent;
	}
	
}
