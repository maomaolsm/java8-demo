package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 * Created by maomao on 17/9/22.
 */
public class JavaCompletableFuture2 {

    List<Shop> shops = Arrays.asList(new Shop("BestShop"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("TaoBao"),
            new Shop("TaoBao"),
            new Shop("TaoBao"),
            new Shop("TaoBao"),
            new Shop("BuyItAll"));

    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    //对findPrices进行并行操作
    public List<String> findPrices2(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPrices3(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product), executor))
                        .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public static void main(String[] args) {
        JavaCompletableFuture2 javaCompletableFuture2 = new JavaCompletableFuture2();
        long start = System.nanoTime();
        System.out.println(javaCompletableFuture2.findPrices("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        System.out.println(javaCompletableFuture2.findPrices2("myPhone27s"));
        long duration2 = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done2 in " + duration2 + " msecs");

        System.out.println(javaCompletableFuture2.findPrices3("myPhone27s"));
        long duration3 = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done3 in " + duration3 + " msecs");

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
