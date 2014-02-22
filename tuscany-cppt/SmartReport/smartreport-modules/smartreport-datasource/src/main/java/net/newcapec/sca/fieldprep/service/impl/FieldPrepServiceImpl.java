package net.newcapec.sca.fieldprep.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.newcapec.sca.datasource.service.impl.ConnDB;
import net.newcapec.sca.dojo.DojoListData;
import net.newcapec.sca.dojo.DojoListParam;
import net.newcapec.sca.fieldprep.FieldPrep;
import net.newcapec.sca.fieldprep.das.FieldPrepDAS;
import net.newcapec.sca.fieldprep.service.FieldPrepService;
import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.result.ResultMsg;
import net.newcapec.sca.session.Session;
import net.newcapec.sca.session.service.SessionService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

/**
 *
 * @author huangxin
 *
 */
public class FieldPrepServiceImpl implements FieldPrepService {

	private static final Logger _log = Logger.getLogger(FieldPrepServiceImpl.class);

	private ConnDB _db = new ConnDB();

	private SessionService sessionService;

	@Reference(name = "sessionService", required = true)
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	private FieldPrepDAS fieldPrepDAS;

	@Reference(name = "fieldPrepDAS", required = true)
	public void setFieldPrepDAS(FieldPrepDAS fieldPrepDAS) {
		this.fieldPrepDAS = fieldPrepDAS;
	}

	public FieldPrep getFieldPrepById(String sessionId, Integer id) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		FieldPrep __fp = this.fieldPrepDAS.getFieldPrepById(id);
		return __fp;
	}

	public List<FieldPrep> findFieldPrepList(String sessionId, Integer resourceId, List<FilterParam> filter, Integer begin, Integer limit) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<FieldPrep> __list = this.fieldPrepDAS.findFieldPrepList(1, 1, filter, begin, limit);
		return __list;
	}

	public DojoListData findFieldPrepDojoList(DojoListParam param) {
		Session session = sessionService.getSession(param.getSessionId());
		if (!this.isSessionVaild(session)) {
			return null;
		}
		List<FieldPrep> __list = this.fieldPrepDAS.findFieldPrepList(1, 1, param.getFilter(), param.getBegin(), param.getLimit());

		int __count = this.fieldPrepDAS.getFieldPrepListRowCount(param.getSessionId(), param.getResourceId(), param.getFilter());

		DojoListData __dld = new DojoListData();
		__dld.setIdentifier("code");
		__dld.setItems(__list.toArray());
		__dld.setLabel("name");
		__dld.setNumRows(__count);
		return __dld;
	}

	public FieldPrep insertFieldPrep(String sessionId, FieldPrep fieldPrep) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		Boolean __b = this.fieldPrepDAS.insertFieldPrep(fieldPrep);
		if (!__b) {
			ResultMsg __rm = new ResultMsg();
			__rm.setErrorId(205);
			__rm.setErrorMsg("添加新字段失败");
			fieldPrep.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
		}
		return fieldPrep;
	}

	public FieldPrep updateFieldPrep(String sessionId, FieldPrep fieldPrep) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.fieldPrepDAS.updateFieldPrep(fieldPrep);
		return fieldPrep;
	}

	public FieldPrep delFieldPrepById(String sessionId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public FieldPrep delFieldPrepByIds(String sessionId, String ids) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		// 验证 sessionId 是否合法

		_log.info("参数：" + ids);

		FieldPrep __fp = new FieldPrep();
		ResultMsg __rm = new ResultMsg();

		if ("".equals(ids)) {
			__rm.setErrorId(205);
			__rm.setErrorMsg("参数 ids 不能为空");
			__fp.setResultMsg(__rm);
			_log.info(__rm.getErrorMsg());
			return __fp;
		}

		String[] __idsArr = ids.split(",");

		Integer[] __ids = new Integer[__idsArr.length];

		for (int __i_3 = 0, __j_3 = __idsArr.length; __i_3 < __j_3; __i_3++) {
			__ids[__i_3] = Integer.parseInt(__idsArr[__i_3]);
		}

		boolean __delResult = this.fieldPrepDAS.delFieldPrepByIds(__ids);

		if (!__delResult) {
			__rm.setErrorId(203);
			__rm.setErrorMsg("删除 FieldPrep 失败");
			__fp.setResultMsg(__rm);
		}

		return __fp;
	}

	public Integer getFieldPrepListRowCount(String sessionId, Integer resourceId, List<FilterParam> filter) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		int __count = this.fieldPrepDAS.getFieldPrepListRowCount(sessionId, resourceId, filter);
		return __count;
	}

	public List<FieldPrep> findFieldListByDscode(String sessionId, Integer ds_code) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		return this.fieldPrepDAS.findFieldByDscode(ds_code);
	}

	public List<FieldPrep> findFieldPrepListByDsCode(String sessionId, Integer resourceId, String ds_code, String ds_type, String ds_sql, String user, String password, String ip, String port, String server) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		FilterParam fp = new FilterParam();
		fp.setField("ds_code");
		fp.setLogical("=");
		fp.setRelation("and");
		fp.setValue(ds_code);

		List<FilterParam> filter = new ArrayList<FilterParam>();
		filter.add(fp);

		List<FieldPrep> __list = this.fieldPrepDAS.findFieldPrepList(1, 1, filter, 0, 1000);

		if (__list != null && __list.size() > 0) {
			return __list;
		}

		if ("sql".equals(ds_type)) {
			List<String[]> __sqlist = _db.getDbTableFieldsByUser(user, password, ip, port, server, ds_sql);

			if (__sqlist != null) {
				for (int i = 0, j = __sqlist.size(); i < j; i++) {
					String[] field = __sqlist.get(i);
					String name = field[0];

					FieldPrep __fp = new FieldPrep();
					__fp.setDs_code(Integer.parseInt(ds_code));
					__fp.setName(name);
					__fp.setAlias(name);
					__fp.setType(field[1]);
					__fp.setInput_type("input");
					__fp.setRegexp("");
					__fp.setMemo("");

					this.insertFieldPrep(sessionId, __fp);
				}
			}

		} else if ("proc".equals(ds_type)) {
			List<String[]> __sqlist = _db.getDbProcFieldsByUser(user, password, ip, port, server, ds_sql);

			if (__sqlist != null) {
				for (int i = 0, j = __sqlist.size(); i < j; i++) {
					String[] field = __sqlist.get(i);
					String name = field[0];

					FieldPrep __fp = new FieldPrep();
					__fp.setDs_code(Integer.parseInt(ds_code));
					__fp.setName(name);
					__fp.setAlias(name);
					__fp.setType(field[1]);
					__fp.setInput_type("input");
					__fp.setRegexp("");
					__fp.setMemo("");

					this.insertFieldPrep(sessionId, __fp);
				}
			}

		} else if ("sca".equals(ds_type)) {

		}

		__list = this.fieldPrepDAS.findFieldPrepList(1, 1, filter, 0, 1000);
		return __list;
	}

	public FieldPrep updateFieldPreps(String sessionId, Integer ds_code, List<FieldPrep> fieldPreps) {
		Session session = sessionService.getSession(sessionId);
		if (!this.isSessionVaild(session)) {
			return null;
		}
		this.fieldPrepDAS.delFieldPrepByDsId(ds_code);
		for (int __i_3 = 0, __j_3 = fieldPreps.size(); __i_3 < __j_3; __i_3++) {
			FieldPrep __fp_4 = fieldPreps.get(__i_3);
			this.fieldPrepDAS.insertFieldPrep(__fp_4);
		}
		FieldPrep fp = new FieldPrep();
		return fp;
	}

	/**
	 * true 有效， false 失效
	 *
	 * @param session
	 * @return
	 */
	protected boolean isSessionVaild(Session session) {
		boolean sessionVaild = true;
		try {
			if (null == session.getId() && session.getState() == 0) {
				sessionVaild = false;
			}
		} catch (Exception e) {
			sessionVaild = false;
			_log.debug(e.getMessage());
		}
		return sessionVaild;
	}
}
