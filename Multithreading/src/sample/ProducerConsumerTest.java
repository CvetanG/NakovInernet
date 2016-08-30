package sample;

import java.util.Date;
import java.util.Vector;

class SharedQueue {
	private static final int QUEUE_SIZE = 5;
	private Vector mQueue = new Vector();

	public synchronized void put(String aObject) throws InterruptedException {
		while (mQueue.size() == QUEUE_SIZE) {
			wait();
		}
		mQueue.addElement(aObject);
		notify();
	}

	public synchronized Object get() throws InterruptedException {
		while (mQueue.size() == 0) {
			wait();
		}
		String message = (String) mQueue.firstElement();
		mQueue.removeElement(message);
		notify();
		return message;
	}
}

class Producer extends Thread {

	private SharedQueue mSharedQueue;

	public Producer(SharedQueue aSharedQueue) {
		mSharedQueue = aSharedQueue;
	}

	public void run() {
		try {
			while (true) {
				String message = new Date().toString();
				System.out.println("producer : put " + message);
				mSharedQueue.put(message);
				sleep(500);
			}
		} catch (InterruptedException e) {
		}
	}
}

class Consumer extends Thread {
	private SharedQueue mSharedQueue;

	public Consumer(SharedQueue aSharedQueue) {
		mSharedQueue = aSharedQueue;
	}

	public void run() {
		try {
			while (true) {
				String message = (String) mSharedQueue.get();
				System.out.println(getName() + " : get " + message);
				sleep(2000);
			}
		} catch (InterruptedException e) {
		}
	}
}

public class ProducerConsumerTest {
	public static void main(String args[]) {
		SharedQueue sharedQueue = new SharedQueue();
		Producer producer = new Producer(sharedQueue);
		producer.start();
		Consumer consumer1 = new Consumer(sharedQueue);
		consumer1.setName("consumer Mincho");
		consumer1.start();
		Consumer consumer2 = new Consumer(sharedQueue);
		consumer2.setName("consumer Pencho");
		consumer2.start();
	}
}
