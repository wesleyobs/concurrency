package br.com.concurrency.atomicity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorConcurrencyAtomicityTest {
    @Test
    public void testCuncurrencyAtomicityWhenError() throws InterruptedException {
        final AtomicityThread errorCuncurrencyAtomicity = new ErrorConcurrencyAtomicity();
        boolean execute = errorCuncurrencyAtomicity.execute();
        Assertions.assertFalse(execute);
    }
}
