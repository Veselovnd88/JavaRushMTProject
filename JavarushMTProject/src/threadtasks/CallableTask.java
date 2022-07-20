package threadtasks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();//список с фьючерами
        ExecutorService executor = Executors.newFixedThreadPool(5);// тредпул с 5 нитями
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));//добавляются в список 10 фьючеров 1000-1010
        }

        futures.add(executor.submit(getTask(10000000)));// добавляется в список еще 1 фьючер - 1000000

        for (Future<String> future : futures) {
            System.out.println(future.get());// печатаем
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

/* output
500500 -1000
501501 -1001
502503 -1002
503506 - 1003
504510 -1004
505515
506521
507528
508536
509545
510555
50000005000000
*/
    }

    public static Callable<String> getTask(final int i) {

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                BigInteger sum = BigInteger.ZERO;
                for (int j = 1; j <= i; j++) {
                    sum=sum.add(BigInteger.valueOf(j));//сумма всех числе от 1 до i
                }
                return sum.toString();
            }
        };
    }
}
