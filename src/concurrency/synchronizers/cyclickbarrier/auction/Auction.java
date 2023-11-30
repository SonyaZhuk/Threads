package concurrency.synchronizers.cyclickbarrier.auction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CyclicBarrier;

/**
 * Define a barrier and action after it's ending.
 */
public class Auction {
    private ArrayList<Bid> bids;
    private CyclicBarrier barrier;
    public final int BIDS_NUMBER = 5;

    public Auction() {
        this.bids = new ArrayList<>();
        this.barrier = new CyclicBarrier(BIDS_NUMBER, () -> {
            Bid winner = Auction.this.defineWinner();
            System.out.println("Bid #" + winner.getBidId() + ", price:" + winner.getPrice() + " win!");
        });
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public boolean add(Bid e) {
        return bids.add(e);
    }

    public Bid defineWinner() {
        return Collections.max(bids, Comparator.comparingInt(Bid::getPrice));
    }
}