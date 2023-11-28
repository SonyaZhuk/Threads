package concurrency.synch.block;


/**
 * Блокировка объекта потоком.
 * Один из потоков блокирует объект, и до тех пор, пока он не закончит выполнение блока синхронизации,
 * в котором производится изменение значения объекта, ни один другой поток не может вызвать синхронизированный
 * блок для этого объекта.
 * Если в коде убрать синхронизацию объекта StringBuilder, то вывод будет другим, так как другой поток сможет
 * получить доступ к объекту и изменить его раньше, чем первый закончит выполнение цикла.
 */
public class TwoThread {
    private static int counter = 0;

    public static void main(String args[]) {

        final StringBuilder builder = new StringBuilder();

        new Thread(() -> {
            synchronized (builder) {
                do {
                    builder.append("A");
                    System.out.println(builder);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.err.print(e);
                    }
                } while (TwoThread.counter++ < 2);
            }
        }).start();

        new Thread(() -> {
            synchronized (builder) {
                while (TwoThread.counter++ < 6) {
                    builder.append("B");
                    System.out.println(builder);
                }
            }
        }).start();
    }
}
