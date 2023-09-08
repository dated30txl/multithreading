# CountDownLatch 
- [ docs.oracle.com ](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/CountDownLatch.html)


- [ ] CountDownLatch - це синхронізаційна допомога у Java, яка дозволяє одному або декільком потокам чекати, поки інші потоки не завершать певні операції. Він ініціалізується певним числом (лічильником), яке зменшується при виклику методу countDown(). Коли лічильник доходить до нуля, всі чекаючі потоки продовжують виконання.
<p align="center">
  <img src="./countdownlatch.gif" alt="CountDownLatch">
</p>

