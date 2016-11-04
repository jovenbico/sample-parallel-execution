package com.bicjo.parallel;

import java.util.concurrent.Callable;

public class CallableTask1 implements Callable<Integer> {

	private Integer value;

	public CallableTask1(Integer value) {
		this.value = value;
	}

	@Override
	public Integer call() throws Exception {

		Thread.sleep(1 + 1000);
		System.out.println("<<<task1 " + this.value);

		return value;
	}

}
