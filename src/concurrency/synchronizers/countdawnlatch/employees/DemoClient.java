package concurrency.synchronizers.countdawnlatch.employees;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch : A synchronization aid that allows one or more threads to wait until
 * a set of operations being performed in other threads completes.
 */
public class DemoClient {

    public static void main(String args[]) throws InterruptedException {

        // Create a task that needs to wait for four threads before it begins
        CountDownLatch latch = new CountDownLatch(4);

        // Let's create four employee threads and begin them.
        Employee first = new Employee(1000, latch, "EMPLOYEE-1");
        Employee second = new Employee(2000, latch, "EMPLOYEE-2");
        Employee third = new Employee(3000, latch, "EMPLOYEE-3");
        Employee fourth = new Employee(4000, latch, "EMPLOYEE-4");


        first.start();
        second.start();
        third.start();
        fourth.start();

        //The main task waits for four threads
        latch.await();

        // Main thread has started
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}
