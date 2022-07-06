package threadtasks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketSolution extends ThreadPoolExecutor {
    public SocketSolution(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    	/* corePoolSize - минимальное количество нитей
    	 * maximumPoolSize - максимальное кличество нитей
    	 * keepAliveTime - когда количеств нитей больше чем в проце, это время которое нити будут ожидать задач перед завершением
    	 * unit - единица времени
    	 * блокирующая очередь с раннаблами
    	*/
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);//создан такой пулл экзекьютор
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {///передаем объект коллабл
        if (callable instanceof CancellableTask) {//если эземпляр класса канселбл то ну этого объекта вызываем метод
            return ((CancellableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);//если нет, то возвращает объект РанблФьючер от этого объекта коллбл
        }
    }

    public static void main(String[] args) {
    }
}