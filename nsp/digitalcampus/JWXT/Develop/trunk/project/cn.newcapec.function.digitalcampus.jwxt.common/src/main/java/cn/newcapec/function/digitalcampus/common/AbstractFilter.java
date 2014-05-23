/*
 * 该类功能及其特点的描述（例如：该类是用来……）
 *
 * @see（与该类相关联的类）：(XXX.java)
 */
/**
 * 
 */
package cn.newcapec.function.digitalcampus.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 */ 
/** 
 * <p>Title: AbstractFilter</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 郑州新开普电子股份有限公司 2013</p>  
 * @author 杨航(Sntey)
 * @version
 * @date 创建日期：2013-7-31
 * 修改日期：
 * 修改人：
 * 复审人：
 */
public abstract class AbstractFilter implements Filter {
	protected final Log log=LogFactory.getLog(getClass());
	
	protected FilterConfig filterConfig;
	@Override
	public void destroy() {
		log.info("filter destroy "+getClass().getName());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
	}

}

