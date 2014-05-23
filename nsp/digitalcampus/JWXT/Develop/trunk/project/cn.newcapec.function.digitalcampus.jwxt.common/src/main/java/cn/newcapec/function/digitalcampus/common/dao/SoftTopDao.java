/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common.dao;

import java.util.List;

import cn.newcapec.function.digitalcampus.common.SoftGod;
import cn.newcapec.function.digitalcampus.common.model.SoftModel;
import cn.newcapec.function.digitalcampus.common.model.SoftModel;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.Page;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.EditStateEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PageEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.QlEnum;

/**
 * @author Administrator
 *
 */
/** 
 * <p>Title: SoftTopDao</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-24
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public interface SoftTopDao extends SoftGod {


	<T extends SoftModel>String save(T entity ,EditStateEnum state);
	
	<T extends SoftModel>String update(T entity ,EditStateEnum state);
	
	<T extends SoftModel> String saveOrUpdateEntity(T entity,EditStateEnum state);
	
	<T extends SoftModel> void saveOrUpdateEntity(List<T> entities ,EditStateEnum state);
	
	<T extends SoftModel>void remove(T entity ,EditStateEnum state);
	
	<T extends SoftModel>Integer remove4Ids(Class<T> clazz,EditStateEnum state, String ids);
	
	<T extends SoftModel>T get(Class<T> clazz,String id);
	
	<T extends SoftModel>T get(Class<T> clazz,String id,String[] fields);
	
	List getByProperty(String qlstring,Object[] values,QlEnum qltype ,Class returnType);
	
	List getByNameParam(String qlstring,String[] params,Object[] values,QlEnum qltype ,Class returnType);
	
	/****
	 * 
	 * @Title: queryByProperty
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param qlstring 传入的sql或hql
	 * @param values 应放入的值
	 * @param type 是sql还是hql
	 * @param needPage 是否要分页
	 * @param pageModel 要返回的分页对象
	 * @param returnType 要返回的分页对象中LIST放值的类型
	 * @return    设定文件
	 * @return CommonPage 返回类型 返回格式
	 * @throws
	 * @author 杨航(Sntey)
	 * @date 创建日期：2013-7-18
	 * 修改日期：
	 * 修改人：
	 * 复审人：
	 */
	Page queryByNameParam(String qlstring,String[] params,Object[] values,QlEnum type,PageEnum needPage,Page pageModel,Class returnType);

	Integer getTotalCountByNameParam(String qlstring,String[] params,Object[] values,QlEnum type);
	
	Integer executeQl(String qlstring,String[] params,Object[] values,QlEnum type);
	
	<T extends SoftModel>boolean isUnique(T entity,String[] fields);
	
	<T extends SoftModel>boolean isUniqueQl(T entity,String tableName,String[] fields);
	
}



