import java.util.concurrent.CompletableFuture;

/**
 * Метод defaultExecutor() з CompletableFuture повертає Executor,
 * який виконує завдання у потоці додатка або у головному потоці,
 * якщо потік додатку недоступний. Цей Executor не використовує додаткові потоки,
 * а виконує задачі асинхронно в контексті вже що існує потоку.
 * Якщо головний потік додатку доступний, defaultExecutor() в CompletableFuture використовуватиме його.
 * Якщо головний потік заблокований, задачі будуть виконуватися в потоці,
 * де був викликаний метод complete() або аналогічний.
 */

public class DefaultExecutorExample {
    public static void main(String[] args) {
        CompletableFuture<String> initialFuture = new CompletableFuture<>();

        // Використання defaultExecutor()
        initialFuture.thenApplyAsync(result -> {
                    System.out.println("Трансформація результату (Thread: " + Thread.currentThread().getName() + ")");
                    return result.toUpperCase();
                }, initialFuture.defaultExecutor())
                .thenAccept(result -> {
                    System.out.println("Остаточний результат: " + result);
                });

        System.out.println("Встановлюємо результат для initialFuture...");
        // Задаємо результат для initialFuture
        initialFuture.complete("Початковий результат");
    }
}
