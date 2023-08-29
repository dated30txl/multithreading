package example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class Lock{
    volatile boolean locked = false;
    void lock(){
       if(!locked) {
           locked = true;
       } else {
           while (locked) {
               try{
                   Thread.sleep(1000, 10000);
               } catch (InterruptedException ignored){

               }

           }
       }
    }
    void unlock(){
        locked = false;
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> dList = Arrays.asList(2,5,9,9,1,8,3,6);
        List<Integer> dList2 = Arrays.asList(2,15,9,59,1,8,83,6);
        List<Integer> result = new ArrayList<>();
        ReentrantLock l = new ReentrantLock();

        Thread thread1 = new Thread(()-> {
            Integer max = Finder.max(dList);
            l.lock();
            result.add(max);
            l.unlock();
        });


        Thread thread2 = new Thread(()-> {
            Integer max = Finder.max(dList2);
            l.lock();
            result.add(max);
            l.unlock();
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(result);
        System.exit(0);
    }
}
