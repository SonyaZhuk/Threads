package concurrency.locks;

import java.util.concurrent.TimeUnit;

/**
 * Lock <- ReentrantLock, ReadLock, WriteLock, ReadWriteLock, ReentrantReadWriteLock
 * <p>
 * ReentrantLock allow threads to enter into lock on a resource more than once.
 * ReadWriteLock - a lot of reading operations, less writing.
 * ReentrantReadWriteLock - a lot of reading operations, a sole writing.
 * ReadLock - once the lock is granted no other thread will be allowed to write.
 * WriteLock - once the lock is granted, no other thread will be allowed to read or write.
 * <p>
 * Пусть необходим нереляционный способ сохранения информации в коллекции, когда неделимым квантом информации считается
 * пара или более следующих друг за другом элементов. То есть добавление и удаление элементов может
 * осуществляться только парами и другой поток не может добавить/удалить свои элементы, пока заблокировавший коллекцию
 * поток полностью не выполнит свои действия.
 * <p>
 * Класс Condition предназначен для управления блокировкой. Ссылку на экземпляр можно получить только из объекта
 * типа Lock методом newCondition(). Расширение возможностей происходит за счет методов await() и signal(),
 * функциональность которых подобна действию методов wait() и notify() класса Object.
 */
public class SynchroClient {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            DoubleResource resource = new DoubleResource();
            new ResThread("a", resource).start();
            new ResThread("Z", resource).start();
            new ResThread("#", resource).start();
            TimeUnit.MILLISECONDS.sleep(1_000);
            System.out.println(resource);
        }
    }
}
