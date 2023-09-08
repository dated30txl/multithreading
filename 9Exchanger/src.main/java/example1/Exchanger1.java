package example1;

import java.util.concurrent.Exchanger;

public class Exchanger1 {
    /**
     * Розглянемо ситуацію, де два потоки, "Продюсер" і "Консюмер",
     * обмінюються рядками за допомогою Exchanger. Продюсер генерує рядок,
     * а Консюмер отримує цей рядок та виводить його.
     */
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {

        // Потік "Продюсер" генерує рядок
        new Thread(() -> {
            try {
                String generatedString = "Дані від Продюсера!";
                // Обмін даними
                EXCHANGER.exchange(generatedString);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Потік "Консюмер" отримує рядок
        new Thread(() -> {
            try {
                // Отримання даних від "Продюсера"
                String receivedString = EXCHANGER.exchange(null);
                System.out.println("Консюмер отримав: " + receivedString);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

