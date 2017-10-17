package completablefuture;

import java.util.concurrent.*;

/**
 * Created by maomao on 17/9/28.
 */
public class FutureAfterJava8 {

    //模拟延迟
    private static void delay() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //java8之前的future的使用,并发异步
    public static void main(String[] args) {
        //创建一个ExecutorService,通过它你可以向线程池提交任务
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() {
                delay();
                System.out.println("异步执行耗时操作");
                return 1.01;
            }
        });
        System.out.println("异步操作的同时,执行别的操作");
        try {
            //如果阻塞,最多等一秒
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
