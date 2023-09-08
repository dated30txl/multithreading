package example2;

import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * У нас є автобус, який рухається по остановках.
 * Пасажири на остановках сідають у автобус та виходять на потрібних їм остановках.
 * Є 7 фаз (остановок). Фази 0 та 6 - це автобусний парк, а 1-5 - остановки.
 * Пасажири, що чекають автобус на остановках,
 * можуть виходити на наступних остановках або на конечній.
 * Кожна остановка (фаза) симулює прибуття автобуса, де пасажири сідають або виходять.
 * Кожен пасажир - окремий потік, який координує свою діяльність через Phaser.
 * Головний потік (автобус) також синхронізує свою роботу через цей же Phaser.
 */

public class Bus {
    // Ініціалізація Phaser з одним учасником (головний потік)
    private static final Phaser PHASER = new Phaser(1);

    public static void main(String[] args) throws InterruptedException {

        // Створюємо список пасажирів на випадкових остановках
        List<Passenger> passengers = IntStream.range(1, 5)
                .boxed()
                .flatMap(i -> IntStream.range(0, 2).mapToObj(j -> new Passenger(i, (Math.random() > 0.5) ? i + 1 : 5)))
                .toList();

        IntStream.range(0, 7).forEach(i -> {
            switch (i) {
                case 0 -> {
                    System.out.println("Автобус виїхав з парку.");
                    PHASER.arrive();
                }
                case 6 -> {
                    System.out.println("Автобус повернувся в парк.");
                    PHASER.arriveAndDeregister();
                }
                default -> {
                    System.out.println("Остановка № " + i);
                    passengers.stream()
                            .filter(p -> p.departure == i)
                            .forEach(p -> {
                                PHASER.register();
                                new Thread(p).start();
                            });
                    PHASER.arriveAndAwaitAdvance();
                }
            }
        });
    }

    public static class Passenger implements Runnable {
        private final int departure;
        private final int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " чекає на остановці № " + this.departure);
        }

        @Override
        public void run() {
            try {
                System.out.println(this + " сів у автобус.");
                while (PHASER.getPhase() < destination)
                    PHASER.arriveAndAwaitAdvance();
                System.out.println(this + " вийшов з автобуса.");
                PHASER.arriveAndDeregister();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Пасажир{" + departure + " -> " + destination + '}';
        }
    }
}
