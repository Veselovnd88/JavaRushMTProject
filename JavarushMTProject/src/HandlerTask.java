import java.io.FileNotFoundException;
import java.io.IOException;

public class HandlerTask extends Thread {
	
	public HandlerTask() {
		UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				if(e instanceof Error ) {
					System.out.println("Нельзя дальше работать");
					return;
				}
				else if(e instanceof Exception) {
					System.out.println("Надо обработать");
				}
				else if(e instanceof Throwable) {
					System.out.println("Поживем увидим");
				}				
			}
		};
		this.setUncaughtExceptionHandler(handler);
		
	}
	
	@Override
	public void run()  {
		super.run();

	}


	
	public static void main(String[] args) {
		Thread thr = new HandlerTask();
		thr.start();
	

	}

}
