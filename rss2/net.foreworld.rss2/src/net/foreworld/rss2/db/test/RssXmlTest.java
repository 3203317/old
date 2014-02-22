package net.foreworld.rss2.db.test;

import junit.framework.TestCase;
import net.foreworld.rss2.utils.UpdateJob;

import org.eclipse.core.runtime.jobs.Job;

/**
 * 
 * @author »ÆöÎ
 * @qq 3203317
 * @email 3203317@qq.com
 * @Aug 16, 2008 9:06:15 PM
 */
public class RssXmlTest extends TestCase {

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	public void testGetFilePath() {
//		UpdateJob uj = new UpdateJob("¸üÐÂÆµµÀ");
//		uj.setUser(true);
//		uj.setPriority(Job.SHORT);
//		uj.schedule(10000);
		String aa = "/designMap/contentProviders/contentProvider/method";
		String[] aaa = aa.split("/");
	
		for(int i=1;i<aaa.length;i++){
			if(!aaa[i].trim().equals(""))
			System.out.println(aaa[i]);
		}
		System.out.println(aaa.length);
	}

}
