package net.newcapec.sca.dojo;

import java.util.List;

import net.newcapec.sca.param.FilterParam;

/**
 *
 * @author huangxin
 *
 */
public class DojoListParam {

	private String sessionId;
	private Integer resourceId;
	private List<FilterParam> filter;
	private Integer begin;
	private Integer limit;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public List<FilterParam> getFilter() {
		return filter;
	}

	public void setFilter(List<FilterParam> filter) {
		this.filter = filter;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
