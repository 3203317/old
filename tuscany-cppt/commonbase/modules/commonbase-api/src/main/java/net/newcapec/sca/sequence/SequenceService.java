package net.newcapec.sca.sequence;

import org.oasisopen.sca.annotation.Remotable;


@Remotable
public interface SequenceService {

	Integer getCurrentValue(String commandName);

	Integer getNextValue(String commandName);

}
