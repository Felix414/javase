package com.study.thread;

import java.lang.Thread.State;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;





public class MyFixedSizeThreadPool {

	//仓库
	private BlockingQueue<Runnable> taskQueue;
	//放线程的集合
	private List<Worker> workers;
	
	private boolean working = true;
	
	public MyFixedSizeThreadPool (int poolSize, int taskQueueSize) {
		if(poolSize <= 0 || taskQueueSize <= 0) {
			throw new IllegalArgumentException("参数异常");
		}
		
		//初始化任务队列
		this.taskQueue = new LinkedBlockingQueue<>(taskQueueSize);
		
		//创建放线程的集合
		this.workers = Collections.synchronizedList(new LinkedList<>());
		
		//创建线程
		for ( int i = 0; i< poolSize; i++) {
			Worker w = new Worker(this);
			this.workers.add(w);
		}
	}
	
	public boolean submit(Runnable task){
		return this.taskQueue.offer(task);
	}
	
	public void shutDown() {
		if(this.working) {
			this.working = false;
			
			for(Thread t : this.workers){
				if(t.getState().equals(State.BLOCKED) 
						|| t.getState().equals(State.WAITING)){
					t.interrupt();
				}
			}
		}
	}
	
	private class Worker extends Thread {
		private MyFixedSizeThreadPool pool;
		
		public Worker(MyFixedSizeThreadPool pool) {
			super();
			this.pool = pool;
		}
		
		public void run () {
			int taskCount = 0;
			//从队列中取任务，去执行，要能不听地取
			while(this.pool.working || this.pool.taskQueue.size() > 0 ) {
				Runnable task = null;
				
				try {
					task = this.pool.taskQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(task != null) {
					try {
						task.run();
						System.out.println(Thread.currentThread().getName() + "执行完第"+ (++taskCount) + "个任务" );
					}catch(Exception e){
						
					}
					
				}
			}
			System.out.println(Thread.currentThread().getName() + "结束了");
		}
	}
	
	public static void main(String[] args) {
		MyFixedSizeThreadPool pool = new MyFixedSizeThreadPool(3, 5);
		for(int i = 0; i<5; i++) {
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("任务开始。。。");
					try {
						Thread.sleep(2000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		pool.shutDown();
	}
}
