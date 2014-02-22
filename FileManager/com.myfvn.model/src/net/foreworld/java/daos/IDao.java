package net.foreworld.java.daos;

import java.sql.SQLException;
import java.util.List;

import net.foreworld.java.domain.ConnSession;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public abstract interface IDao{

	public abstract List select(Object $object, ConnSession $session)
			throws SQLException;

	public abstract List select2(Object $object, ConnSession $session)
			throws SQLException;

	public abstract int selectCount(Object $object, ConnSession $session)
			throws SQLException;

	public abstract Object selectById(Object $object, ConnSession $session)
			throws SQLException;

	public abstract int insert(Object $object, ConnSession $session)
			throws SQLException;

	public abstract int update(Object $object, ConnSession $session)
			throws SQLException;

	public abstract int delete(Object $object, ConnSession $session)
			throws SQLException;

	public abstract int deleteById(Object $object, ConnSession $session)
			throws SQLException;

	public abstract boolean inserts(List $list, ConnSession $session)
			throws SQLException;

	public abstract boolean updates(List $list, ConnSession $session)
			throws SQLException;

	public abstract boolean deletes(List $list, ConnSession $session)
			throws SQLException;

}