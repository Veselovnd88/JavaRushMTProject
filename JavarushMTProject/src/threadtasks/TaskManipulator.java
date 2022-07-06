package threadtasks;


public class TaskManipulator implements Runnable, CustomThreadManipulator {
	
	private Thread thr;
    @Override
    public void run() {
    		Thread thr = Thread.currentThread();//получили текущую нить
    		while(!thr.isInterrupted()) {//проверили что не прервана
    			System.out.println(thr.getName());
    			try {
					Thread.sleep(100);
				} catch (InterruptedException e) {					
					break;
				}
    		}
    		
    	
    	
    }

	@Override
	public void start(String threadName) {
		thr = new Thread(this,threadName);//создали нить, передали туда наш раннбл + имя
		thr.start();//запустили нить

		
	}

	@Override
	public void stop() {
		thr.interrupt();
		
	}
}
