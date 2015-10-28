package com.bicjo.parallel;

import java.util.concurrent.Callable;

public class CallableTask2 implements Callable<String> {

	private String value;

	public CallableTask2(String value) {
		this.value = value;
	}

	@Override
	public String call() throws Exception {

		System.out.println(">>>task2");
		Thread.sleep(4 + 1000);
		System.out.println("<<<task2");

		return value;
	}

}
