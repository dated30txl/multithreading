package example1;

import java.util.Arrays;

public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        for (String s : Arrays.asList(
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        )) {
            // Чекаємо 2 секунди
            Thread.sleep(2000);

            System.out.println(s);
        }
    }
}


