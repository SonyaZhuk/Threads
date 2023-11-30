package concurrency.synchronizers.semaphore.channel;

import java.util.LinkedList;

/**
 * A semaphore controls access to a shared resource through the use of a counter.
 * If the counter is greater than zero, then access is allowed. If it is zero, then access is denied.
 * <p>
 * Задача о пуле ресурсов с ограниченным числом аудиоканалов, и значительно бо́льшим числом клиентов,
 * желающих воспользоваться одним из каналов. Каждый клиент получает доступ к каналу, причем пользоваться
 * можно только одним каналом. Если все каналы заняты, то клиент ждет в течение заданного интервала времени.
 * Если лимит ожидания превышен, генерируется исключение и клиент уходит, так и не воспользовавшись услугами пула.
 */
public class Runner {
    public static void main(String[] args) {
        LinkedList<AudioChannel> list = new LinkedList<>() {
            {
                this.add(new AudioChannel(771));
                this.add(new AudioChannel(883));
                this.add(new AudioChannel(550));
                this.add(new AudioChannel(337));
                this.add(new AudioChannel(442));
            }
        };

        ChannelPool<AudioChannel> pool = new ChannelPool<>(list);
        for (int i = 0; i < 20; i++) {
            new Client(pool).start();
        }
    }
}