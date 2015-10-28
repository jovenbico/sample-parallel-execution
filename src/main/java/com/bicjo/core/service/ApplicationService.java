package com.bicjo.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.bicjo.parallel.CallableTask1;
import com.bicjo.parallel.CallableTask2;
import com.bicjo.parallel.CallableTask3;

public class ApplicationService implements IService {

	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	@SuppressWarnings("serial")
	@Override
	public void work() {
		CallableTask1 task1 = new CallableTask1(1);
		CallableTask2 task2 = new CallableTask2("2");
		CallableTask3 task3 = new CallableTask3(new ArrayList<String>() {
			{
				add("1");
				add("2");
				add("3");
			}
		});

		Future<Integer> result1 = executorService.submit(task1);
		Future<String> result2 = executorService.submit(task2);
		Future<List<String>> result3 = executorService.submit(task3);

		try {

			System.out.println(result1.get() + result2.get() + result3.get());
			System.out.println("<<<completion>>>");

		} catch (InterruptedException | ExecutionException e) {
			System.err.println(e.getMessage());
		} finally {
			if (!executorService.isShutdown()) {
				executorService.shutdown();
			}
		}
	}

}
