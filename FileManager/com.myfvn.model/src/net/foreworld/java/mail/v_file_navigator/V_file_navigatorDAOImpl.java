package net.foreworld.java.mail.v_file_navigator;

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
public class V_file_navigatorDAOImpl implements IDao {

	private static final V_file_navigatorDAOImpl _instance = new V_file_navigatorDAOImpl();

	private V_file_navigatorDAOImpl() {
	}

	public static V_file_navigatorDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"v_file_navigator.deleteById", __v_file_navigator);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"v_file_navigator.delete", __v_file_navigator);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("v_file_navigator.insert", __v_file_navigator).toString());
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
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"v_file_navigator.select", __v_file_navigator);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"v_file_navigator.select2", __v_file_navigator);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"v_file_navigator.selectById", __v_file_navigator, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("v_file_navigator.selectCount", __v_file_navigator,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		V_file_navigator __v_file_navigator = (V_file_navigator) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"v_file_navigator.update", __v_file_navigator);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}