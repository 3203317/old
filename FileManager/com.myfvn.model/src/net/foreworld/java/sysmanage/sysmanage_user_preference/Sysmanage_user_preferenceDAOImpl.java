package net.foreworld.java.sysmanage.sysmanage_user_preference;

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
public class Sysmanage_user_preferenceDAOImpl implements IDao {

	private static final Sysmanage_user_preferenceDAOImpl _instance = new Sysmanage_user_preferenceDAOImpl();

	private Sysmanage_user_preferenceDAOImpl() {
	}

	public static Sysmanage_user_preferenceDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_user_preference.deleteById", __sysmanage_user_preference);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_user_preference.delete", __sysmanage_user_preference);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("sysmanage_user_preference.insert", __sysmanage_user_preference).toString());
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
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_user_preference.select", __sysmanage_user_preference);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_user_preference.select2", __sysmanage_user_preference);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"sysmanage_user_preference.selectById", __sysmanage_user_preference, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("sysmanage_user_preference.selectCount", __sysmanage_user_preference,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_user_preference __sysmanage_user_preference = (Sysmanage_user_preference) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"sysmanage_user_preference.update", __sysmanage_user_preference);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}