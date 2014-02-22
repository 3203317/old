package net.foreworld.java.mail.mail_mailaccountt;

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
public class Mail_mailaccounttDAOImpl implements IDao {

	private static final Mail_mailaccounttDAOImpl _instance = new Mail_mailaccounttDAOImpl();

	private Mail_mailaccounttDAOImpl() {
	}

	public static Mail_mailaccounttDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_mailaccountt.deleteById", __mail_mailaccountt);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_mailaccountt.delete", __mail_mailaccountt);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("mail_mailaccountt.insert", __mail_mailaccountt).toString());
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
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_mailaccountt.select", __mail_mailaccountt);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_mailaccountt.select2", __mail_mailaccountt);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"mail_mailaccountt.selectById", __mail_mailaccountt, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("mail_mailaccountt.selectCount", __mail_mailaccountt,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"mail_mailaccountt.update", __mail_mailaccountt);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}