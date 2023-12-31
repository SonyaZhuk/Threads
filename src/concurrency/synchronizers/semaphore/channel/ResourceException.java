package concurrency.synchronizers.semaphore.channel;

/**
 * Исключение, информирующее о сбое в поставке ресурса.
 */
public class ResourceException extends Exception {

    public ResourceException() {
        super();
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }
}
