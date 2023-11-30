package concurrency.synchronizers.phaser.animal;

import java.util.concurrent.Phaser;

public class AnimalThread implements Runnable {
    private Phaser phaser;
    private String threadName;

    AnimalThread(Phaser phaser, String threadName) {
        this.phaser = phaser;
        this.threadName = threadName;
        this.phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        // phase 1 of our code.
        System.out.println("This is Phase one for : " + this.threadName);

        // creating a phaser barrier for all threads to sync
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(99);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // start new phase of execution, phase 2 of code
        System.out.println("This is Phase two for : " + this.threadName);

        // creating a barrier for all threads to sync
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(99);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // start new phase of execution, phase 3 of code
        System.out.println("This is Phase three for : " + this.threadName);

        phaser.arriveAndDeregister();
    }
}
