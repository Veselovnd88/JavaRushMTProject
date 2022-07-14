package ThreadTaskConsProd;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyQueue<T> {

    Queue<T> queue = new ArrayDeque<>();
    public synchronized T get(){
        while (queue.isEmpty()){//если пустая очередь то ждем  пока не появится
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        T got = queue.poll();
        System.out.println(Thread.currentThread().getName()+" Получил "+got);
        notify();
        return got;
    }
    public  void put(T n){
        synchronized (this){
        while (queue.size()>10){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(n);
        System.out.println("Положили "+n);

        notify(); }
    }


}
