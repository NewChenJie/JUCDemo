package com.cj.offer.Fork;

import com.google.common.collect.Maps;

import java.util.concurrent.*;

/**
 * @author : chenjie
 * @date : 2019-06-27 14:08
 * @describe :
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int i = 10;
        do {
            pool.execute(() -> doSomething(semaphore));
            i--;
        } while (i != 0);
        Thread.sleep(50000);
    }

    private static void doSomething(Semaphore semaphore)  {
        try {
            semaphore.acquire();
            System.out.println("开始执行");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("结束执行");
            semaphore.release();
        }
    }
}
