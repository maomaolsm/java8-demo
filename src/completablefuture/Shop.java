package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by maomao on 17/10/9.
 */
public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    //以ShopName:price:DiscountCode格式返回一个String类型的值
    public String getPrice2(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        Delay.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    //将同步方法转换为异步方法
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //抛出另外一个线程中的异常,完成future的操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    //使用工厂方法supplyAsync创建completableFuture对象,同样提供了错误管理机制
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


}
