package net.foreworld.java.mail.mail_mailfilee;

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
public class Mail_mailfileeDAOImpl implements IDao {

	private static final Mail_mailfileeDAOImpl _instance = new Mail_mailfileeDAOImpl();

	private Mail_mailfileeDAOImpl() {
	}

	public static Mail_mailfileeDAOImpl getInstance() {
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_mailfilee.deleteById", __mail_mailfilee);
	}

	public int delete(Object $object, ConnSession $session) throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;

		return DaoFactory.getInstance().getSqlMap().delete(
				"mail_mailfilee.delete", __mail_mailfilee);
	}

	public boolean deletes(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.deleteById($list.get(__i), $session);
		}
		return true;
	}

	public int insert(Object $object, ConnSession $session) throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap().insert("mail_mailfilee.insert", __mail_mailfilee).toString());
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
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_mailfilee.select", __mail_mailfilee);
	}

	public List select2(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return DaoFactory.getInstance().getSqlMap().queryForList(
				"mail_mailfilee.select2", __mail_mailfilee);
	}

	public Object selectById(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return DaoFactory.getInstance().getSqlMap().queryForObject(
				"mail_mailfilee.selectById", __mail_mailfilee, $session);
	}

	public int selectCount(Object $object, ConnSession $session)
			throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return Integer.parseInt(DaoFactory.getInstance().getSqlMap()
				.queryForObject("mail_mailfilee.selectCount", __mail_mailfilee,
						$session).toString());
	}

	public int update(Object $object, ConnSession $session) throws SQLException {
		Mail_mailfilee __mail_mailfilee = (Mail_mailfilee) $object;
		return DaoFactory.getInstance().getSqlMap().update(
				"mail_mailfilee.update", __mail_mailfilee);
	}

	public boolean updates(List $list, ConnSession $session)
			throws SQLException {
		for (int __i = 0, __j = ($list == null ? 0 : $list.size()); __i < __j; __i++) {
			this.update($list.get(__i), $session);
		}
		return true;
	}




}