package net.newcapec.sca.sequence.impl;

import net.newcapec.sca.sequence.SequenceInit;
import net.newcapec.sca.sequence.SequenceService;

import org.apache.log4j.Logger;
import org.oasisopen.sca.annotation.Reference;

public class SequenceServiceImpl implements SequenceService{

    public static final Logger sequenceSerivceLog = Logger.getLogger(SequenceServiceImpl.class);

    private SequenceInit sequenceInit;
    @Reference(name = "sequenceInit", required = true)
    public void setSequenceInit(SequenceInit SequenceInit) {
            this.sequenceInit = SequenceInit;
    }
    public Integer getCurrentValue(String commandName){
        Integer currentValue;
        try
        {
            int currentVauleInfo = sequenceInit.getSequence(commandName).currentValue();
            currentValue = new Integer(currentVauleInfo);
        }
        catch (Exception e)
        {
            sequenceSerivceLog.error(e.getMessage(),e);
            currentValue = new Integer(-1);
        }
        sequenceSerivceLog.debug("commandName : " + commandName + " ,currentValue:" + currentValue);
        return currentValue;
    }

    public Integer getNextValue(String commandName){
        Integer nextValue;
        try
        {
            int currentVauleInfo =sequenceInit.getSequence(commandName).getNextValue();
            nextValue = new Integer(currentVauleInfo);
        }
        catch (Exception e)
        {
            sequenceSerivceLog.error(e.getMessage(),e);
            nextValue = new Integer(-1);
        }
        sequenceSerivceLog.debug("commandName: " + commandName + " ,nextValue:" + nextValue);
        return nextValue;

    }

}
