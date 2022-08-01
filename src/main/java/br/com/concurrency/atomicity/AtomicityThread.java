package br.com.concurrency.atomicity;

import java.util.concurrent.atomic.AtomicLong;

public interface AtomicityThread {
    boolean execute();

    void startSequenceOfThreads();

    default boolean isValidIncrement(int expectedIncrement, long currentValue) {
        return (currentValue + expectedIncrement) == this.getId();
    }

    default void logValueinconsistencyFailure() {
        System.out.println("Value inconsistency found");
    }

    default void logCurrentValue() {
        System.out.println(this.getId());
    }

    long getId();
}
