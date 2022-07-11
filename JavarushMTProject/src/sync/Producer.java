package sync;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<String> queue;// блокирующая очередь

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;// инициализированили
    }

    public void run() {
        try {
            int i = 0;
            while (true) {
                queue.put(String.valueOf(i++));//кладем увеличенное значение i.//метод пут - кладет в очередь элемент если есть место, если нет - то ждет
                Thread.sleep(300);//спим 3 минуты
            }
        } catch (InterruptedException e) {//если интеррапт = выводит сообщение
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
