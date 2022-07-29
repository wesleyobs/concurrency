package br.com.concurrency.volatiles;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public interface VolatileThread {
    AtomicBoolean initialValue = new AtomicBoolean(true);
    AtomicInteger cont = new AtomicInteger(0);


    void execute();
}
