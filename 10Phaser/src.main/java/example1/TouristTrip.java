package example1;

import java.util.concurrent.Phaser;

/**
 * Група туристів (потоки) відвідують ряд пам'яток (фази).
 * В кожному місці вони роблять фотографії, і всі мають зібратися разом,
 * перш ніж вони переходять до наступної пам'ятки.
 * Створюємо 5 потоків туристів, які відвідують три пам'ятки.
 * Кожен турист зробить фото на пам'ятці та дочекається,
 * поки всі інші зроблять те саме, перш ніж вони перейдуть
 * до наступної пам'ятки.
 */
public class TouristTrip {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // Головний потік реєструється
        int currentPhase;

        System.out.println("Туристи вирушають у подорож!");

        // Створення 5 потоків туристів
        new Thread(new Tourist(phaser, "Турист 1")).start();
        new Thread(new Tourist(phaser, "Турист 2")).start();
        new Thread(new Tourist(phaser, "Турист 3")).start();
        new Thread(new Tourist(phaser, "Турист 4")).start();
        new Thread(new Tourist(phaser, "Турист 5")).start();

        // Очікування завершення всіх фаз
        for (int i = 0; i < 3; i++) {
            currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            System.out.println("Фаза " + currentPhase + " завершена!");
        }

        phaser.arriveAndDeregister(); // Головний потік виходить з реєстрації
    }

    static class Tourist implements Runnable {
        private final Phaser phaser;
        private final String name;

        Tourist(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
            this.phaser.register();
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(name + " відвідав пам'ятку " + (i + 1));
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister(); // Потік виходить з реєстрації після завершення всіх фаз
        }
    }
}

