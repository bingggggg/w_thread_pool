package com.thread.wbcoder.ecutor_util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定长线程池，支持定时及周期性任务执行。
 * 
 * @author wb
 *
 */
public class w_ScheduledThreadPool {

	public static void main(String[] args) {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(availableProcessors);
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		// 延迟1秒后每3秒执行一次
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
				System.out.println(Thread.currentThread().getName());
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

}
