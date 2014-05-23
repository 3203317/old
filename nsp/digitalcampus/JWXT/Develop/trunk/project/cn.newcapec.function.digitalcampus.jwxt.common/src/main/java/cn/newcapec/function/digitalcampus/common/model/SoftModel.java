package cn.newcapec.function.digitalcampus.common.model;

import cn.newcapec.function.digitalcampus.common.SoftGod;

public interface SoftModel extends SoftGod {

	/****
	 * 
	 * @Title: empty
	 * @Description: TODO(清空对象中数据，除了ID)    设定文件
	 * @return void 返回类型 返回格式
	 * @throws
	 * @author 杨航(Sntey)
	 * @date 创建日期：2012-10-22
	 * 修改日期：
	 * 修改人：
	 * 复审人：
	 */
	void empty();
	
	/**如果是保存方法，则返回TRUE**/
	boolean isSave();
	
	
	void setId(String id);
	
	String getId();
	
}
