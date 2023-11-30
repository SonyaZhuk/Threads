package concurrency.executors.forkjoinpool.folder;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FolderClient {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor folder1 = new FolderProcessor("C:\\...", "txt");
        FolderProcessor folder2 = new FolderProcessor("C:\\...", "txt");
        FolderProcessor folder3 = new FolderProcessor("C:\\...", "txt");

        pool.execute(folder1);
        pool.execute(folder2);
        pool.execute(folder3);

        //Write to the console information about the status of the pool every second
        //until the three tasks have finished their execution.
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",
                    pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!folder1.isDone()) || (!folder2.isDone()) || (!folder3.isDone()));

        pool.shutdown();

        List<String> results;
        results = folder1.join();
        System.out.printf("DAILY: %d files found.\n", results.size());
        results = folder2.join();
        System.out.printf("OTHER: %d files found.\n", results.size());
        results = folder3.join();
        System.out.printf("TRAIN: %d files found.\n", results.size());
    }
}
