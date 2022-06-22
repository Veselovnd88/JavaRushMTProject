package equalsNhashcodes;

public class SyncTry {
	public static void main(String[] args) throws InterruptedException {
		Thread th1 = new Thread1();
		th1.start();
		meth();
		
		th1.join();
		Thread th = Thread.currentThread();
		System.out.println(th.getName());
	}
	
	public static class Thread1 extends Thread {
		
		@Override
		public void run() {
			System.out.println("Invoked "+this.getName());
			meth();
			System.out.println("out");
			try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	
	
	
	
}	public static synchronized void meth() {
		System.out.println("Inside meth Method");
}
	
	}
