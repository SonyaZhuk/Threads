package concurrency.locks;

import java.util.Random;

public class ResThread extends Thread {

    private DoubleResource resource;

    public ResThread(String name, DoubleResource rs) {
        super(name);
        resource = rs;
    }

    public void run() {
        for (int i = 1; i < 4; i++) {
            if (new Random().nextInt(2) > 0) {
                resource.adding(getName(), i);
            } else {
                resource.deleting();
            }
        }
    }
}
