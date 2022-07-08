package threadtasks;

public class Task3 {
    public static void main(String[] args) {
        MyThread myThread = new Task3().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
           // setDaemon(true);
        }

        
        
        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }    
    private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread thread, Throwable throwable) {
			try {
				Thread.sleep(500);
				
				
				
				
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}System.out.println(String.format("%s %s %s", MyThread.this.secretKey, thread.getName(), throwable.getMessage() ));
			
			
		}
    	
    }
    }
    
    
    

}
