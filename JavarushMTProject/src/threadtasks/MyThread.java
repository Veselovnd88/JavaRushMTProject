package threadtasks;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static volatile AtomicInteger priority = new AtomicInteger(1);

    private synchronized void correctPriority() {
        int newPriority = priority.getAndIncrement();
        newPriority = getThreadGroup() != null && newPriority > getThreadGroup().getMaxPriority() ? getThreadGroup().getMaxPriority() : newPriority;
        setPriority(newPriority);
        if (priority.intValue() > MAX_PRIORITY) {
            priority.set(1);
        }
    }//создали статическую переменную приоритета, если группа не ноль и новый приоритет больше приоритета группы - оставляем
    //приоритет группы, если нет - то устанавливаем рассчитанное далее рассчитывает значение приоритета, чтобы крутило циклично



    public MyThread() {
        super();
        correctPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        correctPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        correctPriority();
    }

    public MyThread(String name) {
        super(name);
        correctPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        correctPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        correctPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        correctPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        correctPriority();
    }
}
