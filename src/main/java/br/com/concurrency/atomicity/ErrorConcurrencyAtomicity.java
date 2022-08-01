package br.com.concurrency.atomicity;

public class ErrorConcurrencyAtomicity implements AtomicityThread {
    private static Integer LIMIT_ID_INCREMENT = 400;
    private static int EXPECTED_INCREMENT_BY_EXECUTION = 80;
    private static long id;

    @Override
    public boolean execute() {
        long currentValue;
        do {
            currentValue = this.getId();

            this.startSequenceOfThreads();

            this.logCurrentValue();

            if (isValidIncrement(EXPECTED_INCREMENT_BY_EXECUTION, currentValue)) continue;

            this.logValueinconsistencyFailure();
            return false;

        } while (this.getId() < LIMIT_ID_INCREMENT);
        return true;
    }

    @Override
    public void startSequenceOfThreads() {
        final Thread t1 = new Thread(new ErrorConcurrencyAtomicityRunnable());
        final Thread t2 = new Thread(new ErrorConcurrencyAtomicityRunnable());
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
    public long getId() {
        return id;
    }

    record ErrorConcurrencyAtomicityRunnable() implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                id += 2;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
