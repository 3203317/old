package net.newcapec.sca.sequence.impl;

import net.newcapec.sca.sequence.Sequence;

public class SequenceImpl implements Sequence{
	private Integer counter = 0;
	private final Boolean COUNTER_UP = true;
	private final Boolean COUNTER_DOWN = false;


	public SequenceImpl(Integer tempCounter)
	{
		this.counter = tempCounter;
	}

	public Integer getNextValue() {
		setCounter(COUNTER_UP);
		return counter;
	}

	public Integer currentValue() {
		return counter;
	}

	private synchronized void setCounter(Boolean updown) {

		if (updown) {
			++counter;
		} else {
			--counter;
		}
	}
}
