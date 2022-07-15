package ThreadTaskConsProd;

public class Producer implements Runnable{
    private MyQueue myQueue;
    public Producer(MyQueue myQueue){
        this.myQueue = myQueue;
    }


    @Override
    public void run() {
        for (int i = 0; i <200 ; i++) {
            myQueue.put(i);
        }
        System.out.println(this.toString()+" остановился");
    }

}
