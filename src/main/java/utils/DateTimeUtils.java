package utils;

public class DateTimeUtils {

    public static void wait (int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException in Thread.sleep(). Message: " + e.getMessage());
        }
    }
}
