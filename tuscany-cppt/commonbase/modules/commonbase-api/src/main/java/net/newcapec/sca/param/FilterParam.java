package net.newcapec.sca.param;

/**
 *
 * @author huangxin
 *
 */
public class FilterParam {
	private String field; // 条件字段名称
	private String relation; // 关系运算符
	private String value; // 字段值
	private String logical; // 逻辑运算符
	private String type; //类型

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLogical() {
		return logical;
	}

	public void setLogical(String logical) {
		this.logical = logical;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
