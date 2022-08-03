package br.com.concurrency.executortask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScheduledExecutorServiceTaskTest {
    @Test
    public void testScheduledExecutorServiceTaskTest() {
        Task task = new ScheduledExecutorServiceTask();
        Assertions.assertTrue(task.execute());
    }
}
