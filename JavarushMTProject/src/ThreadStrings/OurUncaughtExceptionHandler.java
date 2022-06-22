package ThreadStrings;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForSecondThread(t, e, string));
        } else {
            System.out.println(getFormattedStringForOtherThread(t, e, string));
        }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        System.out.println(t.getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println(e.getClass().getSuperclass().getSuperclass().getSimpleName());
        System.out.println(e.getClass().getCanonicalName());
        
       
    	
    	return "";
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        return null;
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        return null;
    }

}
