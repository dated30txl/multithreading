package example1;

import java.util.*;

public class Synchronized {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> l1 = Arrays.asList(1, 5, 7, 6, 4);
        List<Integer> l2 = Arrays.asList(11, 5, 27, 6, 34);
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());

        Thread thread1 = new Thread(()->{
            Integer max = Collections.max(l1, Integer::compare);
            //synchronized (result){
                result.add(max);
            //}
        });
        Thread thread2 = new Thread(()->{
            Integer max = Collections.max(l2, Integer::compare);
            //synchronized (result){
                result.add(max);
            //}
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(result);
        System.exit(0);
    }
}


