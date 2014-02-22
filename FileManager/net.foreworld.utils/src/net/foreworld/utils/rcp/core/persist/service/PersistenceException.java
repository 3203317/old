package net.foreworld.utils.rcp.core.persist.service;

/**
 * Runtime Exception thrown from Data Access Objects operations.
 * 
 * @author huangxin
 * @email huangxin@foreworld.net
 *
 */
public class PersistenceException extends RuntimeException {

	private static final long serialVersionUID = -163514432083712630L;
	
	
	/**
	 * Constructs a <code>PersistenceException</code> with no detail message.
	 */
	public PersistenceException(){
		super();
	}
	
	/**
	 * Constructs a <code>PersistenceException</code> with the specified detail message.
	 * 
	 * @param $detailMessage the detail message.
	 */
	public PersistenceException(String $detailMessage){
		super($detailMessage);
	}
	
	/**
	 * 
	 * @param $detailMessage
	 * @param $cause
	 */
	public PersistenceException(String $detailMessage, Throwable $cause){
		super($detailMessage, $cause);
	}
	
	
	/**
	 * 
	 * @param $cause
	 */
	public PersistenceException(Throwable $cause){
		super($cause);
	}
	
	

}
