package example1;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<String> name = Arrays.asList("Kamren","Elwyn", "Ervin", "Greak", "Glena");

        System.out.println("\nStream:\n");
        name
                .stream()
                .filter(p->p.length() == 5)
                .forEach(System.out::println);

        System.out.println("\nParallel stream:\n");
        name
                .parallelStream()
                .filter(p->p.length() == 5)
                .forEach(System.out::println);
    }
}
