package cn.newcapec.function.digitalcampus.common.exception;

import cn.newcapec.function.digitalcampus.common.SoftException;
import cn.newcapec.function.digitalcampus.common.SoftUtils;

public class CapecException extends RuntimeException implements SoftException,SoftUtils {

	
	public CapecException(String message){
		super(message);
	}
	
	public CapecException(Exception e){
		super(e.getMessage());
		
	}
}
