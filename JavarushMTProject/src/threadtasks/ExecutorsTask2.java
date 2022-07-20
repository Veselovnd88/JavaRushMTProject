package threadtasks;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsTask2 {
    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Runnable> lbq = new LinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            lbq.add(new Runnable() {//offer не принимает валидатор - разница в том, что add бросает исключение а offer нет
                @Override
                public void run() {
                    doExpensiveOperation(finalI +1);
                }
            });
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,1000, TimeUnit.MILLISECONDS,lbq);
        threadPoolExecutor.prestartAllCoreThreads();//запуск тредов которые входят в основное количество ядро
        threadPoolExecutor.shutdown();//запрет на запуск других задача
        threadPoolExecutor.awaitTermination(5,TimeUnit.SECONDS); //ждем 5 сек до отключения

        // Add your code here

        /* Example output
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
