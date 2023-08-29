package example1;

public class ClassThread {
    /**
     * Для кожного потоку створюється свій власний стек у пам'яті,
     * куди поміщаються всі локальні змінні та ряд інших даних,
     * пов'язаних із виконанням потоку. Відповідно,
     * що більше потоків створюється, то більше пам'яті використовується.
     */
    public static void main(String[] args) {
        Thread t = Thread.currentThread(); // отримуємо головний потік

        System.out.printf("Name: %s\n", t.getName()); // main
        t.setName("thread-1");
        System.out.printf("New name: %s\n", t.getName()); //thread-1

        System.out.printf("Priority: %s\n", t.getPriority());
        t.setPriority(1);
        System.out.printf("New priority: %s\n", t.getPriority()); //1
        System.out.printf("Активний(isAlive)?: %s\n", t.isAlive()); // true потік активний
        System.out.printf("Перервано?(isInterrupted)?: %s\n", t.isInterrupted());//false потік не перервано

        System.out.println("thread-1 finished...");

    }
}
