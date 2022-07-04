package threadtasks;

import java.lang.Thread.State;

public class Task2 {
	
	
    public static void processThreads(Thread... threads) {
        for(Thread thr: threads){
         State st = thr.getState();
         switch(st) {
             case NEW:
                 thr.start();
                 break;
            case WAITING:
                thr.interrupt();
                break;
            case TIMED_WAITING:
                thr.interrupt();
                break;
            case BLOCKED:
                thr.interrupt();
                break;
            case RUNNABLE:
                    if(thr.isInterrupted()){
                        break;
                    }
                    else break;
            case TERMINATED:
                    System.out.println(thr.getPriority());
                    break;


         }
         }
     }

     public static void main(String[] args) {
    	 
    	 
     }
}
