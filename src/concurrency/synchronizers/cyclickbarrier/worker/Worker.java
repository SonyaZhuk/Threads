package concurrency.synchronizers.cyclickbarrier.worker;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
    private final CyclicBarrier barrier;

    Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println("Ready to start.");
            barrier.await();

            doWork();

            barrier.await(); // Use the Same CyclicBarrier Multiple Times
            System.out.println("Done.");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted.");
        } catch (BrokenBarrierException ex) {
            System.out.println("Broken barrier.");
        }
    }

    public void doWork() {
        System.out.println("Doing work.");
    }
}
