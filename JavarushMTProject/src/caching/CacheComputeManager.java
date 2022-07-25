package caching;

import java.util.concurrent.*;

/* Argument and Value are generic types*/
public class CacheComputeManager<Argument, Value> implements Computable<Argument, Value> {
    private final ConcurrentHashMap<Argument, Future<Value>> cache = new ConcurrentHashMap<>();//конкХэшМапа с ключом аргумент, и фючером со зн. Вэлью
    private final Computable<Argument, Value> computable;//Argument Value - это просто дженерики

    public CacheComputeManager(Computable<Argument, Value> computable) {
        this.computable = computable;
    }

    @Override
    public Value compute(final Argument arg) throws InterruptedException {
        Future<Value> f = cache.get(arg);//достаем фьючер в котором вэлью
        if (f == null) {//если нулл
            FutureTask<Value> ft = createFutureTaskForNewArgumentThatHasToComputeValue(arg);
            //должн создать фьючертаск
            cache.putIfAbsent(arg, ft);//положили в мапу это таску
            f = ft;
            ft.run();//запустили
            System.out.print(arg + " will be cached  ");
        } else {
            System.out.print(arg + " taken from cache  ");
        }
        try {
            return f.get();//пытаемся получить фьючер - результат выполнения функции
        } catch (CancellationException e) {
            cache.remove(arg, f);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
        return null;
    }

    public FutureTask<Value> createFutureTaskForNewArgumentThatHasToComputeValue(final Argument arg) {
        //сюда приходит аргумент - аргумент - для этого класса это Argument от переданного класса Computable
        FutureTask<Value> ft = new FutureTask<>(new Callable<Value>() {
            @Override
            public Value call() throws Exception {
                return computable.compute(arg);
            }
        });
        return ft;
    }
}

