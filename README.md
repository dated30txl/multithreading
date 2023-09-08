# multithreading
Parallel programming a selection of basic examples in JAVA

- [Base](https://github.com/yourhostel/multithreading/tree/main/0base/src.main/java)
- [ReentrantLock](https://github.com/yourhostel/multithreading/tree/main/1ReentrantLock/src.main/java)
- [Sleep](https://github.com/yourhostel/multithreading/tree/main/2sleep/src.main/java)
- [Synchronized](https://github.com/yourhostel/multithreading/tree/main/3Synchronized/src.main/java)
- [Executor](https://github.com/yourhostel/multithreading/tree/main/4ExecutorService/src.main/java)
- [ThreadLocal](https://github.com/yourhostel/multithreading/tree/main/5ThreadLocal/src.main/java)
- [Semaphore](https://github.com/yourhostel/multithreading/tree/main/6Semaphore)
- [CountDownLatch](https://github.com/yourhostel/multithreading/tree/main/7CountDownLatch)
- [CyclicBarrier](https://github.com/yourhostel/multithreading/tree/main/8CyclicBarrier)
- [Exchanger](https://github.com/yourhostel/multithreading/tree/main/9Exchanger)

### Багатопоточність 
 це здатність програми виконувати безліч операцій одночасно. У Java для реалізації багатопоточності використовуються потоки, які представлені класом `Thread` та інтерфейсом `Runnable`.
- Створення потоку через наслідування від `Thread`:
```
class MyThread extends Thread {
    public void run() {
        // код, який буде виконуватися в потоці
    }
}

MyThread thread = new MyThread();
thread.start();
```
- Використання лямбда-виразу або анонімного класу:
```
Thread thread = new Thread(() -> {
    // код, який буде виконуватися в потоці
});
thread.start();

```
- Створення потоку через реалізацію інтерфейсу `Runnable`:
```
class MyRunnable implements Runnable {
    public void run() {
        // код, який буде виконуватися в потоці
    }
}
Thread thread = new Thread(new MyRunnable());
```
- `Callable<V>` і `Future<V>` абстракція для потоків в Java, яка дозволяє нам не просто виконувати код в окремому потоці, але й отримувати результат його виконання:
```
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // код, який буде виконуватися в потоці
        return 123;  // Припустимо, ми повертаємо яке-небудь числове значення
    }
}
```
- Аби виконати Callable, потрібно скористатися `ExecutorService`:
```
ExecutorService executor = Executors.newSingleThreadExecutor();

Future<Integer> future = executor.submit(new MyCallable());

try {
    Integer result = future.get();  // чекаємо результат
    System.out.println(result);
} catch (ExecutionException | InterruptedException e) {
    e.printStackTrace();
}

executor.shutdown();
```

### Синхронізація: 
Якщо декілька потоків працюють із спільними даними, можливі конфлікти. Щоб уникнути цього, Java пропонує ключове слово `synchronized`.

### Взаємодія потоків: 
Методи `wait()`, `notify()` та `notifyAll()` використовуються для координації роботи між потоками.

### Високорівневі конструкції для багатопоточності: 
Пакет `java.util.concurrent` містить багато корисних утиліт, таких як `CountDownLatch`, `CyclicBarrier`, `Semaphore`, блокуючі черги та інше.

### ThreadPool: 
Замість створення нового потоку для кожного завдання, можна використовувати пул потоків, який ефективно управляє обмеженою кількістю потоків для виконання великої кількості завдань.