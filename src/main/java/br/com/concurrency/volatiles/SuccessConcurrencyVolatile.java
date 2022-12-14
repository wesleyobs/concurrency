package br.com.concurrency.volatiles;

public class SuccessConcurrencyVolatile implements VolatileThread {
    private static volatile boolean advance = true;
    private int count = 0;

    @Override
    public boolean execute() {
        final Thread thread = new Thread(new SuccessConcurrencyVolatileRunnable());
        thread.start();
        try {
            Thread.sleep(30);
            advance = false;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count < MAX_EXECUTION;
    }


    class SuccessConcurrencyVolatileRunnable implements Runnable {
        @Override
        public void run() {
            while (advance && count < MAX_EXECUTION) {
                count++;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
