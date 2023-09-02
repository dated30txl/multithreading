package example2;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore semaphore;

    public ParkingLot(int slots) {
        this.semaphore = new Semaphore(slots);
    }

    public void park() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + " припаркувався.");
    }

    public void leave() {
        System.out.println(Thread.currentThread().getName() + " поїхав.");
        semaphore.release();
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5);  // паркінг із 5 місцями

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    parkingLot.park();  // намагаємося припаркуватися
                    Thread.sleep(2000);  // автомобіль стоїть на парковці 2 секунди
                    parkingLot.leave();  // їдемо з паркінгу
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Автомобіль " + (i + 1)).start();
        }
    }
}

