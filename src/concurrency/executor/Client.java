package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Запуск множества потоков, выполняющих одинаковые задачи.
 * <p>
 * Сначала создается объект класса ExecutorService. После чего вызывается метод execute,
 * которому в качестве параметра необходимо передать объект, описывающий элемент работы,
 * который мы хотим передать исполнителю.
 * <p>
 * Фабричные методы:
 * Executors.newCachedThreadPool(). Данная реализация применяется в тех случаях, когда заранее неизвестно,
 * какое количество потоков будет необходимо исполнителю.
 * Executors.newFixedThreadPool(n). Фабричный метод создания пула с n потоками.
 * Executors.newScheduledThreadPool(count). Фабричный метод создания пула с delay.
 * <p>
 * Interface Executor implementing Sub-Interfaces: ExecutorService, ScheduledExecutorService
 * Implementing Classes: AbstractExecutorService, ForkJoinPool, ScheduledThreadPoolExecutor, ThreadPoolExecutor
 */
public class Client {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new Counter());
        ex.execute(new Counter());
        ex.shutdown();
    }
}

