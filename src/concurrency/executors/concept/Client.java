package concurrency.executors.concept;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Run a pool thread, executing async tasks.
 * <p>
 * Interface Executor implementing Sub-Interfaces: ExecutorService, ScheduledExecutorService
 * Executor <- ExecutorService <- ScheduledExecutorService
 * <p>
 * Implementing Classes:
 * 1) Run runnable and callable tasks: submit(Callable, Runnable)
 * ExecutorService -> AbstractExecutorService, ForkJoinPool, ThreadPoolExecutor, ExecutorCompletionService
 * 2) Run tasks after delayed periodically:
 * ScheduledExecutorService -> ScheduledThreadPoolExecutor, DelegatedScheduledExecutorService
 * <p>
 * Factory methods:
 * Executors.newCachedThreadPool(). apply in cases we don't know the certain threads
 * Executors.newFixedThreadPool(n). create a pool with n threads.
 * Executors.newScheduledThreadPool(count). create a pool with delay.
 */
public class Client {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new Counter());
        ex.execute(new Counter());
        ex.shutdown();
    }
}

