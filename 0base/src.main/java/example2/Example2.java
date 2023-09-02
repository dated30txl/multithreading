package example2;

public class Example2 {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        // Синхронизация на уровне класса, так как у нас статический контекст
        synchronized (Example2.class) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }

        Thread p = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread q = new Thread(() -> {
            b = 1;
            y = a;
        });

        p.start();
        q.start();
        p.join();
        q.join();

        System.out.println("x=" + x + ", y=" + y);
    }
}
