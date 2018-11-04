package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
	public static final int threshold = 5;

	private int start, end;

	ForkJoinTaskExample(int s, int e) {
		start = s;
		end = e;
	}


	@Override
	protected Integer compute() {
		if(end - start < threshold) {
			int tmp = 0;
			for (int i = start; i <= end; i++){
				tmp += i;
			}
			return tmp;
		} else {
			int mid = (start + end) / 2;
			ForkJoinTaskExample left = new ForkJoinTaskExample(start, mid);
			ForkJoinTaskExample right = new ForkJoinTaskExample(mid + 1, end);
			// run sub tasks
			left.fork();
			right.fork();
			return left.join() + right.join(); // get result of sub tasks
		}
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> future = forkJoinPool.submit(new ForkJoinTaskExample(0, 1000));
		try {
			log.info("result {}", future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
