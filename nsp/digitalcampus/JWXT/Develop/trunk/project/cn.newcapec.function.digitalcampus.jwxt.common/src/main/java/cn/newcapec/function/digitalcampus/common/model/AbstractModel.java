package cn.newcapec.function.digitalcampus.common.model;

import java.util.ArrayList;
import java.util.List;

import cn.newcapec.function.digitalcampus.jwxt.common.utils.ModelUtil;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PropertyUtils;


public abstract class AbstractModel implements SoftEntityModel {

	
	private String ids;
	
	@Override
	public String getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public boolean isSave() {
		
		return ModelUtil.isNew(this);
	}

	/****
	 * 清空实体类中的除了基本类型之外的数据。
	 */
	@Override
	public void empty(){
		PropertyUtils.empty(this);
	}

	
	
}
