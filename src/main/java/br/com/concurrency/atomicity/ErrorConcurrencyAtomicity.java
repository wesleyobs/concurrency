package br.com.concurrency.atomicity;

public class ErrorConcurrencyAtomicity implements AtomicityThread {
    private static Integer LIMIT_ID_INCREMENT = 22000;
    private static long id;

    @Override
    public boolean execute() {
        int incrementForExecution = 1100;
        long currentValue;
        do {
            currentValue = id;

            this.startSequenceOfThreads();

            this.logCurrentValue();

            if (isValidIncrement(incrementForExecution, currentValue)) continue;

            this.logValueinconsistencyFailure();
            return false;

        } while (id < LIMIT_ID_INCREMENT);
        return true;
    }

    @Override
    public void startSequenceOfThreads() {
        final Thread t1 = new Thread(new WriteAndReadAtomicityRunnable(10));
        final Thread t2 = new Thread(new WriteAndReadAtomicityRunnable(100));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logCurrentValue() {
        System.out.println(id);
    }

    record WriteAndReadAtomicityRunnable(long incrementValue) implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                id += incrementValue;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
