package concurrency.call.number;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс ExecutorService методом execute(Runnable thread) запускает традиционные потоки, метод же submit(Callable<T> task)
 * запускает потоки с возвращаемым значением. Метод shutdown() останавливает все запущенные им ранее потоки и прекращает
 * действие самого исполнителя. Статические методы newSingleThreadExecutor() и newFixedThreadPool(int numThreads)
 * класса Executors определяют правила, по которым работает ExecutorService, а именно первый позволяет исполнителю
 * запускать только один поток, второй — не более чем указано в параметре numThreads, ставя другие потоки в
 * очередь ожидания окончания уже запущенных потоков.
 */
public class CalcClient {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Number> future = es.submit(new CalcCallable());
        es.shutdown();
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
