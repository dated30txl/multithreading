package example1;

import java.util.concurrent.Semaphore;

public class Semaphore1 {
    public static void main(String[] args) {
        Semaphore table = new Semaphore(2);

        Person person0 = new Person(table);
        Person person1 = new Person(table);
        Person person2 = new Person(table);
        Person person3 = new Person(table);
        Person person4 = new Person(table);

        person0.start();
        person1.start();
        person2.start();
        person3.start();
        person4.start();

    }

}
class Person extends Thread{
    private final Semaphore table;

    public Person(Semaphore table) {
        this.table = table;
    }

    @Override
    public void run(){
        System.out.println(this.getName() + " Чекає на стіл");
        try {
            table.acquire();
            System.out.println(this.getName() + " їсть за столом");
            System.out.println(this.getName() + " звільнив стіл");
            table.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}