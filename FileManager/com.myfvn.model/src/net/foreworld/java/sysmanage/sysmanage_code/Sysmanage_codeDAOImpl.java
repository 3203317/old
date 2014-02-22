package net.foreworld.java.sysmanage.sysmanage_code;

import java.sql.SQLException;
import java.util.List;

import net.foreworld.java.daos.DaoFactory;
import net.foreworld.java.daos.IDao;
import net.foreworld.java.domain.ConnSession;


/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * @generated
 */
public class Sysmanage_codeDAOImpl implements IDao {

	private static final Sysmanage_codeDAOImpl _instance = new Sysmanage_codeDAOImpl();

	private Sysmanage_codeDAOImpl() {
	}

	public static Sysmanage_codeDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_code.deleteById", __sysmanage_code);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_code.delete", __sysmanage_code);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("sysmanage_code.insert", __sysmanage_code).toString());
	}

	public boolean inserts(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.insert($list.get(__i), $session);
		}
		return true;
	}

	public List select(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_code.select", __sysmanage_code);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_code.select2", __sysmanage_code);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"sysmanage_code.selectById", __sysmanage_code, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("sysmanage_code.selectCount", __sysmanage_code,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_code __sysmanage_code = (Sysmanage_code) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"sysmanage_code.update", __sysmanage_code);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}