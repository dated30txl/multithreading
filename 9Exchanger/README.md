# Exchanger<V> 
- [ docs.oracle.com ](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/Exchanger.html)


- Це синхронізаційний інструмент, що дозволяє двом потокам безпечно обмінюватися даними.
- При використанні exchange(V x) один потік блокується до тих пір, поки інший потік не викличе цей же метод, після чого обмін відбувається.
- Часто використовується в сценаріях обміну ресурсами між потоками, наприклад, коли один потік генерує дані, а інший їх обробляє.

<p align="center">
  <img src="./exchanger.gif" alt="CountDownLatch">
</p>