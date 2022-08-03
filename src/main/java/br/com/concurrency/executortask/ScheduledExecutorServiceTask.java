package br.com.concurrency.executortask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class ScheduledExecutorServiceTask implements Task {
    private static final int EXPECTED_EXECUTION_NUMBER = 10;
    private static final int SCHEDULED_TIME_DELAY = 2;
    private static final int TIME_TO_SLEAP_BEFORE_EACH_THREAD_EXECUTION = 1000;
    private static final int TIME_TO_SLEAD_INSIDE_THREAD_PROCESSMENT = 5000;

    @Override
    public boolean execute() {
        int numberCores = Runtime.getRuntime().availableProcessors();
        this.log("number cores in the current machine %s", numberCores);

        //That schedule implementation enables that a thread execution could be scheduled
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(numberCores);

        //Responsible for count down the number of threads that had been executed successfully
        final CountDownLatch latch = new CountDownLatch(EXPECTED_EXECUTION_NUMBER);

        //Ensures the data consistency between threads during the reading and writing process on that variable
        final AtomicInteger currentExecutionNumber = new AtomicInteger(0);
        final int initialTimeInSeconds = (int) System.currentTimeMillis() / 1000;
        try {
            //Number of times that a scheduling should be performed
            for (int i = 0; i < EXPECTED_EXECUTION_NUMBER; i++) {

                Thread.sleep(TIME_TO_SLEAP_BEFORE_EACH_THREAD_EXECUTION);
                final BiConsumer<String, Object[]> functionalLog = (t, u) -> this.log(t, u);

                /**The defined delay is being set up to wait for a period of time only before starting the first execution
                 * The next executions won't have this delay.*/
                executor.schedule(
                        new ScheduledExecutorServiceTaskRunnable(currentExecutionNumber, latch, functionalLog),
                        SCHEDULED_TIME_DELAY,
                        TimeUnit.SECONDS
                );
            }
            latch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return currentExecutionNumber.get() == EXPECTED_EXECUTION_NUMBER &&
                this.isExpectedTimeExecution(((int) System.currentTimeMillis() / 1000) - initialTimeInSeconds);
    }

    private boolean isExpectedTimeExecution(int totalSpendingTime) {
        return (EXPECTED_EXECUTION_NUMBER * TIME_TO_SLEAP_BEFORE_EACH_THREAD_EXECUTION / 1000) +
                (TIME_TO_SLEAD_INSIDE_THREAD_PROCESSMENT / 1000) +
                (SCHEDULED_TIME_DELAY) == totalSpendingTime;
    }

    record ScheduledExecutorServiceTaskRunnable(AtomicInteger executionNumber,
                                                CountDownLatch latch,
                                                BiConsumer<String, Object[]> log) implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(TIME_TO_SLEAD_INSIDE_THREAD_PROCESSMENT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.accept("Thread %s executing process %d", new Object[]{Thread.currentThread().getName(), executionNumber.incrementAndGet()});
            latch.countDown();
        }
    }
}
