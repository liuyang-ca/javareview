package ca.liu.j2se.concurrency;

public class Consumer implements Runnable {
	public void run() {
		while(true) {
			Resource.instance.consume();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
