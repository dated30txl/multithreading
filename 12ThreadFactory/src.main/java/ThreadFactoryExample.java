import java.util.concurrent.ThreadFactory;

/**
 * ThreadFactory це інтерфейс, який надає метод створення нових потоків.
 * Основна перевага використання ThreadFactory полягає у можливості
 * налаштування створення потоків, наприклад,
 * задавати імена потокам або робити їх демонами.
 */

public class ThreadFactoryExample {


    public static void main(String[] args) throws InterruptedException {
        // Створюємо свою фабрику потоків
        ThreadFactory customThreadFactory = new CustomThreadFactory("CustomThread");

        // Використовуємо фабрику для створення потоків
        Thread thread1 = customThreadFactory.newThread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is processing item: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = customThreadFactory.newThread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is processing item: " + i);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        // Очікуємо завершення потоків
        thread1.join();
        thread2.join();

        System.out.println("Both threads have finished processing.");
    }
}

class CustomThreadFactory implements ThreadFactory {
    private int threadCount = 1;
    private final String prefix;

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, prefix + "-" + threadCount++);
        thread.setDaemon(true);  // Робимо потік демоном
        return thread;
    }
}

