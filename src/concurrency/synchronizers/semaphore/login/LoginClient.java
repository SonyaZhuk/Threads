package concurrency.synchronizers.semaphore.login;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LoginClient {
    public static void main(String[] args) {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueue loginQueue = new LoginQueue(slots);
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();

        System.out.println(loginQueue.availableSlots());
        System.out.println(loginQueue.tryLogin());
    }
}
