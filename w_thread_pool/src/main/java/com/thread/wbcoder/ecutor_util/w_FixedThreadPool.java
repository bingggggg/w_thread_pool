package com.thread.wbcoder.ecutor_util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定长线程池 可控制线程最大并发数，超出的线程会在队列中等待。
 * 
 * @author wb
 *
 */
public class w_FixedThreadPool {

	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			newFixedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index);
					System.out.println(Thread.currentThread().getName());
					//以睡眠时间监听线程池的大小是否变化
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
