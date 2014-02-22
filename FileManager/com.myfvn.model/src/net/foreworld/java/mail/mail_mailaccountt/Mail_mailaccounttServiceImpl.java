package net.foreworld.java.mail.mail_mailaccountt;

import java.sql.SQLException;
import java.util.List;

import net.foreworld.java.daos.DaoFactory;
import net.foreworld.java.daos.IDao;
import net.foreworld.java.domain.ConnSession;
import net.foreworld.java.exceptions.ServiceException;
import net.foreworld.java.services.IService;
import ognl.Ognl;


/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * @generated
 */
public class Mail_mailaccounttServiceImpl implements IService {
	
	private static final Mail_mailaccounttServiceImpl _instance = new Mail_mailaccounttServiceImpl();
	
	private Mail_mailaccounttServiceImpl() {}
	
	public static Mail_mailaccounttServiceImpl getInstance(){
		return _instance;
	}

	public int deleteById(Object $object, ConnSession $session)
			throws ServiceException {
		
		int __result = 0;
		
		try{
			
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			
			__result = DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).deleteById(__mail_mailaccountt, $session);
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return __result;
	}

	public int delete(Object $object, ConnSession $session)
			throws ServiceException {
		
		int __result = 0;
		
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			
			DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).delete(__mail_mailaccountt, $session);
			
			String __detailTablesName = __mail_mailaccountt.getDetailTablesName();
			
			if(__detailTablesName != null && !"".equals(__detailTablesName.trim())){
				
				String[] ___detailTablesNameStrs =  __detailTablesName.trim().split(",");
				
				for(int ___i=0,___j=___detailTablesNameStrs.length; ___i<___j; ___i++){
					
					Object ____object = Class.forName(___detailTablesNameStrs[___i]).newInstance();
					
					Ognl.setValue("tab_mail_mailaccountt_id", ____object, __mail_mailaccountt.getId());
					
					IDao ____idao = (IDao)DaoFactory.getInstance().getDao(___detailTablesNameStrs[___i] +"DAO");
					
					____idao.delete(____object, $session);
				}
			}
			__result = 1;
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException $ex) {
				$ex.printStackTrace();
			}
		}
		return __result;
	}

	public boolean deletes(List $list, ConnSession $session)
			throws ServiceException {
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			for(int __i=0, __j=($list == null ? 0 : $list.size()); __i<__j; __i++){
				DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).delete($list.get(__i), $session);
			}
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	public Object insert(Object $object, ConnSession $session)
			throws ServiceException {
		int __id = 0;
		
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
		
			Mail_mailaccountt ___mail_mailaccountt = (Mail_mailaccountt)$object;
			
			__id = DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).insert(___mail_mailaccountt, $session);
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();			
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException $ex) {
				$ex.printStackTrace();
			}
		}
		return __id;
	}

	public boolean inserts(List $list, ConnSession $session)
			throws ServiceException {
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			for(int __i=0, __j=($list == null ? 0 : $list.size()); __i<__j; __i++){
				Mail_mailaccountt ___mail_mailaccountt = (Mail_mailaccountt)$list.get(__i);
				int ___id = DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).insert(___mail_mailaccountt, $session);
			}
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException $ex) {
				$ex.printStackTrace();
			}
		}
		return true;
	}

	public List select(Object $object, ConnSession $session)
			throws ServiceException {
		try{
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			return DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).select(__mail_mailaccountt, $session);
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
	}

	public List select2(Object $object, ConnSession $session)
			throws ServiceException {
		try{
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			return DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).select2(__mail_mailaccountt, $session);
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
	}

	public Object selectById(Object $object, ConnSession $session)
			throws ServiceException {
		try{
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			return DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).selectById(__mail_mailaccountt, $session);
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
	}

	public int selectCount(Object $object, ConnSession $session)
			throws ServiceException {
		try{
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			return DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).selectCount(__mail_mailaccountt, $session);
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
	}

	public int update(Object $object, ConnSession $session)
			throws ServiceException {
		int _result = 0;
		
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			Mail_mailaccountt __mail_mailaccountt = (Mail_mailaccountt)$object;
			
			DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).update(__mail_mailaccountt, $session);
			
//			String __detailTable = __mail_mailaccountt.getDetailTable();
//			
//			if(__detailTable != null && !"".equals(__detailTable.trim())){
//				JSONArray ___jsonArray = (new JSONArray()).fromObject(__detailTable.trim());
//				
//				if(___jsonArray != null){
//					for(int ____i=0,____j=___jsonArray.size(); ____i<____j; ____i++){
//					
//						JSONObject _____jsonObject = (JSONObject)___jsonArray.get(____i);
//						
//						if(__mail_mailaccountt.getDetailTablesName().indexOf(_____jsonObject.get("tab").toString()) == -1) continue;
//						
//						Object _____object = this.getBean(_____jsonObject.get("tab").toString()).getClass().newInstance();
//						
//						for(Iterator _____iterator = _____jsonObject.keys(); _____iterator.hasNext();){
//						
//							String _____key = _____iterator.next().toString();
//							
//							if(!"tab".equals(_____key) && !"opt".equals(_____key)){
//								Ognl.setValue(_____key, _____object, _____jsonObject.get(_____key));
//							}
//						}
//						
//						IDao _____idao = (IDao)this.getBean(_____jsonObject.get("tab").toString() +"DAO");
//
//						Ognl.setValue("tab_sysmanage_code_id", _____object, __mail_mailaccountt.getId());
//						
//						switch(Integer.parseInt(_____jsonObject.get("opt").toString())){
//							case 1:{
//								_____idao.insert(_____object, $session);
//								break;
//							}
//							case 2:{
//								_____idao.update(_____object, $session);
//								break;
//							}
//							case 3:{
//								_____idao.deleteById(_____object, $session);
//								break;
//							}
//						}
//					}		
//				}
//			}
			
			_result = __mail_mailaccountt.getId();
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return _result;
	}

	public boolean updates(List $list, ConnSession $session)
			throws ServiceException {
		try{
			DaoFactory.getInstance().getSqlMap().startTransaction();
			
			for(int __i=0, __j=($list == null ? 0 : $list.size()); __i<__j; __i++){
				DaoFactory.getInstance().getDao(DaoFactory.MAIL_MAILACCOUNTTDAO).update($list.get(__i), $session);
			}
			
			DaoFactory.getInstance().getSqlMap().commitTransaction();
		}
		catch(Exception $ex){
			throw new ServiceException($ex.getMessage(), $ex);
		}
		finally{
			try {
				DaoFactory.getInstance().getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}