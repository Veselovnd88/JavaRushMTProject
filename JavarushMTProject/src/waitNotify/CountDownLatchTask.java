package waitNotify;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTask{

    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {


            latch.await();


            retrieveValue();


            latch.countDown();
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTask cdlt = new CountDownLatchTask();
        cdlt.someMethod();
    }

}
