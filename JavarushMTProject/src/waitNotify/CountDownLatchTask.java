package waitNotify;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTask{
    private final Object lock = new Object();
    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
        synchronized (lock) {
            while (isWaitingForValue) {
                lock.wait();
            }

            retrieveValue();

            isWaitingForValue = false;
            lock.notify();
        }
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {

    }

}
