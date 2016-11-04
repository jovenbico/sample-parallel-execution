package com.bicjo.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import com.bicjo.parallel.CallableTask1;

public class ApplicationServiceTest1K implements IService {

	private int workload = 400;
	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Rule
	public Stopwatch stopwatch = new Stopwatch() {
		@Override
		protected void succeeded(long nanos, org.junit.runner.Description description) {
			String.format("Test %s %s, spent %d microseconds", description.getMethodName(), "succeeded",
					TimeUnit.MINUTES.toMicros(nanos));
		};
	};

	@Test
	@Override
	public void workParallel() {
		List<Future<Integer>> results = new ArrayList<>();
		for (int ctr = workload; ctr > 0; ctr--) {
			results.add(executorService.submit(new CallableTask1(ctr)));
		}

		try {

			for (Future<Integer> result : results) {
				System.out.println(">>>received " + result.get());
			}
			System.out.println("<<<completion>>> " + stopwatch.runtime(TimeUnit.MILLISECONDS));

		} catch (InterruptedException | ExecutionException e) {
			System.err.println(e.getMessage());
		} finally {
			if (!executorService.isShutdown()) {
				executorService.shutdown();
			}
		}
	}

	@Test
	public void work() {
		try {
			for (int ctr = workload; ctr > 0; ctr--) {
				Integer result = new CallableTask1(ctr).call();
				System.out.println(">>>received " + result);
			}

			System.out.println("<<<completion>>> " + stopwatch.runtime(TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
