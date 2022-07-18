package threadtasks;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryTask {
    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());//первый параметр - группа к которой мы присвоили
        //второй раннбл

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }



    public static class AmigoThreadFactory implements ThreadFactory {

        private static AtomicInteger numFactory = new AtomicInteger(1);
        private  AtomicInteger numThread = new AtomicInteger(1);
        private String group;
        public AmigoThreadFactory(){
            this.group = Thread.currentThread().getThreadGroup().getName()+"-pool-"+numFactory.getAndIncrement()+"-thread-";
            numFactory.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
           // System.out.println("Создал нить");
            int thrNum = numThread.getAndIncrement();
            String name = this.group+thrNum;
            int factNum = numFactory.get();
            Thread th = new Thread(Thread.currentThread().getThreadGroup(), r,name,0);
            th.setDaemon(false);
            th.setPriority(Thread.NORM_PRIORITY);

            return th;
        }
    }
}
