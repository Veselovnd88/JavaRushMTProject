import java.lang.Thread.UncaughtExceptionHandler;
import java.util.TimerTask;

public class HandlerTask2 extends TimerTask {//таймертаск  - это раннбл
	
	   protected TimerTask original;
	    protected final Thread.UncaughtExceptionHandler handler;

	    public HandlerTask2(TimerTask original) {
	        if (original == null) {
	            throw new NullPointerException();
	        }
	        this.original = original;
	        class myHandler implements Thread.UncaughtExceptionHandler{

				@Override
				public void uncaughtException(Thread thread, Throwable throwable) {
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<thread.getName().length(); i++) {
						sb.append("*");
					}
					System.out.println(sb.toString()+ ""+ throwable.getCause());
					
				}
	        	
	        	
	        }
	        
	        
	        
	        this.handler = new myHandler();    //init handler here
	    }

	    public void run() {
	        try {
	            original.run();
	        } catch (Throwable cause) {
	            Thread currentThread = Thread.currentThread();
	            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
	        }
	    }

	    public long scheduledExecutionTime() {
	        return original.scheduledExecutionTime();
	    }

	    public boolean cancel() {
	        return original.cancel();
	    }

	    public static void main(String[] args) {
	    	
	        HandlerTask2 solution = new HandlerTask2(new TimerTask() {
	            @Override
	            public void run() {
	                throw new RuntimeException();
	            }
	        });

	        solution.run();
	    	
	    	
	    }

}
