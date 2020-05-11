package com.thread.wbcoder.ecutor_util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程化的线程池 
 * 它只会用唯一的工作线程来执行任务， 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * 
 * @author wb
 *
 */
public class w_SingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						System.out.println(Thread.currentThread().getName());
						Thread.sleep(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
