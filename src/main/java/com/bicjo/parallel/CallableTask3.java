package com.bicjo.parallel;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableTask3 implements Callable<List<String>> {

	private List<String> values;

	public CallableTask3(List<String> values) {
		this.values = values;
	}

	@Override
	public List<String> call() throws Exception {

		System.out.println(">>>task3");
		Thread.sleep(1 + 1000);
		System.out.println("<<<task3");

		return values;
	}

}
