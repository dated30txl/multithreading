package example1;

public class ThreadLocal0 {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        class MyRunnable implements Runnable {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();

                System.out.printf("%s first threadLocal: %s\n",
                        name,
                        threadLocal.get());

                threadLocal.set(name + " thread value");
                System.out.printf("%s end threadLocal: %s\n",
                        name,
                        threadLocal.get());
            }
        }

        threadLocal.set("From main thread");
        Thread thread1 = new Thread(new MyRunnable(), "first_thread");
        thread1.start();
        Thread thread2 = new Thread(new MyRunnable(), "second_thread");
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.printf("fromMainThread: %s\n",  threadLocal.get());
    }
}


