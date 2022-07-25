package caching;

public interface Computable<Argument, Value> {
    Value compute(Argument argument) throws InterruptedException;
}