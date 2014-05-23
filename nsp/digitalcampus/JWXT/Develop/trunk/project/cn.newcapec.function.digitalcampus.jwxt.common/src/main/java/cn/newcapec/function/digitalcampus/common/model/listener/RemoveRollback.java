/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common.model.listener;

import cn.newcapec.function.digitalcampus.common.SoftUtils;
import cn.newcapec.function.digitalcampus.common.exception.CapecException;

/**
 * @author Administrator
 *
 */
/** 
 * <p>Title: RemoveRollback</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-4
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public interface RemoveRollback extends SoftUtils {

	
	void doRemove(String ids) throws CapecException;
}

