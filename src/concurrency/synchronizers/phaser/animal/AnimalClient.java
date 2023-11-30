package concurrency.synchronizers.phaser.animal;

import java.util.concurrent.Phaser;

public class AnimalClient {

    public static void main(String[] args) {

        Phaser phaser = new Phaser();
        phaser.register();

        System.out.println("let's start phaser example");

        AnimalThread cat = new AnimalThread(phaser, "cat");
        AnimalThread dog = new AnimalThread(phaser, "dog");
        AnimalThread elephant = new AnimalThread(phaser, "elephant");

        phaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase one");

        phaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase two");

        phaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase three");
    }
}
