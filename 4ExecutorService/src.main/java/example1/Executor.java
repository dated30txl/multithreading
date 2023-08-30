package example1;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<List<Integer>> ll = Arrays.asList(
                Arrays.asList(1, 15, 7, 6, 4),
                Arrays.asList(1, 5, 7, 76, 4),
                Arrays.asList(1, 5, 97, 6, 4),
                Arrays.asList(10, 5, 7, 46, 4),
                Arrays.asList(1, 5, 71, 6, 41));

        ExecutorService es = Executors.newFixedThreadPool(4);

        List<Future<Integer>> futures = new ArrayList<>();

        for (List<Integer>l1: ll){
            Future<Integer> submit = es.submit(()-> Collections.max(l1, Integer::compare));
            futures.add(submit);
        }

        for (Future<Integer> future: futures){
            System.out.println(future.get());
        }

        System.out.println(ll.parallelStream()
                .map(
                        (l)->Collections.max(l, Integer::compare)
                ).toList());

        System.exit(0);
    }
}


