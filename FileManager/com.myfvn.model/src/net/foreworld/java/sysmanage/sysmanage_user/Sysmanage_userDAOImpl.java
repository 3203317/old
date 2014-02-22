package net.foreworld.java.sysmanage.sysmanage_user;

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
public class Sysmanage_userDAOImpl implements IDao {

	private static final Sysmanage_userDAOImpl _instance = new Sysmanage_userDAOImpl();

	private Sysmanage_userDAOImpl() {
	}

	public static Sysmanage_userDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_user.deleteById", __sysmanage_user);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_user.delete", __sysmanage_user);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("sysmanage_user.insert", __sysmanage_user).toString());
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
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_user.select", __sysmanage_user);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_user.select2", __sysmanage_user);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"sysmanage_user.selectById", __sysmanage_user, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("sysmanage_user.selectCount", __sysmanage_user,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user __sysmanage_user = (Sysmanage_user) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"sysmanage_user.update", __sysmanage_user);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}