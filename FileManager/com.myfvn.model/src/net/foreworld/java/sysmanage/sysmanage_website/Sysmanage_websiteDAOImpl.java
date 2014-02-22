package net.foreworld.java.sysmanage.sysmanage_website;

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
public class Sysmanage_websiteDAOImpl implements IDao {

	private static final Sysmanage_websiteDAOImpl _instance = new Sysmanage_websiteDAOImpl();

	private Sysmanage_websiteDAOImpl() {
	}

	public static Sysmanage_websiteDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_website.deleteById", __sysmanage_website);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"sysmanage_website.delete", __sysmanage_website);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("sysmanage_website.insert", __sysmanage_website).toString());
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
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_website.select", __sysmanage_website);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"sysmanage_website.select2", __sysmanage_website);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"sysmanage_website.selectById", __sysmanage_website, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("sysmanage_website.selectCount", __sysmanage_website,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Sysmanage_website __sysmanage_website = (Sysmanage_website) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"sysmanage_website.update", __sysmanage_website);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}