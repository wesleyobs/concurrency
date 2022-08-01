package br.com.concurrency.volatiles;

public class ErrorConcurrencyVolatile implements VolatileThread {
    private static boolean advance = true;
    private int count = 0;

    @Override
    public boolean execute() {
        final Thread thread = new Thread(new ErrorConcurrencyVolatileRunnable());
        thread.start();
        try {
            Thread.sleep(100);
            advance = false;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count < MAX_EXECUTION;
    }

    class ErrorConcurrencyVolatileRunnable implements Runnable {
        @Override
        public void run() {
            while (advance || count < MAX_EXECUTION) {
                count++;
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            ;
        }
    }
}
