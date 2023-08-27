package example1;

public class CreateThread {
    /**
     * Головний потік завершує роботу раніше, ніж породжені ним дочірні
     */
    public static void main(String[] args) {
        System.out.println("Main thread started...");
        for(int i=1; i < 3; i++)
            new ChildThread("ChildThread " + i).start();
        System.out.println("Main thread finished...");
    }
    static class ChildThread extends Thread {

        ChildThread(String name){
            super(name);
        }

        public void run(){

            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
            System.out.printf("%s fiished... \n", Thread.currentThread().getName());
        }
    }
}
