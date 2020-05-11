package com.thread.wbcoder.thread_pool_excutor_util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 其会优先创建核心线程，执行任务，
 * 当核心线程增加CorePoolSize后，我们会把任务添加到work Queue中，
 * 当work Queue里面的任务也塞满了，线程池就会创建非核心线程执行去执行任务，
 * 当线程达到maximumPoolSize时候和work queue也达最大值时候我们会执行对应的拒绝策略
 * @author wb
 *
 */
public class w_ThreadPoolExecutor {
	final static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static void main(String[] args) {
		//   corePoolSize：      线程池维护线程的最少数量 （core : 核心）
		//      maximumPoolSize：   线程池维护线程的最大数量 
		//      keepAliveTime：     线程池维护线程所允许的空闲时间
		//      unit：               线程池维护线程所允许的空闲时间的单位
		//      workQueue：          线程池所使用的缓冲队列
		//      handler：            线程池对拒绝任务的处理策略
		// new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
		// workQueue);

		// 核心：1，最大：5，超时时间：60s，队列：ArrayBlockingQueue
		// 最大线程输出 ：最大+队列 超出异常
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3));
		for (int i = 0; i < 0; i++) {
			threadPoolExecutor.execute(new myThread());
		}

		// LinkedBlockingQueue不设置数值时，有序取任务，maximumPoolSize设置可能失效。最大线程输出 ：核心
		// 设置值时与ArrayBlockingQueue相同
		ThreadPoolExecutor threaPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		for (int i = 0; i < 0; i++) {
			threaPoolExecutor.execute(new myThread());
		}

		// SynchronousQueue不存值。最大线程输出 ：最大 超出异常
		// 常用设置 核心：0，最大：Integer.MAX_VALUE
		ThreadPoolExecutor threPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		for (int i = 0; i < 0; i++) {
			threPoolExecutor.execute(new myThread());
		}
		
		
		
		
		// 拒接策略，报异常（> 最大+队列数）
		ThreadPoolExecutor thrPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 0; i++) {
			thrPoolExecutor.execute(new myThread());
		}
		// 拒接策略，创建新的（> 最大+队列数）
		ThreadPoolExecutor thPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 0; i++) {
			thPoolExecutor.execute(new myThread());
		}
		// 拒接策略，抛弃旧的（> 最大+队列数）
		ThreadPoolExecutor tPoolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
		for (int i = 0; i < 0; i++) {
			tPoolExecutor.execute(new myThread());
		}
		// 拒接策略，抛弃心中的（> 最大+队列数）
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardPolicy());
		for (int i = 0; i < 0; i++) {
			poolExecutor.execute(new myThread());
		}
		
		
		//获取返回（自定义返回与异常抛出）
		Future<Object> future = poolExecutor.submit(new Callable<Object>() {
			@Override
			public  String  call() throws Exception {
				// throw new RuntimeException("exception in call~");
				// 该异常会在调用Future.get()时传递给调用者
				System.out.println(Thread.currentThread().getName() + "-begin-" + sf.format(new Date()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+ "-end-" + sf.format(new Date()));
				return "ddd";
			}
		});
		try {
			Object result = future.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			// interrupt
		} catch (ExecutionException e) {
			// exception in Callable.call()
			e.printStackTrace();
		}
	}
}

class myThread extends Thread {
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + "____extends:  00000 ");
	}
}