package concurrency.cyclickbarrier.worker;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier : A synchronization aid that allows a set of threads to all wait
 * for each other to reach a common barrier point.
 * <p>
 * CyclicBarrier allows threads to wait for other threads at a barrier point.
 * It serves a similar purpose as a CountDownLatch but unlike a CountDownLatch,
 * we can use it multiple times.
 * <p>
 * A CyclicBarrier is initialized with a count representing the number of attending parties/threads.
 * When a thread invokes the await() method, it blocks until all other threads invoke await().
 * When this happens, the barrier is reset and the threads are released.
 * <p>
 * Another important feature of CyclicBarrier is that we can supply a barrier action.
 * It runs after all threads reach the barrier point but before they're allowed to continue.
 * <p>
 * Demo for using the Same CyclicBarrier Multiple Times
 */
public class DemoClient {

    public static void main(String[] args) throws InterruptedException {
        final int threadCount = 3;
        final ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        final CyclicBarrier start = new CyclicBarrier(threadCount,
                () -> System.out.println("All ready to continue!"));

        for (int i = 0; i < threadCount; ++i) {
            threadPool.execute(new Worker(start));
        }

        TimeUnit.SECONDS.sleep(1); // Wait for some time

        threadPool.shutdown();
    }
}
