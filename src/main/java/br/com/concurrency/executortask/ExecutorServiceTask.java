package br.com.concurrency.executortask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceTask implements Task {
    private static final int PROCESS_NUMBER = 2;

    @Override
    public boolean execute() {
        int numberCores = Runtime.getRuntime().availableProcessors();

        System.out.println(String.format("number cores in the current machine %s", numberCores));
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(numberCores);

        final CountDownLatch latch = new CountDownLatch(2);

        final AtomicInteger executionNumber = new AtomicInteger(0);
        for (int i = 0; i < PROCESS_NUMBER; i++) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executor.schedule(() -> {
                System.out.println(String.format("Thread %s executing process %d", Thread.currentThread().getName(), executionNumber.incrementAndGet()));
                latch.countDown();


            }, 1, TimeUnit.SECONDS);
        }

        try {
            latch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(executionNumber.get());
        return executionNumber.get() == PROCESS_NUMBER;
    }
}
