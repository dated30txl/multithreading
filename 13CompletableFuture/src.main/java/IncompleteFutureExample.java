import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Метод newIncompleteFuture() дозволяє створити новий незавершений
 * CompletableFuture того ж типу, що і оригінальний.
 * Він часто використовується для створення власних асинхронних операцій.
 */

public class IncompleteFutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> original = CompletableFuture.completedFuture("Оригінальне значення");

        CompletableFuture<String> newFuture = original.newIncompleteFuture();

        // Додаємо задачу до нового CompletableFuture
        newFuture.thenAccept(result -> {
            System.out.println("Значення нового CompletableFuture: " + result);
        });

        System.out.println("newFuture ще не завершений...");

        // Імітуємо якусь операцію, після якої ми завершуємо newFuture
        Thread.sleep(2000);

        newFuture.complete("Нове завершене значення");

        // Щоб головний потік чекав завершення
        newFuture.join();
    }
}
