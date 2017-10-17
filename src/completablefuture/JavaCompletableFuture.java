package completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by maomao on 17/9/22.
 */
public class JavaCompletableFuture {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation returned after " + invocationTime + " msecs");
        Delay.delay();
        System.out.println("1m delay");
        try {
            Double price = futurePrice.get();
            System.out.printf("price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("price returned after " + retrievalTime + " msecs");
    }
}
