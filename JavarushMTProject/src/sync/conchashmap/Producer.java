package sync.conchashmap;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
	
    private ConcurrentHashMap<String, String> map;// канкарент хэшмап блокирует только конкретный бакет при обращении к нему

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

	@Override
	public void run() {
					int i=1;
			while(true) {		
		try {


			this.map.putIfAbsent(String.valueOf(i), "Some text for "+i);
			i++;
			Thread.sleep(500);
			
		}
		catch (InterruptedException e) {
			System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
		}
		}
	
		
	}

}
