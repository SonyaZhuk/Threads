package concurrency.lock.pairs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ресурсы добавляются и удаляются только парами.
 */
public class DoubleResource {

    private Deque<String> list = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition isFree = lock.newCondition();

    public void adding(String str, int i) {
        try {
            lock.lock();
            list.add(i + "<" + str);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            list.add(str + ">" + i);
            isFree.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public String deleting() {
        lock.lock();
        String s = list.poll();
        s += list.poll();
        isFree.signal();
        lock.unlock();
        return s;
    }

    public String toString() {
        return list.toString();
    }
}

