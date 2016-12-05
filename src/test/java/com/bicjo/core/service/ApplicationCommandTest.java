package com.bicjo.core.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.bicjo.parallel.Command;

public class ApplicationCommandTest {

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Test
	public void _test() throws Exception {
		for (int ctr = 0; ctr < 100; ctr++) {
			executorService.execute(new Command(ctr));
		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}
	}
}
