package com.potato.concurrency.threadlocal;

public class RequestHolder {

	private final static ThreadLocal<Long> holder = new ThreadLocal<>();

	public static void setId(long id) {
		holder.set(id);
	}

	public static long getId() {
		return holder.get();
	}

	public static void remove() {
		holder.remove();
	}
}
