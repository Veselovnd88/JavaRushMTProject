package sync;

public class Task1 {
	
    public static void main(String[] args) {
    }
    
    
    public static class IntegerHolder{
    	private int value;

		public synchronized int getValue() {
			return value;
		}

		public synchronized void setValue(int value) {
			this.value = value;
		}
    	
    	
    	
    }

}
