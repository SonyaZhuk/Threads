package concurrency.synch.method;

import java.io.IOException;

public class SynchroClient {

    public static void main(String[] args) {
        Resource resource = null;
        try {
            resource = new Resource("C:\\result.txt");
            SyncThread t1 = new SyncThread("First", resource);
            SyncThread t2 = new SyncThread("Second", resource);
            t1.start();
            t2.start();

            t1.join();
            t2.join();
        } catch (IOException e) {
            System.err.print("error file: " + e);
        } catch (InterruptedException e) {
            System.err.print("error thread: " + e);
        } finally {
            resource.close();
        }
    }
}
