package net.foreworld.java.sysmanage.sysmanage_codetype;

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
public class Sysmanage_codetypeDAOImpl implements IDao {

	private static final Sysmanage_codetypeDAOImpl _instance = new Sysmanage_codetypeDAOImpl();

	private Sysmanage_codetypeDAOImpl() {
	}

	public static Sysmanage_codetypeDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_codetype.deleteById", __sysmanage_codetype);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_codetype.delete", __sysmanage_codetype);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("sysmanage_codetype.insert", __sysmanage_codetype).toString());
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
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_codetype.select", __sysmanage_codetype);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_codetype.select2", __sysmanage_codetype);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"sysmanage_codetype.selectById", __sysmanage_codetype, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("sysmanage_codetype.selectCount", __sysmanage_codetype,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_codetype __sysmanage_codetype = (Sysmanage_codetype) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"sysmanage_codetype.update", __sysmanage_codetype);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}