package br.com.concurrency.executortask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExecutorServiceTaskTest {
    @Test
    public void testExecutorServiceTaskTest() {
        Task task = new ExecutorServiceTask();
        Assertions.assertTrue(task.execute());
    }
}
