package concurrency.call.product;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class BaseCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        String product = ProductList.getProduct();

        String result = (product != null) ?
                product + " done" : "productList is empty";

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(result);
        return result;
    }
}

