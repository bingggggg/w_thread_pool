package com.thread.wbcoder.thread_util;

public class w_Thread {

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "____Thread:" + i);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + "____Runnable:" + i);

				}
			}
		}).start();

		new myThread().start();

		new Thread(new myRunnable()).start();

	}
}

class myThread extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.getName() + "____extends:" + i);
		}
	}
}

class myRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "____implements:" + i);
		}
	}

}