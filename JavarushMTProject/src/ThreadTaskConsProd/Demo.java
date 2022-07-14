package ThreadTaskConsProd;

public class Demo {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        new Thread(new Consumer(myQueue)).start();
        new Thread(new Producer(myQueue)).start();
        new Thread(new Consumer(myQueue)).start();
        //и продолжает ждать пока ему не положат еще
    }
}
