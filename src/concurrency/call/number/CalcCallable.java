package concurrency.call.number;

import java.util.Random;
import java.util.concurrent.Callable;

public class CalcCallable implements Callable<Number> {

    @Override
    public Number call() {
        Number res = new Random().nextGaussian();
        // имитация вычислений
        return res;
    }
}