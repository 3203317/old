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

import cn.newcapec.function.digitalcampus.jwxt.common.utils.Page;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PageEnum;

/**
 * @author Administrator
 */
/** 该接口用于最新设计，目前正在设计中
 * 设计思路在第一代基础上做一些改变，对查询方法，所有以query开头的方法都支持分页查询，所有以get开头的方法都不支持分页查询
 *  2013.07.18 sntey
 * 
 * <p>Title: SoftTopDao</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-18
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public interface SoftDao extends SoftTopDao {

	List getByPropertySql(String qlstring,Object[] values,Class returnType);
	
	List getByNameParamSql(String qlstring,String[] params,Object[] values,Class returnType);
	
	Page queryByNameParamSql(String qlstring,String[] params,Object[] values,PageEnum needPage,Page pageModel,Class returnType);

	Integer getTotalCountByNameParamSql(String qlstring,String[] params,Object[] values);
	
	Integer executeSQL(String qlstring,String[] params,Object[] values);
	
	
	
	List getByPropertySql(String qlstring,Object[] values);
	
	List getByNameParamSql(String qlstring,String[] params,Object[] values);
	
	Page queryByNameParamSql(String qlstring,String[] params,Object[] values);

	
	
	List getByPropertyHql(String qlstring,Object[] values,Class returnType);
	
	List getByNameParamHql(String qlstring,String[] params,Object[] values,Class returnType);
	
	List getByPropertyHql(String qlstring,Object[] values);
	
	List getByNameParamHql(String qlstring,String[] params,Object[] values);
}




