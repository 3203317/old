package cn.newcapec.function.digitalcampus.common.exception;

import cn.newcapec.function.digitalcampus.common.SoftException;
import cn.newcapec.function.digitalcampus.common.SoftUtils;

public class CapecErrorException extends RuntimeException implements SoftException,SoftUtils {

	
	public CapecErrorException(String message){
		super(message);
	}
	
	public CapecErrorException(Exception e){
		super(e.getMessage());
		
	}
}
