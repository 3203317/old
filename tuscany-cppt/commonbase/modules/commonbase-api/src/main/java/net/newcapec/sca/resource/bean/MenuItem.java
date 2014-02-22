package net.newcapec.sca.resource.bean;

public class MenuItem {
	private String id;//节点id 标示符名称不可改
	private String name;//节点名称 标示符名称不可改
	private String type;//节点类型--默认为menu 标示符名称不可改
	private String parent;//父id 标示符名称不可改
	private String href;//url


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
