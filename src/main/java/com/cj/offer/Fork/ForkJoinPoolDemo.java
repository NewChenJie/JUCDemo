package com.cj.offer.Fork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenjie
 * @date : 2019-06-26 9:51
 * @describe :
 */
public class ForkJoinPoolDemo {
    class SendMsgTask extends RecursiveAction {

        private final int THRESHOLD = 10;

        private int start;
        private int end;
        private List<String> list;

        public SendMsgTask(int start, int end, List<String> list) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((end - start) <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
                }
            }else {
                int middle = (start + end) / 2;
                invokeAll(new SendMsgTask(start, middle, list), new SendMsgTask(middle, end, list));
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(String.valueOf(i+1));
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoinPoolDemo().new SendMsgTask(0, list.size(), list));
        pool.awaitTermination(1, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
