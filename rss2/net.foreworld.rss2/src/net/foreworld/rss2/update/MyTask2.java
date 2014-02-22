package net.foreworld.rss2.update;
/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 9, 2008 3:41:31 PM
 */
public class MyTask2 implements Runnable {

	public void run() {
		String name = Thread.currentThread().getName();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println(name + " executed OK");
		}
		System.out.println(name);
		
	}

}
