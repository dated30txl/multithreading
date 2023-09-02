package example2;

public class Example2 {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        // Синхронізація на рівні класу, тому що у нас статичний контекст
        synchronized (Example2.class) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }
//Ці потоки працюватимуть паралельно і можуть оновлювати змінні 'a', 'b', 'x', та 'y' у будь-якому порядку.
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
