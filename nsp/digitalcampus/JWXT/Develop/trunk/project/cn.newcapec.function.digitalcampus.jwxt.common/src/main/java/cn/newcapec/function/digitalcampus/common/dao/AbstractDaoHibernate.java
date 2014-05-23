/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.newcapec.function.digitalcampus.common.exception.CapecException;
import cn.newcapec.function.digitalcampus.common.model.SoftModel;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.Assert;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.Page;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.EditStateEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.HqlUtils;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.ListPage;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.NumberUtils;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PageEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PropertyUtils;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.QlEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.StringUtils;

/**
 * @author Administrator
 *
 */
/** 
 * <p>Title: 该实现秉承一个原则。百川汇海。一切方法都是由一个源方法演变而来 2013.07.19</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-24
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public abstract class AbstractDaoHibernate extends AbstractDaoSupport {
	
	@Override
	public <T extends SoftModel> T get(Class<T> clazz, String id) {
		
		return this.get(clazz, id, null);
	}

	@Override
	public <T extends SoftModel> T get(Class<T> clazz, String id,
			String[] fields) {
		
		if(StringUtils.notText(id)){
			return null;
		}
		if(fields == null || fields.length<=0){
			return (T) super.getHibernateTemplate().get(clazz, id);
		}
		
		StringBuffer qlstring = new StringBuffer();
		qlstring.append(" select  ");
		qlstring.append(HqlUtils.arrayToString(fields));
		qlstring.append(" from "+clazz.getName());
		qlstring.append(" where id=:id");
		List<T> tl = this.getByNameParamHql(qlstring.toString(), new String[]{"id"}, new Object[]{id},clazz);
		return tl==null || tl.isEmpty() ? null : tl.get(0);
	}

	@Override
	public List getByNameParam(String qlstring, String[] params,
			Object[] values, QlEnum qltype, Class returnType) {
		return this.queryByNameParam(qlstring, params, values, qltype, PageEnum.PAGE_NO, new ListPage(), returnType).getRecord();
	}

	@Override
	public List getByProperty(String qlstring, Object[] values, QlEnum qltype,
			Class returnType) {
		return this.queryByNameParam(qlstring, null, values, qltype, PageEnum.PAGE_NO, new ListPage(), returnType).getRecord();
	}

	@Override
	public Page queryByNameParam(final String qlstring,final String[] params,
			final Object[] values,final QlEnum type,final PageEnum needPage,final Page pageModel,final Class returnType) {
		Assert.isTrue(StringUtils.hasText(qlstring));
		List l = super.getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = null;
				if(type.equals(QlEnum.SQL)){
					query = session.createSQLQuery(qlstring);
				}else if(type.equals(QlEnum.HQL)){
					query = session.createQuery(qlstring);
				}
				if(returnType != null){
					if(Map.class.equals(returnType)){
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					}else{
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					}
				}
				if(PageEnum.PAGE_NEED.equals(needPage)){
					query.setFirstResult(pageModel.getFirst());
					query.setMaxResults(pageModel.getLength());
				}
				if(params == null){
					applyQueryParameters(query, values);
				}else{
					applyQueryNamedParameters(query, params, values);
				}
				return query.list();
			}
		});
		if(returnType == null){
			pageModel.setRecord(l);
		}else{
			if(returnType != null){
				if(Map.class.equals(returnType) || TreeMap.class.equals(returnType) || HashMap.class.equals(returnType)){
					pageModel.setRecord(l);
				}else{
					List l2 = this.converList2Model(returnType, l);
					pageModel.setRecord(l2);
				}
			}
		}
		if(PageEnum.PAGE_NEED.equals(needPage)){
			pageModel.setTotal(this.getTotalCountByNameParam(qlstring, params, values, type));
		}
		return pageModel;
	}

	
	@Override
	public Integer getTotalCountByNameParam(final String qlstring,final String[] params,
			final Object[] values,final QlEnum type) {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = null;
				if(type.equals(QlEnum.SQL)){
					query = session.createSQLQuery(qlstring);
				}else if(type.equals(QlEnum.HQL)){
					query = session.createQuery(qlstring);
				}
				if(params == null){
					applyQueryParameters(query, values);
				}else{
					applyQueryNamedParameters(query, params, values);
				}
	    		ScrollableResults scrollableResults = query.scroll(ScrollMode.SCROLL_SENSITIVE);
	            scrollableResults.last();
	            return (Integer)(scrollableResults.getRowNumber() + 1);
			}
		});
	}

	@Override
	public Integer executeQl(final String qlstring,final String[] params,final Object[] values,
			final QlEnum type) {
		return (Integer)super.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query= null;
				if(QlEnum.SQL.equals(type)){
					query = session.createSQLQuery(qlstring);
				}else if(QlEnum.HQL.equals(type)){
					query = session.createQuery(qlstring);
				}
				if(params == null){
					applyQueryParameters(query, values);
				}else{
					applyQueryNamedParameters(query, params, values);
				}
				return query.executeUpdate();
			}
		});
	}

	@Override
	public <T extends SoftModel> void remove(final T entity, EditStateEnum state) {
		this.doMethod(state, new DaoMethodTemplate() {
			@Override
			public Object rollbockDaoMethod() throws CapecException {
				getHibernateTemplate().delete(entity);
				return null;
			}
		});
	}
	@Override
	public <T extends SoftModel> Integer remove4Ids(final Class<T> clazz,
			EditStateEnum state,final String ids) {
		return (Integer)this.doMethod(state, new DaoMethodTemplate() {
			@Override
			public Object rollbockDaoMethod() throws CapecException {
				StringBuffer qlstring = new StringBuffer();
				qlstring.append(" delete from "+clazz.getName());
				qlstring.append(" where id in(:ids)");
				return executeQl(qlstring.toString(), new String[]{"ids"}, ids.split(StringUtils.Symbol.COMMA), QlEnum.HQL);
			}
		});
	}

	@Override
	public <T extends SoftModel> void saveOrUpdateEntity(final List<T> entities,
			EditStateEnum state) {
		this.doMethod(state, new DaoMethodTemplate() {
			@Override
			public Object rollbockDaoMethod() throws CapecException {
				getHibernateTemplate().saveOrUpdateAll(entities);
				return null;
			}
		});
	}
	
	@Override
	public <T extends SoftModel> String save(final T entity,EditStateEnum state) {
		return (String)this.doMethod(state, new DaoMethodTemplate() {
			@Override
			public Object rollbockDaoMethod() throws CapecException {
				return getHibernateTemplate().save(entity);
			}
		});
	}
	
	@Override
	public <T extends SoftModel> String update(final T entity, EditStateEnum state) {
		return (String)this.doMethod(state, new DaoMethodTemplate() {
			@Override
			public Object rollbockDaoMethod() throws CapecException {
				getHibernateTemplate().update(entity);
				return entity.getId();
			}
		});
	}
	
	@Override
	public <T extends SoftModel> String saveOrUpdateEntity(T entity,
			EditStateEnum state) {
		if(entity.isSave()){
			return this.save(entity, state);
		}else{
			return this.update(entity, state);
		}
	}

	@Override
	public Integer executeSQL(String qlstring, String[] params, Object[] values) {
		return this.executeQl(qlstring, params, values, QlEnum.SQL);
	}

	@Override
	public List getByNameParamSql(String qlstring, String[] params,
			Object[] values, Class returnType) {
		return this.getByNameParam(qlstring, params, values, QlEnum.SQL, returnType);
	}

	@Override
	public List getByPropertySql(String qlstring, Object[] values,
			Class returnType) {
		return this.getByProperty(qlstring, values, QlEnum.SQL, returnType);
	}

	@Override
	public Integer getTotalCountByNameParamSql(String qlstring,
			String[] params, Object[] values) {
		return this.getTotalCountByNameParam(qlstring, params, values, QlEnum.SQL);
	}

	@Override
	public Page queryByNameParamSql(String qlstring, String[] params,
			Object[] values, PageEnum needPage, Page pageModel,
			Class returnType) {
		return this.queryByNameParam(qlstring, params, values, QlEnum.SQL, needPage, pageModel, returnType);
	}

	@Override
	public List getByNameParamSql(String qlstring, String[] params,
			Object[] values) {
		return this.getByNameParamSql(qlstring, params, values, null);
	}

	@Override
	public List getByPropertySql(String qlstring, Object[] values) {
		return this.getByPropertySql(qlstring, values, null);
	}

	@Override
	public Page queryByNameParamSql(String qlstring, String[] params,
			Object[] values) {
		return this.queryByNameParamSql(qlstring, params, values, PageEnum.PAGE_NEED, new ListPage(), null);
	}

	
	
	
	
	@Override
	public List getByNameParamHql(String qlstring, String[] params,
			Object[] values, Class returnType) {
		return this.getByNameParam(qlstring, params, values, QlEnum.HQL, returnType);
	}

	@Override
	public List getByPropertyHql(String qlstring, Object[] values,
			Class returnType) {
		return this.getByProperty(qlstring, values, QlEnum.HQL, returnType);
	}

	@Override
	public List getByNameParamHql(String qlstring, String[] params,
			Object[] values) {
		return this.getByNameParamHql(qlstring, params, values,null);
	}

	@Override
	public List getByPropertyHql(String qlstring, Object[] values) {
		return this.getByPropertyHql(qlstring, values,null);
	}

	@Override
	public <T extends SoftModel> boolean isUnique(T entity,
			String[] fields) {
		return this.isUniqueQl(entity, null, fields);
	}

	@Override
	public <T extends SoftModel> boolean isUniqueQl(T entity,
			String tableName, String[] fields) {
		StringBuffer qlstring =  new StringBuffer();
		QlEnum qltype = null;
		if(StringUtils.hasText(tableName)){
			qlstring.append("select count(*) from "+tableName+" a where 1=1 ");
			qltype = QlEnum.SQL;
		}else{
			qlstring.append("select count(*) from "+entity.getClass().getName() + " a where 1=1 ");
			qltype = QlEnum.HQL;
		}
		List<Object> values = new ArrayList<Object>(fields.length);
		for(String name : fields){
			if(StringUtils.notText(name)){
				continue;
			}
			name = name.trim();
			Object propertyValue = PropertyUtils.getProperty(entity, name);
			qlstring.append(" and a."+name+"=?");
			values.add(propertyValue);
		}
		if(StringUtils.hasText(entity.getId())){
			qlstring.append(" and a.id<>?");
			Object propertyValue = entity.getId();
			values.add(propertyValue);
		}
		List list = this.getByProperty(qlstring.toString(),values.toArray(), qltype, null);
		return NumberUtils.toInteger(list.get(0))<=0;
	}
	
	

}

