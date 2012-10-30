package ca.liu.j2se.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

public enum Resource {
	instance;
	private int count = 5;
	private final static int max = 10;
	private AtomicBoolean flag;
	
	Resource() {
		flag = new AtomicBoolean(true);
	}

	public void produce() {
		if(flag.get()) {
			flag.set(false);
			if(count < max) {
				count++;
			}
			System.out.println("+++++++++++: " + getCount());
			flag.set(true);
		} else {
			System.out.println("++++++Locked+++++");
		}
	}
	//synchronized
	public void consume() {
		if(flag.get()) {
			flag.set(false);
			if(count > 0) {
				count--;
			}
			System.out.println("-----------: "+ getCount());
			flag.set(true);
		} else {
			System.out.println("------Locked-----");
		}
	}
	
	public int getCount() {
		return count;
	}
		
	public static void main(String[] args) {
		(new Thread(new Producer())).start();
		(new Thread(new Consumer())).start();
	}
}
