package net.newcapec.sca.sequence;

import org.oasisopen.sca.annotation.Remotable;

@Remotable
public interface Sequence{

	public Integer getNextValue() ;

	public Integer currentValue() ;


}
