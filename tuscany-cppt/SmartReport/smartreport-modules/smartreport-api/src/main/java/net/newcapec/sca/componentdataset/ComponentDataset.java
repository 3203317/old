package net.newcapec.sca.componentdataset;

import net.newcapec.sca.result.ResultMsg;

/**
 * <p>Title: 业务实体类 </p>
 * <p>Description:控件绑定数据集实体类</p>
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2013</p>
 * @author  普秀霞
 * @version
 * @date 创建日期：2013-01-08
 * 修改日期：
 * 修改人：
 * 复审人：
 */

public class ComponentDataset {

	private  Integer  code ;    //编号
	private  Integer  domain_code ; //域编号
	private  Integer  unit_code ; //客户单位编号
	private  String   component_type ;//控件类型
	private  String   component_datasource ;//控件数据源code
	private  String   datasource_name ;//控件数据源名称
	private  String   dataset_name ;//数据集名称
	private  String   text_field ;//Text字段
	private  String   value_field ;//Value字段
	private  String   parent_field ;//父级字段
	private  String   top_default ;//顶级默认值
	private ResultMsg resultMsg ; //封装消息结果

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getDomain_code() {
		return domain_code;
	}
	public void setDomain_code(Integer domain_code) {
		this.domain_code = domain_code;
	}
	public Integer getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(Integer unit_code) {
		this.unit_code = unit_code;
	}
	public String getComponent_type() {
		return component_type;
	}
	public void setComponent_type(String component_type) {
		this.component_type = component_type;
	}
	public String getComponent_datasource() {
		return component_datasource;
	}
	public void setComponent_datasource(String component_datasource) {
		this.component_datasource = component_datasource;
	}
	public String getDataset_name() {
		return dataset_name;
	}
	public void setDataset_name(String dataset_name) {
		this.dataset_name = dataset_name;
	}
	public String getText_field() {
		return text_field;
	}
	public void setText_field(String text_field) {
		this.text_field = text_field;
	}
	public String getValue_field() {
		return value_field;
	}
	public void setValue_field(String value_field) {
		this.value_field = value_field;
	}
	public String getParent_field() {
		return parent_field;
	}
	public void setParent_field(String parent_field) {
		this.parent_field = parent_field;
	}
	public String getTop_default() {
		return top_default;
	}
	public void setTop_default(String top_default) {
		this.top_default = top_default;
	}
	public ResultMsg getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getDatasource_name() {
		return datasource_name;
	}
	public void setDatasource_name(String datasource_name) {
		this.datasource_name = datasource_name;
	}








}
