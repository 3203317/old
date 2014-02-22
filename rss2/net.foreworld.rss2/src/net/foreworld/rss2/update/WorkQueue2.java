package net.foreworld.rss2.update;

import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;


/**
 * @author ����
 * @qq 3203317
 * @email 3203317@qq.com
 * @version ����ʱ�䣺Oct 9, 2008 4:33:37 PM
 */
public class WorkQueue2 {
	//�̳߳ش�С
	private final int nThreads;
	//������ʵ���̳߳�
	private final PoolWorker[] jobs;
	//�������
	private final LinkedList queue;
	
	public WorkQueue2(int nThreads){
		this.nThreads = nThreads;
		this.jobs = new PoolWorker[this.nThreads];
		this.queue = new LinkedList();
		
		//���������߳�
		for(int i=0;i<this.nThreads;i++){
			jobs[i] = new PoolWorker();
			jobs[i].schedule();
		}
	}
	
	/**
	 * ִ������
	 * @param job
	 */
	public void execute(Job job){
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
	private class PoolWorker extends Job{
		public PoolWorker() {
			super("�̳߳�");
		}
		
		protected IStatus run(IProgressMonitor arg0) {
			Job job = null;
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
					job = (Job)queue.removeFirst();
				}
				try{
					//ִ������
					job.schedule(3000);
					try {
						job.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				catch(RuntimeException e){
					System.err.println("�߳������쳣");
				}
			}
		}
		
	}
}
