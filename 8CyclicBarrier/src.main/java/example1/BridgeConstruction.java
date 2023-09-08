package example1;

import java.util.concurrent.CyclicBarrier;

public class BridgeConstruction {
    /**
     * Уявімо, що ми хочемо створити систему, де три робітники будують частини мосту.
     * Кожен робітник будує свою частину мосту, але вони повинні дочекатися один одного,
     * щоб почати роботу над наступною частиною разом.
     */
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Частина мосту готова! Починаємо наступний етап...");
        });

        new Thread(new Worker("Робітник 1", barrier)).start();
        new Thread(new Worker("Робітник 2", barrier)).start();
        new Thread(new Worker("Робітник 3", barrier)).start();
    }

    static class Worker implements Runnable {
        private final String name;
        private final CyclicBarrier barrier;

        Worker(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println(name + " готовить частину мосту...");
                    Thread.sleep((int) (Math.random() * 1000));
                    System.out.println(name + " готовий до наступного етапу!");
                    barrier.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

