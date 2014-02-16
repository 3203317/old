package net.htjs.editor.designmap.models;

import java.util.ArrayList;
import java.util.List;

public class ContentsModel{
	private List children = new ArrayList();

	public void addChild(Object child){
		this.children.add(child);
	}
	
	public List getChildren(){
		return this.children;
	}
}
