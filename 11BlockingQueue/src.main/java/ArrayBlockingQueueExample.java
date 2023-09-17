import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) {

        // Паркінг може вмістити лише 3 автомобілі.
        BlockingQueue<String> parkingLot = new ArrayBlockingQueue<>(3);

        // Thread для автомобілів, які приїжджають на паркування
        Thread carsArriving = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    parkingLot.put("Car" + i);
                    System.out.println("Car" + i + " припаркувався. Місця, що залишилися: " + (3 - parkingLot.size()));
                    Thread.sleep(500);  // Імітація часу між приїздом автомобілів
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread для автомобілів, які виїжджають із паркування
        Thread carsDeparting = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String car = parkingLot.take();
                    System.out.println(car + " поїхав із паркування. Місця, що залишилися: " + (3 - parkingLot.size()));
                    Thread.sleep(2000);  // Імітація часу, через який автомобілі їдуть
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        carsArriving.start();
        carsDeparting.start();
    }
}
