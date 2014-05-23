/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.newcapec.function.digitalcampus.common.exception.CapecException;
import cn.newcapec.function.digitalcampus.common.model.SoftModel;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.Assert;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.EditStateEnum;
import cn.newcapec.function.digitalcampus.jwxt.common.utils.PropertyUtils;

/**
 * @author Administrator
 *
 */
/** 
 * <p>Title: AbstractDaoSupport</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-24
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public abstract class AbstractDaoSupport extends HibernateDaoSupport implements SoftDao {
	public Log log = LogFactory.getLog(this.getClass());
	
	
	protected void applyQueryParameters(Query query,Object[] values){
		if(values!=null && values.length>0){
			for(int i=0; i<values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
	}
	
	protected void applyQueryNamedParameters(Query query, String[] params, Object[] values){
    	if(null == params || null == values){
    		return;
    	}
    	Assert.isTrue(params.length==values.length,"传参个数不对应");
		for (int i = 0; i < params.length; i++) {
			String paramName = params[i];
			Object value = values[i];
			if (value instanceof Collection) {
				query.setParameterList(paramName, (Collection) value);
			}
			else if (value instanceof Object[]) {
				query.setParameterList(paramName, (Object[]) value);
			}
			else {
				query.setParameter(paramName, value);
			}
		}
	}
	protected <T extends SoftModel> List converList2Model(Class<T> clazz,List<Map> list){
		if(clazz == null){
			return list;
		}
		List<T> relst = new ArrayList<T>(list.size());
		if(list == null || list.isEmpty()){
			return relst;
		}
		try {
			for(Map<String,Object> map : list){
				T t = clazz.newInstance();
				PropertyUtils.setPropertyType(t, map);
				relst.add(t);
			}
		} catch (CapecException e) {
			throw e;
		} catch (Exception e) {
			log.debug(e.getMessage());
		}finally{
			return relst;
		}
		
	}
	
	
	protected Object doMethod(EditStateEnum state,DaoMethodTemplate template){
		Object r = null;
		if(EditStateEnum.RIGHT_NOW_YES.equals(state)){
			super.getHibernateTemplate().clear();
			r =template.rollbockDaoMethod();
			super.getHibernateTemplate().flush();
		}else{
			r = template.rollbockDaoMethod();
		}
		return r;
	}
	
	
}

