package concurrency.atomics;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Торговая площадка
 * Атомарность поля обеспечивает получение экземплярами класса Broker идентичных текущих значений поля index.
 */
public class AtomicClient {
    private static final int NUMBER_BROKERS = 30;

    public static void main(String[] args) {
        Market market = new Market(new AtomicLong(100));
        market.start();
        for (int i = 0; i < AtomicClient.NUMBER_BROKERS; i++) {
            new Broker(market).start();
        }
    }
}
