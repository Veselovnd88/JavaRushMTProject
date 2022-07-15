package ThreadTaskConsProd;

public class Demo {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Thread th1 = new Thread(new Consumer(myQueue));
        Thread th2 = new Thread(new Producer(myQueue));
        Thread th3 = new  Thread(new Consumer(myQueue));


        th1.start();
        th2.start();
        th3.start();
        //
    }
}
