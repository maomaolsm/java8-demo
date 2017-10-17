package completablefuture;

/**
 * Created by maomao on 17/10/9.
 */
public class Delay {
    protected static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
