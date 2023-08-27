package example1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MaxMinMethodsTest {
    private static final int SIZE = 1_000_000;
    @Test
    public void testMaxMinMethods() {
        // Генеруємо великий список випадкових чисел
        List<Integer> numbers = new ArrayList<>(SIZE);
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            numbers.add(random.nextInt());
        }

        // Вимірюємо час виконання для Finder
        long startTime1 = System.nanoTime();
        Finder.max(numbers);
        Finder.min(numbers);
        double finderTime = (System.nanoTime() - startTime1) / 1_000_000_000.0;

        // Вимірюємо час виконання для Collections
        long startTime2 = System.nanoTime();
        Collections.max(numbers);
        Collections.min(numbers);
        double collectionsTime = (System.nanoTime() - startTime2) / 1_000_000_000.0;

        System.out.printf("Finder: %.3f sec%n", finderTime);
        System.out.printf("Collections: %.3f sec%n", collectionsTime);
    }
}
