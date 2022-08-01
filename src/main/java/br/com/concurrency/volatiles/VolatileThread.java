package br.com.concurrency.volatiles;

public interface VolatileThread {
    int MAX_EXECUTION = 1000;
    boolean execute();
}
