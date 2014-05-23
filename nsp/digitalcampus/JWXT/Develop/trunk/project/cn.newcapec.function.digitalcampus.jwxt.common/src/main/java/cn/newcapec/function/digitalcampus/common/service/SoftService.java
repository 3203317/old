/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common.service;

import java.util.List;

import cn.newcapec.function.digitalcampus.common.dao.SoftDao;
import cn.newcapec.function.digitalcampus.common.model.SoftEntityModel;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.Page;

/**
 * @author Administrator
 *
 */
/** 
 * <p>Title: SoftService</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-24
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public interface SoftService extends SoftTopService {
	
	SoftDao getDao();
	
	<T extends SoftEntityModel> String saveEntity(T entity,String[] withoutFields);
	
	<T extends SoftEntityModel> String updateEntity(T entity,String[] withoutFields);
	
	<T extends SoftEntityModel> String saveOrUpdateEntity(T entity,String[] withoutFields);
	
	<T extends SoftEntityModel> void saveOrUpdateAllEntity(List<T> entities);
	
	<T extends SoftEntityModel> void removeEntity(T entity);
	
	<T extends SoftEntityModel> Integer removeEntity(Class<T> clazz,String ids);
	
	Page query();

}

