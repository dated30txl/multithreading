package example1;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {

    private static final int NUMBER_OF_TASKS = 3;
    private static final CountDownLatch LATCH = new CountDownLatch(NUMBER_OF_TASKS);

    public static void main(String[] args) throws InterruptedException {

        // Запуск трьох завдань у окремих потоках
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            final int taskNumber = i + 1;
            new Thread(() -> {
                try {
                    performTask(taskNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LATCH.countDown(); // Зменшуємо лічильник на 1
                }
            }).start();
        }

        // Головний потік чекає на завершення всіх трьох завдань
        LATCH.await();

        System.out.println("Усі завдання виконані!");
    }

    private static void performTask(int taskNumber) throws InterruptedException {
        // Імітація роботи
        Thread.sleep((long) (Math.random() * 2000));
        System.out.println("Завдання " + taskNumber + " виконано.");
    }
}

