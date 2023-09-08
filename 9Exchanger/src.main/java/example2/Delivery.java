package example2;

import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

/**
 * Ми маємо два грузовика, які виїжджають із своїх пунктів відправлення.
 * Кожен грузовик має дві посилки: одна для його кінцевого пункту призначення, інша для обміну.
 * При доїзді до пункту E відбувається обмін посилками між грузовиками через EXCHANGER.
 * Після обміну грузовики продовжують рух до своїх пунктів призначення.
 */
public class Delivery {
    // Створюємо обмінник для рядків
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {
        String[][] parcels = {
                {"{посилка A->D}", "{посилка A->C}"},
                {"{посилка B->C}", "{посилка B->D}"}
        };

        IntStream.range(0, 2).forEach(i -> {
            new Thread(new Truck(i + 1, i == 0 ? "A" : "B", i == 0 ? "D" : "C", parcels[i])).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static class Truck implements Runnable {
        private final int number;
        private final String dep;
        private final String dest;
        private final String[] parcels;

        public Truck(int number, String departure, String destination, String[] parcels) {
            this.number = number;
            this.dep = departure;
            this.dest = destination;
            this.parcels = parcels;
        }

        @Override
        public void run() {
            try {
                System.out.printf("В грузовик №%d погрузили: %s і %s.\n", number, parcels[0], parcels[1]);
                System.out.printf("Грузовик №%d виїхав з пункту %s в пункт %s.\n", number, dep, dest);
                Thread.sleep(1000 + (long) (Math.random() * 5000));
                System.out.printf("Грузовик №%d приїхав в пункт Е.\n", number);

                // При виклику exchange() потік блокується і чекає, доки інший потік викличе exchange()
                parcels[1] = EXCHANGER.exchange(parcels[1]);
                System.out.printf("В грузовик №%d перемістили посилку для пункту %s.\n", number, dest);

                Thread.sleep(1000 + (long) (Math.random() * 5000));
                System.out.printf("Грузовик №%d приїхав в %s та доставив: %s і %s.\n", number, dest, parcels[0], parcels[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

