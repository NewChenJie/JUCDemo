package com.cj.offer.Fork;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @author : chenjie
 * @date : 2019-06-26 10:08
 * @describe :
 */
public class ForkOne {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyTask extends RecursiveTask<Integer> {
        private List<Integer> tmpList;

        @Override
        protected Integer compute() {
            int sum = 0;
            if (tmpList == null) {

            } else if (tmpList.size() < 6) {
                for (Integer tmp : tmpList) {
                    sum += tmp;
                }
            } else {
                List<Integer> right = tmpList.stream().skip(5).collect(Collectors.toList());
                List<Integer> left = tmpList.stream().limit(5).collect(Collectors.toList());
                MyTask one = new MyTask(right);
                MyTask two = new MyTask(left);
                invokeAll(one, two);
                sum = one.join() + two.join();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> tmpList = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            tmpList.add(i);
        }
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long start = System.currentTimeMillis();
        ForkJoinTask<Integer> task = pool.submit(new MyTask(tmpList));
        System.out.println(task.get());
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        long start1 = System.currentTimeMillis();
        int sum = 0;
        for (Integer tmp : tmpList) {
            sum += tmp;
        }
        System.out.println(sum);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }
}
