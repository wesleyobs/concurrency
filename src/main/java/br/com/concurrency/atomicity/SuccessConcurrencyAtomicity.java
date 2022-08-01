package br.com.concurrency.atomicity;

import java.util.concurrent.atomic.AtomicLong;

public class SuccessConcurrencyAtomicity implements AtomicityThread {
    private static Integer LIMIT_ID_INCREMENT = 400;
    private static int EXPECTED_INCREMENT_BY_EXECUTION = 80;
    private AtomicLong id = new AtomicLong();

    @Override
    public boolean execute() {
        long currentValue;
        do {
            currentValue = this.getId();

            this.startSequenceOfThreads();

            this.logCurrentValue();

            if (this.isValidIncrement(EXPECTED_INCREMENT_BY_EXECUTION, currentValue)) continue;

            this.logValueinconsistencyFailure();
            return false;
        } while (this.getId() < LIMIT_ID_INCREMENT);
        return true;
    }

    @Override
    public void startSequenceOfThreads() {
        final Thread t1 = new Thread(new SuccessCuncurrencyAtomicityRunnable(id));
        final Thread t2 = new Thread(new SuccessCuncurrencyAtomicityRunnable(id));

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
        return this.id.get();
    }

    record SuccessCuncurrencyAtomicityRunnable(AtomicLong id) implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                id.getAndAdd(2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
