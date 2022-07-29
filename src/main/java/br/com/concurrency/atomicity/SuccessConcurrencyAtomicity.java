package br.com.concurrency.atomicity;

public class SuccessConcurrencyAtomicity implements AtomicityThread {
    private static Integer LIMIT_ID_INCREMENT = 22000;
    private static int EXPECTED_INCREMENT_BY_EXECUTION = 1100;

    @Override
    public boolean execute() {
        long currentValue;
        do {
            currentValue = id.get();

            this.startSequenceOfThreads();

            this.logCurrentValue();

            if (this.isValidIncrement(EXPECTED_INCREMENT_BY_EXECUTION, currentValue)) continue;

            this.logValueinconsistencyFailure();
            return false;
        } while (id.get() < LIMIT_ID_INCREMENT);
        return true;
    }

    @Override
    public void startSequenceOfThreads() {
        final Thread t1 = new Thread(new SuccessCuncurrencyAtomicityRunnable(10));
        final Thread t2 = new Thread(new SuccessCuncurrencyAtomicityRunnable(100));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    record SuccessCuncurrencyAtomicityRunnable(int incrementValue) implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                id.getAndAdd(incrementValue);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
