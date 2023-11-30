package concurrency.executors.forkjoinpool.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * The Fork-Join breaks the task at hand into sub-tasks until the mini-task is simple enough
 * to solve it without further breakups. It’s like a divide-and-conquer algorithm.
 * One crucial concept in this framework is that no worker thread is idle.
 * They implement a work-stealing algorithm in that idle workers steal the work from those workers who are busy.
 * <p>
 * Для рекурсивного решения задач, алгоритм "разделяй и влавствуй" & map-reduce
 */
public class SumTaskForkJoinPool {

    public static void main(String[] args) {

        //By default, it creates a pool of size that equals
        // the number of available processors obtained using the given technique.
        var numberOfProcessors = Runtime.getRuntime().availableProcessors();

        //ForkJoinPool with the specified parallelism level
        ForkJoinPool forkJoinPool = new ForkJoinPool(numberOfProcessors);

        // submit tasks to be executed by the pool
        ForkJoinTask<Integer> task =
                forkJoinPool.submit(() -> {
                    int result = 0;
                    for (int i = 1; i <= 10; i++) {
                        result += i;
                    }
                    return result;
                });

        try {
            System.out.println("Result: " + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        forkJoinPool.shutdown();
    }
}
