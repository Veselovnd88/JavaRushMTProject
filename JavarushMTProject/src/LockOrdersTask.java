public class LockOrdersTask {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    private static volatile boolean flag = false;
    private static volatile boolean isInnerThreadBlocked = false;

    public static boolean isLockOrderNormal(final LockOrdersTask solution, final Object o1, final Object o2) throws Exception {
        //do something here
        synchronized (o1) {//метод заблокировал монитор o1
            Thread outerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread innerThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            solution.someMethodWithSynchronizedBlocks(o1, o2);//внутри нити вызвали метод который- берет монитор о1, потом берет монитор о2
                            //
                        }
                    });
                    innerThread.start();//запустили

                    while (innerThread.getState() != Thread.State.BLOCKED) ;//работает пока не заблокирована - потом
                    isInnerThreadBlocked = true;// ставим блокировку в тру
                    synchronized (o2) { //потом берем монитор о2
                        flag = true;
                    }
                }
            });
            outerThread.setDaemon(true);
            outerThread.start();

            while (!isInnerThreadBlocked) {//пока незаблокирована внутрення нить-спим
                Thread.sleep(1);
            }
            while (outerThread.getState() != Thread.State.BLOCKED && outerThread.isAlive()) {
                Thread.sleep(1);
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {
        final LockOrdersTask solution = new LockOrdersTask();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
