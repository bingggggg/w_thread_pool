package com.thread.wbcoder.ecutor_util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程池
 * 如果线程池长度超过处理需要， 可灵活回收空闲线程， 若无可回收，则新建线程。
 * 
 * @author wb
 *
 */
public class w_CachedThreadPool {

	public static void main(String[] args) {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			// 加睡眠时间，查看线程是否新创建
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			newCachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index);
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
	}
}
