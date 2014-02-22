package net.foreworld.java.mail.mail_maill;

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
public class Mail_maillDAOImpl implements IDao {

	private static final Mail_maillDAOImpl _instance = new Mail_maillDAOImpl();

	private Mail_maillDAOImpl() {
	}

	public static Mail_maillDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_maill.deleteById", __mail_maill);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_maill.delete", __mail_maill);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("mail_maill.insert", __mail_maill).toString());
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
		Mail_maill __mail_maill = (Mail_maill) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_maill.select", __mail_maill);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_maill.select2", __mail_maill);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"mail_maill.selectById", __mail_maill, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("mail_maill.selectCount", __mail_maill,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Mail_maill __mail_maill = (Mail_maill) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"mail_maill.update", __mail_maill);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}