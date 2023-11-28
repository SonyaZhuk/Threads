package concurrency.waitnotify;

/**
 * Interaction wait() и notify()
 * <p>
 * В результате компиляции и запуска при вводе корректного значения для инициализации поля amount будет запущен
 * процесс проведения платежа. Задержки потоков методом sleep() используются для точной демонстрации
 * последовательности действий, выполняемых потоками. Если же в коде приложения убрать все блоки синхронизации,
 * а также вызовы методов wait() и notify(), то результатом вычислений, скорее всего, будет ноль, так как вычисление
 * будет произведено до инициализации полей объекта.
 */
public class PaymentClient {

    public static void main(String[] args) throws InterruptedException {
        final Payment payment = new Payment();

        // call synchronized method
        new Thread(payment::doPayment).start();

        Thread.sleep(200);

        synchronized (payment) { // 1-st block
            System.out.println("Init amount:");
            payment.initAmount();
            payment.notify(); // return blocking
        }
        synchronized (payment) { // 2-d block
            payment.wait(1_000);
            System.out.println("ok");
        }
    }
}
