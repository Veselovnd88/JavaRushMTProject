package threadtasks;

public class LoggingStateThread extends Thread {
	private Thread thr;
	
	
	public LoggingStateThread (Thread thr) {
		this.thr = thr;
		setDaemon(true);
	}


	@Override
	public void run() {
		State currentState =thr.getState();
		System.out.println(currentState);
		
		while(currentState!=Thread.State.TERMINATED) {
			State newState;
			if((newState = thr.getState())!=currentState) {
				System.out.println(newState);
				currentState = newState;
			}					
		}
	}
}
