import java.util.concurrent.CompletableFuture;

/**
 * В цьому прикладі ми асинхронно запускаємо задачу із поверненням результату.
 */

public class SupplyAsyncExample {
    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Результат асинхронної операції";
        });

        future.thenAccept(result -> System.out.println("Результат: " + result));
    }
}
