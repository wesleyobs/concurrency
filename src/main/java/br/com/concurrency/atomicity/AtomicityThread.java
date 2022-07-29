package br.com.concurrency.atomicity;

import java.util.concurrent.atomic.AtomicLong;

public interface AtomicityThread {
    AtomicLong id = new AtomicLong();
    boolean execute();

    void startSequenceOfThreads();
    default boolean isValidIncrement(int expectedIncrement, long currentValue) {
        return (currentValue + expectedIncrement) == id.get();
    }

    default void logValueinconsistencyFailure(){
        System.out.println("Value inconsistency found");
    }

    default void logCurrentValue(){
        System.out.println(id.get());
    }
}
