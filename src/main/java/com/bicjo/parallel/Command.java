package com.bicjo.parallel;

public class Command implements Runnable {

	private int counter;

	public Command(int counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1 + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("command-execution " + counter);
	}

}
