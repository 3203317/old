package net.newcapec.sca.util;

import org.apache.tuscany.das.rdb.DAS;

public class DasHelper
{
	private static DAS das = null;

	public DasHelper()
	{
		this.getDefaultDAS();
	}

	public DAS getDefaultDAS()
	{
		String configFile = "session.xml";
		if(null == das)
		{
			das = DAS.FACTORY.createDAS(getClass().getClassLoader().getResourceAsStream(configFile));
		}
		return das;
	}
}
