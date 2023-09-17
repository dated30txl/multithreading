import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println("Waiting for data...");
                System.out.println(queue.take());  // Waiting for data to be available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);  // Simulating some work
                queue.put("Data");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
