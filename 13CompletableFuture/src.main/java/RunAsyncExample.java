import java.util.concurrent.CompletableFuture;

/**
 * Тут ми асинхронно запускаємо задачу без повернення результату.
 */
public class RunAsyncExample {
    public static void main(String[] args) {
        // Асинхронно виконуємо Runnable без повернення результату
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Асинхронна операція виконана! (Thread: " + Thread.currentThread().getName() + ")");
        });

        future.join();  // очікуємо завершення асинхронної операції
    }
}
