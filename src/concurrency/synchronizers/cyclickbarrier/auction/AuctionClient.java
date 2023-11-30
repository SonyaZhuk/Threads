package concurrency.synchronizers.cyclickbarrier.auction;

import java.util.Random;

/**
 * Процесс проведения аукциона подразумевает корректное использование класса CyclicBarrier.
 * Класс Auction определяет список конкурирующих предложений от клиентов и размер барьера.
 * Чтобы приложение работало корректно, необходимо, чтобы размер списка совпадал со значением константы BIDS_NUMBER.
 * Барьер инициализируется потоком определения победителя торгов, который запустится после того, как все предложения
 * будут объявлены. Если потоков будет запущено больше чем размер барьера, то «лишние» предложения могут быть не учтены
 * при вычислении победителя, если же потоков будет меньше, то приложение окажется в состоянии deadlock.
 * Для предотвращения подобных ситуаций следует использовать метод await() с параметрами.
 */
public class AuctionClient {

    public static void main(String[] args) {
        Auction auction = new Auction();
        int startPrice = new Random().nextInt(100);
        for (int i = 0; i < auction.BIDS_NUMBER; i++) {
            Bid thread = new Bid(i, startPrice, auction.getBarrier());
            auction.add(thread);
            thread.start();
        }
    }
}
