import java.util.concurrent.CompletableFuture;

/**
 * У цьому прикладі ми створюємо вже завершений CompletableFuture з певним значенням та виводимо його.
 */
public class CompletedFutureExample {
    public static void main(String[] args) {

        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Готовий результат");

        completedFuture.thenAccept(result -> System.out.println("Результат: " + result));
    }
}
