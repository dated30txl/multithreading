package example2;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * Паром може переправляти максимум три автомобілі одночасно.
 * Автомобілі під'їжджають до паромної переправи та чекають,
 * коли набереться трійка для переправи. Після переправи всі
 * автомобілі продовжують рух.
 */
public class Ferry {
    // Ініціалізуємо бар'єр на три потоки та завданням, яке буде виконуватися при досягненні бар'єра
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());

    public static void main(String[] args) {
        IntStream.range(0, 9) // Створюємо поток чисел від 0 до 8
                .forEach(i -> {
                    new Thread(new Car(i)).start();
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    // Завдання, яке буде виконуватися при досягненні бар'єра усіма сторонами
    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Паром переправив автомобілі!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Сторони, які будуть досягати бар'єра
    public static class Car implements Runnable {
        private final int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобіль №%d під'їхав до паромної переправи.\n", carNumber);
                BARRIER.await();  // Сигналізуємо про досягнення бар'єра
                System.out.printf("Автомобіль №%d продовжив рух.\n", carNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

