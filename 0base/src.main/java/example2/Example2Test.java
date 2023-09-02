package example2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class Example2Test {

    @Test
    public void testMultithreadingBehavior() throws InterruptedException {
        Set<String> observedResults = new HashSet<>();

        for (int i = 0; i < 150000; i++) {
            observedResults.add(Example2.executeThreadsAndGetResult());
        }

        // Перевіримо, чи ми отримали всі можливі комбінації результатів
        //в теорії комбінація x=0, y=0 є неможливою,

        assertTrue("x=0, y=0 was not observed", observedResults.contains("x=0, y=0"));

        // але тест іноді показує зворотне, що може бути викликане
        // при оптимізації на рівні компілятора, або процесора що може змінити порядок операцій

        assertTrue("x=1, y=0 was not observed", observedResults.contains("x=1, y=0"));
        assertTrue("x=0, y=1 was not observed", observedResults.contains("x=0, y=1"));
        assertTrue("x=1, y=1 was not observed", observedResults.contains("x=1, y=1"));

        // Спостережувані результати
        observedResults.forEach(System.out::println);
    }
}
