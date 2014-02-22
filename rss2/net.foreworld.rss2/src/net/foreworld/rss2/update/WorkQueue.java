package net.foreworld.rss2.update;

import java.util.LinkedList;

/**
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @version ����ʱ�䣺Oct 8, 2008 5:46:06 PM
 */
public class WorkQueue {
	//�̳߳ش�С
	private final int nThreads;
	//������ʵ���̳߳�
	private final PoolWorker[] jobs;
	//�������
	private final LinkedList queue;
	
	public WorkQueue(int nThreads){
		this.nThreads = nThreads;
		this.jobs = new PoolWorker[this.nThreads];
		this.queue = new LinkedList();
		
		//���������߳�
		for(int i=0;i<this.nThreads;i++){
			jobs[i] = new PoolWorker();
			jobs[i].start();
		}
	}
	
	/**
	 * ִ������
	 * @param job
	 */
	public void execute(Runnable job){
		synchronized(this.queue){
			this.queue.addLast(job);
			this.queue.notify();
		}
	}
	
	/**
	 * �̳߳�
	 * @author hx
	 * �������������Ƿ��������������ִ��
	 */
	private class PoolWorker extends Thread{
		public void run() {
			Runnable job = null;
			while(true){
				synchronized(queue){
					//������������û��������ȴ�
					while(queue.isEmpty()){
						try{
							queue.wait();
						}
						catch(InterruptedException  e){
							System.err.println("����ȴ��쳣");
						}
					}
					//������ʱ��ȡ������
					job = (Runnable)queue.removeFirst();
				}
				try{
					//ִ������
					job.run();
				}
				catch(RuntimeException e){
					System.err.println("�߳������쳣");
				}
			}
		}
		
	}
}
