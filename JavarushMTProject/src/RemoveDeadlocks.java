public class RemoveDeadlocks {

    public void safeMethod(Object obj1, Object obj2) {
        /* чтобы они поступали всегда в одном порядке  - нужно этот порядок  определить */
        Object obj_first = obj1.hashCode()>obj2.hashCode()?obj1:obj2;
        Object obj_second = obj1.hashCode()>obj2.hashCode()?obj2:obj1;

        synchronized (obj_first) {//сначала блокируется монитор обж1 - запускается лонтайметод, потом запускается забирает объект 2
            longTimeMethod();
            synchronized (obj_second) {
                unsafeMethod(obj1, obj2);
            }
        }
    }

    public void longTimeMethod() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    protected void unsafeMethod(Object obj1, Object obj2) {
        System.out.println(obj1 + " " + obj2);
    }

    public static void main(String[] args) {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final RemoveDeadlocks solution = new RemoveDeadlocks();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o1, o2);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o2, o1);
            }
        }.start();
    }
}
