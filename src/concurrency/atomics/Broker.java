package concurrency.atomics;


/**
 * Класс Broker, запрашивающий значение поля index с некоторым интервалом в миллисекундах.
 */
public class Broker extends Thread {
    private static final int PAUSE = 500;// in millis
    private Market market;

    public Broker(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Current index: " + market.getIndex());
                Thread.sleep(PAUSE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
