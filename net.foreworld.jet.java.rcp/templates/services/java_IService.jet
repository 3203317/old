package net.foreworld.java.services;

import java.util.List;

import net.foreworld.java.domain.ConnSession;
import net.foreworld.java.exceptions.ServiceException;

/**
 * 
 * @author 	huangxin
 * @email  	huangxin@foreworld.net
 * @qq		3203317
 * @generated
 */
public abstract interface IService{

	public abstract List select(Object $object, ConnSession $session) throws ServiceException;

	public abstract List select2(Object $object, ConnSession $session) throws ServiceException;
	
	public abstract int selectCount(Object $object, ConnSession $session) throws ServiceException;

	public abstract Object selectById(Object $object, ConnSession $session) throws ServiceException;

	public abstract Object insert(Object $object, ConnSession $session) throws ServiceException;

	public abstract int update(Object $object, ConnSession $session) throws ServiceException;

	public abstract int delete(Object $object, ConnSession $session) throws ServiceException;
	
	public abstract int deleteById(Object $object, ConnSession $session) throws ServiceException;

	public abstract boolean inserts(List $list, ConnSession $session) throws ServiceException;

	public abstract boolean updates(List $list, ConnSession $session) throws ServiceException;

	public abstract boolean deletes(List $list, ConnSession $session) throws ServiceException;
}