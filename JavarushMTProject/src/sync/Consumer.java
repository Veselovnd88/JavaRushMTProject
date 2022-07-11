package sync;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
             while (true) {

			try {
				String str = queue.take();
				               System.out.println(str);// метод take - забирает элемент если он есть, если элемента нет = ждет пока не появится
			} catch (InterruptedException e) {
				System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
			}

            }
   
    }
}
