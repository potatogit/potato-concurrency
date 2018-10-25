package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式 双重同步锁单例模式
 */
@NotThreadSafe
public class SingletonExample4 {

	private static SingletonExample4 example = null;

	private SingletonExample4() {}

	public static SingletonExample4 getSingleton() {
		if(example == null) {
			synchronized (SingletonExample4.class){ //同步锁
				if(example == null) { // 双重检测
					/**
					 * 1. 分配内存空间
					 * 3. example 指向分配的内存空间 （2，3指令重排，会造成线程不安全）
					 * 2. 初始化对象
					 */
					example = new SingletonExample4();
				}
			}
		}
		return example;
	}

}
