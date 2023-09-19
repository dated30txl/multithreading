import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

/**
 * Future дозволяє представити результат асинхронної операції
 */

public class FutureExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Запускаємо задачу асинхронно
        Future<String> future = executor.submit(FutureExample::loadData);

        System.out.println("Очікуємо завершення завантаження...");

        try {
            // Отримуємо результат (блокуючи виклик до завершення завдання)
            String result = future.get();
            System.out.println(result);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println("Починаємо виконувати решту коду");
    }

    public static String loadData() throws InterruptedException {
        Thread.sleep(2000); // Симулюємо затримку у 2 секунди
        return "Дані завантажені";
    }
}


