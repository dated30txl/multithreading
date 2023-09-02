package example2;

public class Example2 {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static String executeThreadsAndGetResult() throws InterruptedException {
        synchronized (Example2.class) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }

        /* Ці потоки працюватимуть паралельно і можуть оновлювати змінні 'a', 'b', 'x', та 'y' у будь-якому порядку.
         *
         * Коли обидва потоки завершать свою роботу та виводиться результат, можливі наступні варіанти значень для x та y:
         *
         * x=0, y=0: якщо обидва потоки виконали запис 'a' і 'b' до того, як прочитали значення для 'x' і 'y'.
         * x=1, y=0: якщо потік 'p' прочитав значення 'b' після того, як потік 'q' записав 1 у 'b', але до того, як потік 'q' прочитав значення 'a'.
         * x=0, y=1: якщо потік 'q' прочитав значення 'a' після того, як потік 'p' записав 1 у 'a', але до того, як потік 'p' прочитав значення 'b'.
         * x=1, y=1: якщо обидва потоки прочитали значення після того, як були виконані відповідні записи.
         */

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

        return "x=" + x + ", y=" + y;
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(executeThreadsAndGetResult());
    }
}
