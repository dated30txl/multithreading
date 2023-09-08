package example2;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 1. Ініціалізація: Створюється об'єкт CountDownLatch із початковим значенням рахівника 8,
 *     що відповідає 5 автомобілям та трьом стартовим командам.
 * 2. Запуск автомобілів: Запускається 5 потоків, де кожен представляє автомобіль.
 *     Коли автомобіль під'їжджає до стартової лінії, рахівник зменшується на 1.
 * 3. Очікування старту: Головний потік чекає, поки всі автомобілі не під'їдуть до стартової лінії.
 * 4. Команди до старту: Після того, як усі автомобілі зайняли свої місця,
 *     головний потік видає команди "На старт!", "Увага!" та "Марш!",
 *     зменшуючи рахівник на 1 після кожної команди.
 * 5. Старт гонки: Як тільки рахівник досягає нуля, всі потоки (автомобілі) одночасно починають гонку.
 * 6. Фініш: Кожен автомобіль "їде" по трасі, а потім повідомляє про свій фініш.
 */

public class Race {
    private static final int NUM_CARS = 5;
    private static final CountDownLatch START = new CountDownLatch(NUM_CARS + 3);
    private static final int TRACK_LENGTH = 500000;

    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1, NUM_CARS)
                .mapToObj(i -> new Thread(new Car(i, (int) (Math.random() * 100 + 50))))
                .forEach(Thread::start);

        while (START.getCount() > 3) {
            Thread.sleep(100);
        }

        giveCommand("На старт!");
        giveCommand("Увага!");
        giveCommand("Руш!");
    }

    private static void giveCommand(String command) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(command);
        START.countDown();
    }

    public static class Car implements Runnable {
        private final int carNumber;
        private final int carSpeed;

        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобіль №%d під'їхав до стартової прямої.\n", carNumber);
                START.countDown();
                START.await();
                Thread.sleep(TRACK_LENGTH / carSpeed);
                System.out.printf("Автомобіль №%d фінішував!\n", carNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

