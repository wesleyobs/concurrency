package br.com.concurrency.volatiles;

public class SuccessConcurrencyVolatile implements VolatileThread {
    @Override
    public void execute() {

    }


    class SuccessConcurrencyVolatileRunnable implements Runnable {

        @Override
        public void run() {
            while (initialValue.get() || cont.getAndIncrement() <= 30) {

            }

        }
    }
}
