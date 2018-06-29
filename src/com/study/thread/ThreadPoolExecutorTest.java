package com.study.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1. 线程池的返回值ExecutorService简介：
 * ExecutorService是Java提供的用于管理线程池的类。该类的两个作用：控制线程数量和重用线程 2.
 * 具体的4种常用的线程池实现如下：（返回值都是ExecutorService）
 */
public class ThreadPoolExecutorTest {
	public static void main(String[] args) {

		ste();
	}

	/**
	 * 2.1 Executors.newCacheThreadPool()：可缓存线程池，先查看池中有没有以前建立的线程，如果有，就直接使用。
	 * 如果没有，就建一个新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务
	 */
	static void ctp() {
		// 创建一个可缓存线程池
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			try {
				// sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					// 打印正在执行的缓存线程信息
					// 线程池为无限大，当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
					System.out.println(Thread.currentThread().getName() + "正在被执行："
							+ new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
				}
			});
		}
	}

	/**
	 * 2.2 Executors.newFixedThreadPool(int
	 * n)：创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程。
	 */
	static void ftp() {
		// 创建一个可重用固定个数的线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						// 打印正在执行的缓存线程信息
						System.out.println(Thread.currentThread().getName() + "正在被执行"
								+ new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
						Thread.sleep(2000);
						// 因为线程池大小为3，每个任务输出打印结果后sleep 2秒，所以每两秒打印3个结果。
						// 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/** 2.3 Executors.newScheduledThreadPool(int n)：创建一个定长线程池，支持定时及周期性任务执行 */
	static void stp() {
		// 创建一个定长线程池，支持定时及周期性任务执行——延迟执行
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		// 延迟1秒执行
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("延迟1秒执行");
			}
		}, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * 2.4  Executors.newSingleThreadExecutor()：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
	 * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	 * */
	static void ste() {
		//创建一个单线程化的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        //结果依次输出，相当于顺序执行各个任务
                        System.out.println(Thread.currentThread().getName()+"正在被执行,打印的值是:"+index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
	}
	
}
