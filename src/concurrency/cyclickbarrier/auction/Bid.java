package concurrency.cyclickbarrier.auction;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Класс Bid определяет предложение клиента на аукционе и запрашивает барьер,
 * после которого клиент либо заплатит за лот, либо будет продолжать работать дальше.
 */
public class Bid extends Thread {
    private Integer bidId;
    private int price;
    private CyclicBarrier barrier;

    public Bid(int id, int price, CyclicBarrier barrier) {
        this.bidId = id;
        this.price = price;
        this.barrier = barrier;
    }

    public Integer getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + this.bidId + " specifies a price.");
            Thread.sleep(new Random().nextInt(3000)); // time to think
            int delta = new Random().nextInt(50); // define the level of price increasing
            price += delta;
            System.out.println("Bid " + this.bidId + " : " + price);
            this.barrier.await(); // await from barrier
            System.out.println("Continue to work..."); // check who is winner and pay in winner case
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}