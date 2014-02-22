package net.foreworld.rss2.update;

import java.util.LinkedList;

/**
 * @author 黄鑫
 * @qq 3203317
 * @email 3203317@qq.com
 * @version 创建时间：Oct 8, 2008 5:46:06 PM
 */
public class WorkQueue {
	//线程池大小
	private final int nThreads;
	//用数组实现线程池
	private final PoolWorker[] jobs;
	//任务队列
	private final LinkedList queue;
	
	public WorkQueue(int nThreads){
		this.nThreads = nThreads;
		this.jobs = new PoolWorker[this.nThreads];
		this.queue = new LinkedList();
		
		//启动所有线程
		for(int i=0;i<this.nThreads;i++){
			jobs[i] = new PoolWorker();
			jobs[i].start();
		}
	}
	
	/**
	 * 执行任务
	 * @param job
	 */
	public void execute(Runnable job){
		synchronized(this.queue){
			this.queue.addLast(job);
			this.queue.notify();
		}
	}
	
	/**
	 * 线程池
	 * @author hx
	 * 检测任务队列中是否有任务，如果有则执行
	 */
	private class PoolWorker extends Thread{
		public void run() {
			Runnable job = null;
			while(true){
				synchronized(queue){
					//如果任务队列中没有任务，则等待
					while(queue.isEmpty()){
						try{
							queue.wait();
						}
						catch(InterruptedException  e){
							System.err.println("任务等待异常");
						}
					}
					//有任务时，取出任务
					job = (Runnable)queue.removeFirst();
				}
				try{
					//执行任务
					job.run();
				}
				catch(RuntimeException e){
					System.err.println("线程运行异常");
				}
			}
		}
		
	}
}
