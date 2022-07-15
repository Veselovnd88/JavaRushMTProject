package ThreadTaskConsProd;

public class Consumer implements Runnable{
    private MyQueue myQueue;


    public Consumer(MyQueue myQueue){
        this.myQueue = myQueue;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            myQueue.get();
        }
        System.out.println(this.toString()+" остановился");
    }
}
