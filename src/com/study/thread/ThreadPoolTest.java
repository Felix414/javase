package com.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
那么线程池的排除策略是什么样呢，一般按如下规律执行：
A.  如果运行的线程少于 corePoolSize，则 Executor 始终首选添加新的线程，而不进行排队。
B.  如果运行的线程等于或多于 corePoolSize，则 Executor 始终首选将请求加入队列，而不添加新的线程。
C.  如果无法将请求加入队列，则创建新的线程，除非创建此线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。

总结：
1. 线程池可立即运行的最大线程数 即maximumPoolSize 参数。
2. 线程池能包含的最大线程数 = 可立即运行的最大线程数 + 线程队列大小 (一部分立即运行，一部分装队列里等待)
3. 核心线程数可理解为建议值，即建议使用的线程数，或者依据CPU核数
4. add，offer，put三种添加线程到队列的方法只在队列满的时候有区别，add为抛异常，offer返回boolean值，put直到添加成功为止。
5.同理remove，poll， take三种移除队列中线程的方法只在队列为空的时候有区别， remove为抛异常，poll为返回boolean值， take等待直到有线程可以被移除。
 */
public class ThreadPoolTest implements Runnable {
	public void run() {
		synchronized (this) {
			try {
				System.out.println("线程名称：" + Thread.currentThread().getName());
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		abq();
	}

	static void lbq() {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat("" + i)));
			int threadSize = queue.size();
			System.out.println("线程队列大小为-->" + threadSize);
		}
		executor.shutdown();
	}

	static void abq() {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4); // 固定为4的线程队列
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat("" + i)));
			int threadSize = queue.size();
			System.out.println("线程队列大小为-->" + threadSize);

			if (threadSize == 4) {
				/*add 队列已满，抛异常 */
				/*queue.add(new Runnable() {  //队列已满，抛异常  
	                @Override  
	                public void run(){  
	                    System.out.println("我是新线程，看看能不能搭个车加进去！");  
	                      
	                }  
	            }); */ 
				
				/*offer 可以看到，当线程队列已满的时候，线程没有被添加到线程队列，程序也没有抛异常。*/
				/*final boolean flag = queue.offer(new Runnable() {
					@Override
					public void run() {
						System.out.println("我是新线程，看看能不能搭个车加进去！");
					}
				});
				System.out.println("添加新线程标志为-->" + flag);*/
				
				/*put 很明显，尝试了四次才加进去，前面三次尝试添加，但由于线程sleep(3000)，所以没有执行完，
				线程队列一直处于满的状态，直到某个线程执行完，队列有空位，新线程才加进去，
				没空位之前一直阻塞（即等待），我能加进去为止。*/
				try {
					queue.put(new Runnable() {  
					    @Override  
					    public void run(){  
					        System.out.println("我是新线程，看看能不能搭个车加进去！");  
					    }  
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		executor.shutdown();
	}
}
