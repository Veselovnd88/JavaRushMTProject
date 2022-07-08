import java.lang.Thread.UncaughtExceptionHandler;
import java.util.LinkedList;
import java.util.List;

public class HandlerTask3 implements UncaughtExceptionHandler {
	
	
    @Override
    public void uncaughtException(Thread t, Throwable e) {
    	t.interrupt();
    	List<String> except = new LinkedList<>();
    	while(e!=null) {
    		except.add(0, e.toString());
    		e = e.getCause();
    	}
    	except.forEach(x-> System.out.println(x));
    	
    	
    }

    public static void main(String[] args) throws Exception {
        try {
            throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        }catch (Exception e) {
            HandlerTask3 solution = new HandlerTask3();
            solution.uncaughtException(Thread.currentThread(),e);
        }
    }
}
