package concurrency.synchronizers.countdawnlatch.employees;


import java.util.concurrent.CountDownLatch;

/**
 * A class to represent threads for which the main thread waits.
 */
class Employee extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Employee(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
