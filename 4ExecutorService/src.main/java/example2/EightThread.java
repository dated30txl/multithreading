package example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EightThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService threadPool = Executors.newFixedThreadPool(8);

        CustomThreadPoolExecutor threadPool = new CustomThreadPoolExecutor (8);
        Counter counter = new Counter();

        long start = System.nanoTime();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j),
                            threadPool
                    ));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        // Executors.newFixedThreadPool(8): 4sec
        // new CustomThreadPoolExecutor (8): 4sec
        System.out.printf("Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000_000),
                value);

        threadPool.shutdown();
    }
}
