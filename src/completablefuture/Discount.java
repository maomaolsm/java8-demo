package completablefuture;

import static com.sun.tools.javac.util.Constants.format;

/**
 * Created by maomao on 17/10/10.
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static String apply(double price, Code discountCode) {
        Delay.delay();
        return format(price * (100 - discountCode.percentage) / 100);
    }
}
