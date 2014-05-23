package cn.newcapec.function.digitalcampus.common.model.listener;

import java.util.Date;

/****
 * 
 * @author Sntey
 * 实现该接口代表MODEL对象中需要自动添加创建人、记录所属单位、维护时间、创建时间
 *
 */
public interface ModelPlusListener extends ModelListener {

	
	/*记录创建人**/
	String getCjr();
	void setCjr(String cjr);
	
	/****
	 * 
	 * @return 记录所属单位
	 */
	String getJlssdw();
	void setJlssdw(String jlssdw);
	
	/****
	 * 
	 * @return 维护时间
	 */
	Date getWhsj();
	void setWhsj(Date whsj);
	
	/****
	 * 
	 * @return 创建时间
	 */
	Date getCjsj();
	void setCjsj(Date cjsj);
}
